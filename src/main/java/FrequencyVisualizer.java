import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FrequencyVisualizer extends JLabel {

    private int positionX;
    private int positionY;
    private int upperFloor;
    private int downFloor;
    private int radius;

    private int speedValue;
    private int speedSign;
    private Lock speedLock;

    public FrequencyVisualizer(int width, int height, int upperFloor, int downFloor, int radius){
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setHorizontalAlignment(JLabel.LEFT);
        this.setVerticalAlignment(JLabel.TOP);

        this.radius = radius;
        this.positionX = width/2;
        this.positionY = downFloor - 2*radius;
        this.upperFloor = upperFloor;
        this.downFloor = downFloor;
        this.speedValue = 4;
        this.speedSign = -1;
        this.speedLock = new ReentrantLock();
    }

    public void setSpeedValue(int speedValue){
        this.speedLock.lock();
        this.speedValue = speedValue;
        this.speedLock.unlock();
    }

    @Override
    protected void paintComponent(Graphics g){
        this.speedLock.lock();
        super.paintComponent(g);
        if(this.positionY - this.radius <= this.upperFloor || this.positionY + this.radius >= this.downFloor){
            this.speedSign *= -1;
        }
        this.positionY += this.speedSign * this.speedValue;
        g.setColor(Color.BLUE);
        g.fillOval(this.positionX - this.radius, this.positionY - this.radius, 2 * this.radius, 2 * this.radius);
        this.speedLock.unlock();
    }
}
