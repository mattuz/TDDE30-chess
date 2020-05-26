package schack;

import javax.swing.*;
import java.net.URL;

public class Knight extends Piece
{
   // protected static String color = null;

    public Knight(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int prevX, int prevY){
	return ((Math.abs(getPieceX() - prevX) == 1 && Math.abs(getPieceY() - prevY) == 2) ||
		(Math.abs(getPieceX() - prevX) == 2 && Math.abs(getPieceY() - prevY) == 1))
	         && color == Board.getState() && isValidDestination();
    }

    public boolean isValidDestination(){
	return (Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY || Board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }
}
