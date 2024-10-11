public class Snake {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    private int direction = RIGHT;
    private final int[] x = new int[GameBoard.BOARD_WIDTH * GameBoard.BOARD_HEIGHT / GameBoard.TILE_SIZE];
    private final int[] y = new int[GameBoard.BOARD_WIDTH * GameBoard.BOARD_HEIGHT / GameBoard.TILE_SIZE];
    private int bodyParts;

    public Snake(int maxLength) {
        bodyParts = 1;
        x[0] = GameBoard.BOARD_WIDTH / 2;
        y[0] = GameBoard.BOARD_HEIGHT / 2;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case UP:
                y[0] -= GameBoard.TILE_SIZE;
                break;
            case DOWN:
                y[0] += GameBoard.TILE_SIZE;
                break;
            case LEFT:
                x[0] -= GameBoard.TILE_SIZE;
                break;
            case RIGHT:
                x[0] += GameBoard.TILE_SIZE;
                break;
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void grow() {
        bodyParts++;
    }
}
