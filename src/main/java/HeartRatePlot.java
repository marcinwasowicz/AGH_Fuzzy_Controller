import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HeartRatePlot extends JLabel {

    private ArrayList<ArrayList<Integer>> points;
    private int maxPoints;
    private Lock pointsLock;

    public HeartRatePlot(int width, int height, int maxPoints){
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setHorizontalAlignment(JLabel.LEFT);
        this.setVerticalAlignment(JLabel.TOP);

        this.points = new ArrayList<ArrayList<Integer>>();
        this.maxPoints = maxPoints;
        this.pointsLock = new ReentrantLock();
    }

    public void addPoint(ArrayList<Integer> point){
        this.pointsLock.lock();
        if(this.points.size() >= this.maxPoints){
            this.points.remove(0);
        }
        this.points.add(point);
        this.pointsLock.unlock();
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.pointsLock.lock();
        super.paintComponent(g);
        g.setColor(Color.RED);

        for(int i = 0; i<points.size() - 1; i++){
            int x1 = this.points.get(i).get(0);
            int y1 = this.points.get(i).get(1);
            int x2 = this.points.get(i + 1).get(0);
            int y2 = this.points.get(i + 1).get(1);

            if(x2 > x1){
               g.drawLine(x1, y1, x2, y2);
            }
        }

        this.pointsLock.unlock();
    }
}
