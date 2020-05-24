package schack;

import java.net.URL;

public class King extends Piece
{
    protected static String color = null;

    public King(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int x, int y){
	if ((getPieceX() == x && Math.abs(getPieceY() - y) == 1) && color == Board.getState()){
	    return true;
	}
	else if ((getPieceY() == y && Math.abs(getPieceX() - x) == 1) && color == Board.getState()){
	    return true;
	}
	else return ((Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 1) && color == Board.getState());
    }

}

