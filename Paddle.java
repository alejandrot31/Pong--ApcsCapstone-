import java.awt.Color;
import java.awt.Graphics;

public class Paddle {

    private int width; 
    private int height; 
    private int x; 
    private int y; 
    private int yVelocity;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.yVelocity = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void moveUp() {
        yVelocity = -5;
    }

    public void moveDown() {
        yVelocity = 5;
    }

    public void stop() {
        yVelocity = 0;
    }

    public void move() {
        y += yVelocity;
        if (y < 0) y = 0;
        if (y + height > 600) y = 600 - height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
