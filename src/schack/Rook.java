package schack;

import java.net.URL;

public class Rook extends Piece
{
    public Rook(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int prevX, int prevY) {
	if (isValidDestination() && color == Board.getState() &&
	    ((Math.abs(prevX - getPieceX()) != 0 && Math.abs(prevY - getPieceY()) == 0) ||
	     (Math.abs(prevX - getPieceX()) == 0 && Math.abs(prevY - getPieceY()) != 0))) {
	    boolean freePath = true;
	    if (prevX < getPieceX() && getPieceY() == prevY) {
		for (int i = getPieceX() - 1; i > prevX; i--) {
		    if (Board.getPieceTypeAt(i, getPieceY()) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > getPieceX() && getPieceY() == prevY) {
		for (int i = getPieceX() + 1; i < prevX; i++) {
		    if (Board.getPieceTypeAt(i, getPieceY()) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevY > getPieceY() && getPieceX() == prevX) {
		for (int i = getPieceY() + 1; i < prevY; i++) {
		    if (Board.getPieceTypeAt(getPieceX(), i) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevY < getPieceY() && getPieceX() == prevX) {
		for (int i = getPieceY() - 1; i > prevY; i--) {
		    if (Board.getPieceTypeAt(getPieceX(), i) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }	return freePath;

	} else {
	    return false;
	}
	}



    public boolean isValidDestination(){
	return (Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY || Board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }
}
