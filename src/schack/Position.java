package schack;

/**
 * Class used to keep track of the pieces positions on the board.
 */
public class Position
{
    private int x; //Har liknande namn av uppenbara anledningar (x, y) är relativt lika rent bokstavsmässigt.
    private int y; //Men eftersom att de representerar de olika axlarna på ett pedagogiskt sätt har vi valt att ha kvar det så.

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
	return x;
    } //Ovan kommentarer berör även dessa två.

    public int getY() {
	return y;
    }

}
