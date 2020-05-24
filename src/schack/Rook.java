package schack;

import java.net.URL;

public class Rook extends Piece
{
    protected static String color = null;

    public Rook(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int x, int y){
	return ((getPieceX() == x || getPieceY() == y) && color == Board.getState());
    }

}
