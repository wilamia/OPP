import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/*определяющий размер рабочей области
и хранящий список объектов, с параметрами заданными вариантом*/
public class Habitat extends JPanel {
    private CardIndex cardIndex;
    private boolean running = false;
    private long startTime;
    private int width = 800, height = 600;
    private Random random = new Random();

    public Habitat() {
        cardIndex = new CardIndex();
        setPreferredSize(new Dimension(width, height));
    }


    public void update() {
        if (running) {
            if (random.nextDouble() < 0.1) {
                if (random.nextBoolean()) {
                    cardIndex.addIndividual(random.nextInt(1, width-1), random.nextInt(1, height-1));
                } else {
                    cardIndex.addLegal(random.nextInt(1, width-1), random.nextInt(1, height-1));
                }
            }

            for (IndividualEntity individual : cardIndex.getIndividualEntities()) {
                individual.move();
            }
            for (LegalEntity legal : cardIndex.getLegalEntities()) {
                legal.move();
            }

            repaint();
        }
    }

    public void startSimulation() {
        cardIndex = new CardIndex();
        running = true;
        startTime = System.currentTimeMillis();
    }

    public void stopSimulation() {
        running = false;
        repaint(); // Перерисовать для отображения статистики
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (IndividualEntity individual : cardIndex.getIndividualEntities()) {
            individual.draw(g);
        }
        for (LegalEntity legal : cardIndex.getLegalEntities()) {
            legal.draw(g);
        }
        if (!running) {
            displayStatistics(g);
        }
    }

    private void displayStatistics(Graphics g) {
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000; // Время в секундах

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Статистика симуляции:", 10, 20);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.BLUE);
        g.drawString("Физические лица: " + cardIndex.getIndividualEntities().size(), 10, 50);

        g.setColor(Color.RED);
        g.drawString("Юридические лица: " + cardIndex.getLegalEntities().size(), 10, 70);

        g.setColor(Color.GREEN);
        g.drawString("Общее время: " + duration + " сек", 10, 90);
    }
}

