package schack;

import java.net.URL;

public class Queen extends Piece
{

    public Queen( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
	super(x, y, type, color, path, board, firstStep);
	updateLegalMoves();
    }

	public void updateLegalMoves(){
        int maxDistance = 8;
	Position position = new Position(getPieceX(), getPieceY());
	legalMoves.clear();
	legalMoves = addDiagonal(legalMoves, maxDistance, position);
	legalMoves = addHorisontal(legalMoves, maxDistance, position);
	legalMoves = addVertical(legalMoves, maxDistance, position);
	}

    public void updatePreviousLegalMoves(){
	previousLegalMoves = legalMoves;
    }
}
