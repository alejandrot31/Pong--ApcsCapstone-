import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GameManager extends JFrame implements ActionListener, KeyListener {

    private Timer timer;
    private Ball ball;
    private Paddle leftPaddle, rightPaddle;
    private BufferedImage buffer;
    private GamePanel panel;
    private int leftScore, rightScore;

    public GameManager() {
        setTitle("Pong");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ball = new Ball(400, 300, 20, 20);
        leftPaddle = new Paddle(30, 250, 20, 100);
        rightPaddle = new Paddle(750, 250, 20, 100);

        panel = new GamePanel();
        add(panel);

        buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

        timer = new Timer(5, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    private class GamePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawToBuffer();
            g.drawImage(buffer, 0, 0, null);
        }

        private void drawToBuffer() {
            Graphics g = buffer.getGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
            ball.draw(g);
            leftPaddle.draw(g);
            rightPaddle.draw(g);
            drawScores(g);
            g.dispose();
        }

        private void drawScores(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Player 1: " + leftScore, 50, 50);
            g.drawString("Player 2: " + rightScore, 550, 50);
        }
    }

    public void actionPerformed(ActionEvent e) {
        ball.move(getWidth(), getHeight(), leftPaddle, rightPaddle,this);
        leftPaddle.move();
        rightPaddle.move();
        panel.repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            leftPaddle.moveUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            leftPaddle.moveDown();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rightPaddle.moveUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightPaddle.moveDown();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            leftPaddle.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightPaddle.stop();
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void increaseLeftScore() {
        leftScore++;
    }

    public void increaseRightScore() {
        rightScore++;
    }

    public static void main(String[] args) {
        GameManager game = new GameManager();
    }
}
