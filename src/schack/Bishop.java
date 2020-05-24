package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

    public boolean isLegal(int prevX, int prevY){
	return Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY) && color == Board.getState();
    }
}
