package schack;

import java.net.URL;

public class Queen extends Piece
{
    protected static String color = null;

    public Queen(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

    public boolean isLegal(int x, int y){
	if (((getPieceX() == x || getPieceY() == y)) && color == Board.getState()){
	    return true;
	}
	return ((Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y)) && color == Board.getState());
    }
}
