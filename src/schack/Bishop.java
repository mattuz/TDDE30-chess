package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

    public boolean istLegal(int prevX, int prevY){
	return Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY) && color == Board.getState();
    }

    public boolean isLegal(int prevX, int prevY){
	if ((Math.abs(prevX - getPieceX()) - Math.abs(prevY - getPieceY()) == 0) &&
	    isValidDestination() && color == Board.getState()) {
	    boolean freePath = true;
	    if (prevX < getPieceX() && prevY < getPieceY()) {
		for (int i = prevX+1; i < getPieceX(); i++) {
		    if (Board.getPieceTypeAt(i, prevY + i-prevX ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > getPieceX() && prevY < getPieceY()) {
		for (int i = prevX-1; i > getPieceX(); i--) {
		    if (Board.getPieceTypeAt(i, prevY + prevX-i ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX < getPieceX() && prevY > getPieceY()) {
		for (int i = prevX+1; i < getPieceX(); i++) {
		    if (Board.getPieceTypeAt(i, prevY - i+prevX ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > getPieceX() && prevY > getPieceY()) {
		for (int i = prevX-1; i > getPieceX(); i--) {
		    if (Board.getPieceTypeAt(i, prevY - prevX+i ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    } return freePath;
	} else {
	    return false;
	}
    }

    public boolean squareChecker(int prevX, int prevY, boolean freePath) { //TODO vet inte ens om denna behövs längre
        if (prevX > getPieceX() && prevY > getPieceY() || prevX > getPieceX() && prevY < getPieceY()) {
	    for (int i = prevX-1; i > getPieceX(); i--) {
		if ((Board.getPieceTypeAt(i, prevY - prevX+i ) != PieceType.EMPTY && prevY - prevX+i != -1) ||
		    (Board.getPieceTypeAt(i, prevY + prevX-i ) != PieceType.EMPTY && prevY + prevX-i != -1 )) {
		    freePath = false;
		}
	    }
	}
	else if (prevX < getPieceX() && prevY > getPieceY() || prevX < getPieceX() && prevY < getPieceY()) {
	    for (int i = prevX+1; i < getPieceX(); i++) {
		if ((Board.getPieceTypeAt(i, prevY + i-prevX ) != PieceType.EMPTY && prevY + i-prevX != -1 ) ||
		    (Board.getPieceTypeAt(i, prevY - i+prevX ) != PieceType.EMPTY && prevY - i+prevX != -1)) {
		    freePath = false;
		}
	    }
	} return freePath;
    }

    public boolean isValidDestination(){
	return (Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY ||
		Board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }
}


