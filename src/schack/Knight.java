package schack;

import java.net.URL;

public class Knight extends Piece
{
    protected static String color = null;

    public Knight(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int x, int y){
	return (((Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 2) ||
		 (Math.abs(getPieceX() - x) == 2 && Math.abs(getPieceY() - y) == 2)&& color == Board.getState()));
    }
}
