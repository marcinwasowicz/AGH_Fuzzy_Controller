import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JPanel mainPanel;
    private JPanel frequencyPanel;

    public Window(String name, int width, int height, HeartRatePlot heartRatePlot, FrequencyVisualizer bouncingBall, Timer timer){
        super(name);

        heartRatePlot.setBorder(BorderFactory.createLineBorder(Color.BLACK, Parameters.borderThickness));
        heartRatePlot.setFont(new Font(Parameters.font, Font.ITALIC, Parameters.fontSize));

        bouncingBall.setBorder(BorderFactory.createLineBorder(Color.BLACK, Parameters.borderThickness));
        bouncingBall.setFont(new Font(Parameters.font, Font.ITALIC, Parameters.fontSize));

        timer.setBorder(BorderFactory.createLineBorder(Color.BLACK, Parameters.borderThickness));
        timer.setFont(new Font(Parameters.font, Font.ITALIC, Parameters.fontSize));

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout());
        this.mainPanel.setOpaque(true);
        this.mainPanel.add(heartRatePlot);
        this.mainPanel.add(timer);

        this.frequencyPanel = new JPanel();
        this.frequencyPanel.setLayout(new GridLayout());
        this.frequencyPanel.setOpaque(true);
        this.frequencyPanel.add(bouncingBall);

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(width, height));

        this.add(this.mainPanel, BorderLayout.NORTH);
        this.add(this.frequencyPanel, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

}
