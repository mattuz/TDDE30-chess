package schack;

import java.net.URL;

public class King extends Piece
{
    //protected static String color = null;

    public King(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int prevX, int prevY){
	if ((getPieceX() == prevX && Math.abs(getPieceY() - prevY) == 1) && color == Board.getState()){
	    return true;
	}
	else if ((getPieceY() == prevY && Math.abs(getPieceX() - prevX) == 1) && color == Board.getState()){
	    return true;
	}
	else return ((Math.abs(getPieceX() - prevX) == 1 && Math.abs(getPieceY() - prevY) == 1) && color == Board.getState());
    }

}

