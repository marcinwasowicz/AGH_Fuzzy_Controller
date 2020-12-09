import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Scheduler implements Runnable {

    private HeartRatePlot heartRatePlot;
    private FrequencyVisualizer frequencyVisualizer;
    private BodySimulator bodySimulator;
    private Controller controller;
    private Timer timer;

    private int heartRatePlotDistance;
    private double frequency;
    private int heartRate;
    private long time;

    public Scheduler(HeartRatePlot heartRatePlot, FrequencyVisualizer frequencyVisualizer, BodySimulator bodySimulator, Controller controller, Timer timer){
        this.heartRatePlot = heartRatePlot;
        this.frequencyVisualizer = frequencyVisualizer;
        this.bodySimulator = bodySimulator;
        this.controller = controller;
        this.timer = timer;

        this.heartRatePlotDistance = 0;
        this.frequency = Parameters.initialFrequency;
        this.heartRate = Parameters.restHeartRate;
        this.time = System.nanoTime();
    }

    private void waitForHeart(int rate) throws InterruptedException {
        Thread.sleep(Parameters.minute/rate);
    }

    private void tickHeart(int distance, int maxDistance, int heartState) throws InterruptedException {
        this.heartRatePlot.addPoint(new ArrayList<Integer>(Arrays.asList(this.heartRatePlotDistance, heartState)));
        this.waitForHeart(this.heartRate);
        this.heartRatePlotDistance += 2*distance;
        this.heartRatePlotDistance %= maxDistance;
    }

    private void updateHeartRate() throws InterruptedException {
       int maxDistance = this.heartRatePlot.getWidth();
       int distance = maxDistance/this.heartRate;
       this.tickHeart(distance, maxDistance, Parameters.heartLow);
       this.tickHeart(distance, maxDistance, Parameters.heartHigh);
       this.heartRatePlot.setText(Parameters.heartLabel + this.heartRate + Parameters.massLabel + this.bodySimulator.getMass());
    }

    private void updateFrequencyVisualizer(){
        int distance = this.frequencyVisualizer.getHeight();
        double speed = distance*this.frequency;
        this.frequencyVisualizer.setSpeedValue((int) speed);
        this.frequencyVisualizer.setText(Parameters.frequencyVisualizerLabel + this.frequency);
    }

    private int getHeartRate(){
        return this.bodySimulator.getHeartRate(this.frequency, this.timer.getSeconds());
    }

    private double getFrequency(final double rate, final double time, final double mass){
        HashMap<String, Double> variables = new HashMap<String, Double>() {{
           put(Parameters.heartRate, rate);
           put(Parameters.time, time);
           put(Parameters.mass, mass);
        }};
        return this.controller.evaluate(variables);
    }

    private void updateTimer(){
        if(Math.abs(this.time - System.nanoTime()) >= Parameters.second){
            this.time = System.nanoTime();
            this.timer.addSecond();
        }
    }

    private void cycle() throws InterruptedException {
        this.heartRate = this.getHeartRate();
        this.updateHeartRate();
        this.frequency = this.getFrequency(this.heartRate, this.timer.getSeconds(), this.bodySimulator.getMass());
        this.updateFrequencyVisualizer();
        this.updateTimer();
    }

    public void run() {
        while(true){
            try {
                this.cycle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

