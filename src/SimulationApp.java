import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationApp {
    private Habitat habitat;
    private Timer timer;

    public SimulationApp() {
        habitat = new Habitat();
        JFrame frame = new JFrame("Симуляция");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(habitat);
        frame.pack();
        frame.setVisible(true);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitat.update();
            }
        });

        setupControls(frame);
    }

    private void setupControls(JFrame frame) {
        JPanel controls = new JPanel();
        JButton startButton = new JButton("Запуск (S)");
        JButton stopButton = new JButton("Стоп (E)");

        startButton.addActionListener(e -> startSimulation());
        stopButton.addActionListener(e -> stopSimulation());

        controls.add(startButton);
        controls.add(stopButton);
        frame.add(controls, BorderLayout.SOUTH);
    }

    public void startSimulation() {
        habitat.startSimulation();
        timer.start();
    }

    public void stopSimulation() {
        habitat.stopSimulation();
        timer.stop();
    }
}
