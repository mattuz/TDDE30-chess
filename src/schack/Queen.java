package schack;

import java.net.URL;

public class Queen extends Piece
{
    //protected static String color = null;

    public Queen(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

    public boolean isLegal(int prevX, int prevY){
	if (((getPieceX() == prevX || getPieceY() == prevY)) && color == Board.getState()){
	    return true;
	}
	return ((Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY)) && color == Board.getState());
    }
}
