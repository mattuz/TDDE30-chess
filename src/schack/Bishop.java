package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final String color, final URL path, final Board board) {
	super(x, y, type, color, path, board);
    }

    public boolean squareChecker(int prevX, int prevY, boolean freePath) { //TODO vet inte ens om denna behövs längre
	if (prevX > getPieceX() && prevY > getPieceY() || prevX > getPieceX() && prevY < getPieceY()) {
	    for (int i = prevX-1; i > getPieceX(); i--) {
		if ((board.getPieceTypeAt(i, prevY - prevX+i ) != PieceType.EMPTY && prevY - prevX+i != -1) ||
		    (board.getPieceTypeAt(i, prevY + prevX-i ) != PieceType.EMPTY && prevY + prevX-i != -1 )) {
		    freePath = false;
		}
	    }
	}
	else if (prevX < getPieceX() && prevY > getPieceY() || prevX < getPieceX() && prevY < getPieceY()) {
	    for (int i = prevX+1; i < getPieceX(); i++) {
		if ((board.getPieceTypeAt(i, prevY + i-prevX ) != PieceType.EMPTY && prevY + i-prevX != -1 ) ||
		    (board.getPieceTypeAt(i, prevY - i+prevX ) != PieceType.EMPTY && prevY - i+prevX != -1)) {
		    freePath = false;
		}
	    }
	} return freePath;
    }

    public void updateLegalMoves(){
        legalMoves = addDiagonal(legalMoves,7, new Position(getPieceX(),getPieceY()));
    }

    /*public boolean istLegal(int prevX, int prevY){
	return Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY) && color == board.getState();
    }

    public boolean isLegal(int prevX, int prevY){
	if ((Math.abs(prevX - getPieceX()) - Math.abs(prevY - getPieceY()) == 0) &&
	    isValidDestination() && color == board.getState()) {
	    boolean freePath = true;
	    if (prevX < getPieceX() && prevY < getPieceY()) {
		for (int i = prevX+1; i < getPieceX(); i++) {
		    if (board.getPieceTypeAt(i, prevY + i-prevX ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > getPieceX() && prevY < getPieceY()) {
		for (int i = prevX-1; i > getPieceX(); i--) {
		    if (board.getPieceTypeAt(i, prevY + prevX-i ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX < getPieceX() && prevY > getPieceY()) {
		for (int i = prevX+1; i < getPieceX(); i++) {
		    if (board.getPieceTypeAt(i, prevY - i+prevX ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > getPieceX() && prevY > getPieceY()) {
		for (int i = prevX-1; i > getPieceX(); i--) {
		    if (board.getPieceTypeAt(i, prevY - prevX+i ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    } return freePath;
	} else {
	    return false;
	}
    }
    */
}


