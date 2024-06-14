import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

    private int x, y, width, height, xVelocity, yVelocity;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        Random random = new Random();
        xVelocity = random.nextBoolean() ? 5 : -5;
        yVelocity = random.nextBoolean() ? 5 : -5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move(int windowWidth, int windowHeight, Paddle leftPaddle, Paddle rightPaddle, GameManager gameManager) {
        x += xVelocity;
        y += yVelocity;

        if (y < 0 || y + height > windowHeight) {
            yVelocity = -yVelocity;
        }

        if (x < 0) {
            gameManager.increaseRightScore();
            resetBall();
        } else if (x + width > windowWidth) {
            gameManager.increaseLeftScore();
            resetBall();
        }

        if (collision(leftPaddle) || collision(rightPaddle)) {
            xVelocity = -xVelocity;
        }
    }

    private boolean collision(Paddle paddle) {
        return x < paddle.getX() + paddle.getWidth() &&
               x + width > paddle.getX() &&
               y < paddle.getY() + paddle.getHeight() &&
               y + height > paddle.getY();
    }

    private void resetBall() {
        x = 400;
        y = 300;

        Random random = new Random();
        xVelocity = random.nextBoolean() ? 5 : -5;
        yVelocity = random.nextBoolean() ? 5 : -5;
    }
}
