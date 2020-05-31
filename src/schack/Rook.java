package schack;

import java.net.URL;

public class Rook extends Piece
{
    public Rook( int x,  int y, final PieceType type, final String color, final URL path, final Board board) {
	super(x, y, type, color, path, board);
    }

    public void updateLegalMoves(){
        Position position = new Position(getPieceX(), getPieceY());
        legalMoves.clear();
        legalMoves = addHorisontal(legalMoves, 8, position);
        legalMoves = addVertical(legalMoves, 8, position);
    }
    /*public boolean isLegal(int prevX, int prevY) {
	if (isValidDestination() && color == board.getState() &&
	    ((Math.abs(prevX - getPieceX()) != 0 && Math.abs(prevY - getPieceY()) == 0) ||
	     (Math.abs(prevX - getPieceX()) == 0 && Math.abs(prevY - getPieceY()) != 0))) {
	    boolean freePath = true;
	    if (prevX < getPieceX() && getPieceY() == prevY) {
		for (int i = getPieceX() - 1; i > prevX; i--) {
		    if (board.getPieceTypeAt(i, getPieceY()) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > getPieceX() && getPieceY() == prevY) {
		for (int i = getPieceX() + 1; i < prevX; i++) {
		    if (board.getPieceTypeAt(i, getPieceY()) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevY > getPieceY() && getPieceX() == prevX) {
		for (int i = getPieceY() + 1; i < prevY; i++) {
		    if (board.getPieceTypeAt(getPieceX(), i) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevY < getPieceY() && getPieceX() == prevX) {
		for (int i = getPieceY() - 1; i > prevY; i--) {
		    if (board.getPieceTypeAt(getPieceX(), i) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }	return freePath;

	} else {
	    return false;
	}
	}
     */

}
