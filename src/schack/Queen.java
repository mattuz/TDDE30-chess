package schack;

import java.net.URL;

public class Queen extends Piece
{

    public Queen(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

	/*if (((getPieceX() == prevX || getPieceY() == prevY)) && color == Board.getState()){
	    return true;
	}
	return ((Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY)) && color == Board.getState());
    }*/
	public boolean isLegal(int prevX, int prevY){
	    boolean freePath = true;
	    if ((Math.abs(prevX - getPieceX()) - Math.abs(prevY - getPieceY()) == 0) && isValidDestination() && color == Board.getState()) {
		if (prevX < getPieceX() && prevY < getPieceY()) {
		    for (int i = prevX + 1; i < getPieceX(); i++) {
			if (Board.getPieceTypeAt(i, prevY + i - prevX) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		if (prevX > getPieceX() && prevY < getPieceY()) {
		    for (int i = prevX - 1; i > getPieceX(); i--) {
			if (Board.getPieceTypeAt(i, prevY + prevX - i) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		if (prevX < getPieceX() && prevY > getPieceY()) {
		    for (int i = prevX + 1; i < getPieceX(); i++) {
			if (Board.getPieceTypeAt(i, prevY - i + prevX) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		if (prevX > getPieceX() && prevY > getPieceY()) {
		    for (int i = prevX - 1; i > getPieceX(); i--) {
			if (Board.getPieceTypeAt(i, prevY - prevX + i) != PieceType.EMPTY) {
			    freePath = false;
			    break;
			}
		    }
		}
		return freePath;
	    } if (isValidDestination() && color == Board.getState() &&
		  ((Math.abs(prevX - getPieceX()) != 0 && Math.abs(prevY - getPieceY()) == 0) ||
		   (Math.abs(prevX - getPieceX()) == 0 && Math.abs(prevY - getPieceY()) != 0))) {
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
		} return freePath;
	    }
	    else {
	        return false;
	    }
	}




    public boolean isValidDestination(){
	return (Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY ||
		Board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }
}
