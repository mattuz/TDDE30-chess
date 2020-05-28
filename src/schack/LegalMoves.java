package schack;

public class LegalMoves
{
    private Board board;

    public LegalMoves(Board board) {
        this.board = board;
    }

    /*public boolean isLegalMove(Piece piece, int prevX, int prevY) {
        boolean isLegal = false;
        switch (piece.getType()) {
	    case PAWN:

	        break;
	    case KNIGHT:

	        break;
	    case BISHOP:
	        isLegal = movingDiagonal(piece, prevX, prevY);
	        break;
	    case QUEEN:
	        isLegal = (movingDiagonal(piece,prevX,prevY) || movingVertical(piece, prevX, prevY));
	        break;
	    case ROOK:

	        break;

	    case KING:

	        break;
	}
    }*/

    public boolean movingDiagonal(Piece piece, int prevX, int prevY) {
	int newX = piece.pieceX;
	int newY = piece.pieceY;
	if ((Math.abs(prevX - newX) - Math.abs(prevY - newY) == 0) &&
	    isValidDestination(piece) && piece.color == board.getState()) {
	    boolean freePath = true;
	    if (prevX < newX && prevY < newY) {
		for (int i = prevX+1; i < newX; i++) {
		    if (board.getPieceTypeAt(i, prevY + i-prevX ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > newX && prevY < newY) {
		for (int i = prevX-1; i > newX; i--) {
		    if (board.getPieceTypeAt(i, prevY + prevX-i ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX < newX && prevY > newY) {
		for (int i = prevX+1; i < newX; i++) {
		    if (board.getPieceTypeAt(i, prevY - i+prevX ) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > newX && prevY > newY) {
		for (int i = prevX-1; i > newX; i--) {
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


    public boolean movingVertical(Piece piece, int prevX, int prevY) {
	int newX = piece.pieceX;
	int newY = piece.pieceY;
	if (isValidDestination(piece) && piece.color == board.getState() &&
	    ((Math.abs(prevX - newX) != 0 && Math.abs(prevY - newY) == 0) ||
	     (Math.abs(prevX - newX) == 0 && Math.abs(prevY - newY) != 0))) {

	    boolean freePath = true;

	    if (prevX < newX && newY == prevY) {
		for (int i = newX - 1; i > prevX; i--) {
		    if (board.getPieceTypeAt(i, newY) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevX > newX && newY == prevY) {
		for (int i = newX + 1; i < prevX; i++) {
		    if (board.getPieceTypeAt(i, newY) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevY > newY && newX == prevX) {
		for (int i = newY + 1; i < prevY; i++) {
		    if (board.getPieceTypeAt(newX, i) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }
	    if (prevY < newY && newX == prevX) {
		for (int i = newY - 1; i > prevY; i--) {
		    if (board.getPieceTypeAt(newX, i) != PieceType.EMPTY) {
			freePath = false;
			break;
		    }
		}
	    }	return freePath;

	} else {
	    return false;
	}
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
    }
*/

    public boolean isValidDestination(Piece piece){
	int newX = piece.pieceX;
	int newY = piece.pieceY;
	return (board.getPieceTypeAt(newX, newY) == PieceType.EMPTY ||
		board.getPieceAt(newX, newY).getColor() != piece.color);
    }
}

