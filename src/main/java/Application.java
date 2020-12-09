
public class Application {

    public static void main(String[] args) {

        BodySimulator bodySimulator = new BodySimulator(Integer.parseInt(args[0]));

        Controller controller = new Controller(Parameters.controllerPath, Parameters.outputVariableName);

        HeartRatePlot heartRatePlot = new HeartRatePlot(
                Parameters.heartRatePlotWidth, Parameters.heartRatePlotHeight, Parameters.heartRatePlotMaxPoints);

        FrequencyVisualizer frequencyVisualizer = new FrequencyVisualizer(
                Parameters.bouncingBallWidth, Parameters.bouncingBallHeight,
                Parameters.upperFloor, Parameters.downFloor, Parameters.radius);

        Timer timer = new Timer(Parameters.timerWidth, Parameters.timerHeight);

        Window applicationWindow = new Window(
                Parameters.windowLabel, Parameters.windowWidth,
                Parameters.windowHeight, heartRatePlot, frequencyVisualizer, timer);

        Thread scheduler = new Thread(new Scheduler(heartRatePlot, frequencyVisualizer, bodySimulator, controller, timer));
        Thread renderer = new Thread(new Renderer(applicationWindow));

        scheduler.start();
        renderer.start();

    }
}
