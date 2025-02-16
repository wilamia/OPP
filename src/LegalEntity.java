import java.awt.*;
import java.util.Random;

public class LegalEntity implements IBehaviour {
    private int x, y;
    private int targetX, targetY;
    private int speed;
    private boolean reachedTarget = false;
    private Random random = new Random();

    public LegalEntity(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.speed = random.nextInt(5) + 1;
        setRandomTarget();
    }

    private void setRandomTarget() {
        this.targetX = random.nextInt(400);
        this.targetY = random.nextInt(300);
    }

    @Override
    public void move() {
        if (!reachedTarget) {
            if (x < targetX) x += speed;
            if (x > targetX) x -= speed;
            if (y < targetY) y += speed;
            if (y > targetY) y -= speed;

            if (x == targetX && y == targetY) {
                reachedTarget = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
    }
}