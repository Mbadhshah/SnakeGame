import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener {
    public static final int TILE_SIZE = 20;
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 600;
    private Timer timer;
    private Snake snake;
    private Food food;
    private int score =0;
    private boolean running = true;
    private int highScore = 0; 
    
    public GameBoard() {
        setPreferredSize(new java.awt.Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLUE);
        setFocusable(true);
        
        snake = new Snake(100);
        food = new Food(TILE_SIZE);
        food.spawnFood(snake);
        timer = new Timer(100, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && snake.getDirection() != Snake.RIGHT) {
                    snake.setDirection(Snake.LEFT);
                } else if (key == KeyEvent.VK_RIGHT && snake.getDirection() != Snake.LEFT) {
                    snake.setDirection(Snake.RIGHT);
                } else if (key == KeyEvent.VK_UP && snake.getDirection() != Snake.DOWN) {
                    snake.setDirection(Snake.UP);
                } else if (key == KeyEvent.VK_DOWN && snake.getDirection() != Snake.UP) {
                    snake.setDirection(Snake.DOWN);
                }
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        if (running) {
            drawSnake(g);
            food.draw(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20); 
            g.drawString("High Score: " + highScore, 10, 40); 
        } else {
            showGameOver(g);
        }
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snake.getBodyParts(); i++) {
            g.setColor(i == 0 ? Color.GREEN : Color.YELLOW); 
            g.fillRect(snake.getX()[i], snake.getY()[i], TILE_SIZE, TILE_SIZE);
        }
    }

    private void checkFoodCollision() {
        if (snake.getX()[0] == food.getx() && snake.getY()[0] == food.gety()) {
            snake.grow();
            food.spawnFood(snake);
            score++; 
            if (score > highScore) {
                highScore = score; 
            }
        }
    }

    private void checkCollisions() {
        checkWallCollision();
        checkSelfCollision();
    }

    private void checkSelfCollision() {
        for (int i = snake.getBodyParts(); i > 0; i--) {
            if (snake.getX()[0] == snake.getX()[i] && snake.getY()[0] == snake.getY()[i]) {
                running = false; // Snake hit itself
            }
        }
    }

    private void checkWallCollision() {
        int headX = snake.getX()[0];
        int headY = snake.getY()[0];

        if (headX < 0 || headX >= BOARD_WIDTH || headY < 0 || headY >= BOARD_HEIGHT) {
            running = false; 
        }
    }

   
    private void showGameOver(Graphics g) {
        String gameOverMessage = "Game Over! Your Score: " + score;
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(gameOverMessage, (BOARD_WIDTH / 2) - (g.getFontMetrics().stringWidth(gameOverMessage) / 2), BOARD_HEIGHT / 2);
        
 
        resetGame(); 
        
    }

    public void resetGame() {
        running = true;
        score = 0; 
        snake = new Snake(100);
        food = new Food(TILE_SIZE);
        food.spawnFood(snake); 
    }

    public void updateGame() {
        if (running) {
            snake.move();
            checkFoodCollision();
            checkCollisions();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint(); 
    }
}