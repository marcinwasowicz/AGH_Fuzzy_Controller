import javax.swing.*;
import java.awt.*;

public class Timer extends JLabel{
    private int seconds;

    public Timer(int width, int height){
        this.seconds = 0;
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    private String getTimeFormat(){
        int hours = this.seconds / 3600;
        int minutes = (this.seconds % 3600) / 60;
        int seconds = (this.seconds % 60);

        return hours + ":" + minutes + ":" + seconds;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setText(this.getTimeFormat());
    }

    public int getSeconds(){
        return this.seconds;
    }

    public void addSecond(){
        this.seconds++;
    }
}
