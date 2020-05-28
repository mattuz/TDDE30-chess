package schack;

import java.net.URL;

public class Knight extends Piece
{
   // protected static String color = null;

    public Knight( int x,  int y, final PieceType type, final String color, final URL path, final Board board) {
	super(x, y, type, color, path, board);
    }
    public boolean isLegal(int prevX, int prevY){
	return ((Math.abs(getPieceX() - prevX) == 1 && Math.abs(getPieceY() - prevY) == 2) ||
		(Math.abs(getPieceX() - prevX) == 2 && Math.abs(getPieceY() - prevY) == 1))
	       && color == board.getState() && isValidDestination();
    }

    public boolean isValidDestination(){
	return (board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY || board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }
}
