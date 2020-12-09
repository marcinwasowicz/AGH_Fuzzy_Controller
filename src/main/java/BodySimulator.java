public class BodySimulator {

    private int mass;

    public BodySimulator(int mass){
        this.mass = mass;
    }

    public int getHeartRate(double frequency, int seconds){
        return Parameters.restHeartRate + (int)(frequency * 10000.0) + 2*seconds/70 + (this.mass/10);
    }

    public int getMass(){
        return this.mass;
    }
}
