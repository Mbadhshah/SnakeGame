import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
    private int x;
    private int y;
    private final int TILE_SIZE;

    public Food(int tileSize) {
        this.TILE_SIZE = tileSize;
        generateNewPosition();
    }

    public void generateNewPosition() {
        Random random = new Random();
        x = random.nextInt(GameBoard.BOARD_WIDTH / TILE_SIZE) * TILE_SIZE;
        y = random.nextInt(GameBoard.BOARD_HEIGHT / TILE_SIZE) * TILE_SIZE;
    }

    public void spawnFood(Snake snake) {

        do {
            generateNewPosition();
        } while (isFoodOnSnake(snake));
    }

    private boolean isFoodOnSnake(Snake snake) {
        for (int i = 0; i < snake.getBodyParts(); i++) {
            if (x == snake.getX()[i] && y == snake.getY()[i]) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }
}
