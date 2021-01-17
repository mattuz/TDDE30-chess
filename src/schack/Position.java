package schack;

/**
 * Class used to keep track of the pieces positions on the board.
 */
public class Position
{
    private int x;
    private int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

}
