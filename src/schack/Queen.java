package schack;

import java.net.URL;

public class Queen extends Piece
{

    public Queen( int x,  int y, final PieceType type, final String color, final URL path, final Board board) {
	super(x, y, type, color, path, board);
    }

	/*if (((getPieceX() == prevX || getPieceY() == prevY)) && color == Board.getState()){
	    return true;
	}
	return ((Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY)) && color == Board.getState());
    }
	public boolean isLegal(int prevX, int prevY){
	    boolean freePath = true;
	    if ((Math.abs(prevX - getPieceX()) - Math.abs(prevY - getPieceY()) == 0) && isValidDestination() && color == board.getState()) {
		if (prevX < getPieceX() && prevY < getPieceY()) {
		    for (int i = prevX + 1; i < getPieceX(); i++) {
			if (board.getPieceTypeAt(i, prevY + i - prevX) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		if (prevX > getPieceX() && prevY < getPieceY()) {
		    for (int i = prevX - 1; i > getPieceX(); i--) {
			if (board.getPieceTypeAt(i, prevY + prevX - i) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		if (prevX < getPieceX() && prevY > getPieceY()) {
		    for (int i = prevX + 1; i < getPieceX(); i++) {
			if (board.getPieceTypeAt(i, prevY - i + prevX) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		if (prevX > getPieceX() && prevY > getPieceY()) {
		    for (int i = prevX - 1; i > getPieceX(); i--) {
			if (board.getPieceTypeAt(i, prevY - prevX + i) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		return freePath;
	    } if (isValidDestination() && color == board.getState() &&
		  ((Math.abs(prevX - getPieceX()) != 0 && Math.abs(prevY - getPieceY()) == 0) ||
		   (Math.abs(prevX - getPieceX()) == 0 && Math.abs(prevY - getPieceY()) != 0))) {
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
		} return freePath;
	    }
	    else {
	        return false;
	    }
	}
	*/

	public void updateLegalMoves(){
	    Position position = new Position(getPieceX(), getPieceY());
	    legalMoves.clear();
	    legalMoves = addDiagonal(legalMoves, 8, position);
	    legalMoves = addHorisontal(legalMoves, 8, position);
	    legalMoves = addVertical(legalMoves, 8, position);
	}
}
