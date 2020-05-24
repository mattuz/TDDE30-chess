package schack;

import java.net.URL;

public class Rook extends Piece
{
    //protected static String color = null;

    public Rook(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int prevX, int prevY){
	return ((getPieceX() == prevX || getPieceY() == prevY) && color == Board.getState());
    }

}
