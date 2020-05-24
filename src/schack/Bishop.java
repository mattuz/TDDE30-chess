package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

    public boolean isLegal(int x, int y){
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y) && color == Board.getState();
    }
}
