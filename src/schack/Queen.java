package schack;

import java.net.URL;

public class Queen extends Piece
{

    public Queen( int x,  int y, final PieceType type, final String color, final URL path, final Board board, boolean firstStep) {
	super(x, y, type, color, path, board, firstStep);
    }

	public void updateLegalMoves(){
	    Position position = new Position(getPieceX(), getPieceY());
	    legalMoves.clear();
	    legalMoves = addDiagonal(legalMoves, 8, position);
	    legalMoves = addHorisontal(legalMoves, 8, position);
	    legalMoves = addVertical(legalMoves, 8, position);
	}
}
