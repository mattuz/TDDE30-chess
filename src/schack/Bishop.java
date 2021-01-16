package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
	super(x, y, type, color, path, board, firstStep);
	updateLegalMoves();
    }

   /* public boolean squareChecker(int prevX, int prevY, boolean freePath) { //TODO vet inte ens om denna behövs längre
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
    }*/

    public void updateLegalMoves(){
      //  updatePreviousLegalMoves();
        legalMoves.clear();
        legalMoves = addDiagonal(legalMoves,8, new Position(getPieceX(),getPieceY()));
    }

    public void updatePreviousLegalMoves(){
	previousLegalMoves = legalMoves;
    }
}

//OYOYOY
