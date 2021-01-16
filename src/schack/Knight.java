package schack;

import java.net.URL;
import java.util.List;

public class Knight extends Piece
{
    public Knight( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
	super(x, y, type, color, path, board, firstStep);
	updateLegalMoves();
    }

    public List<Position> addLegalMoves(List<Position> list, Position p) {
	if (color == board.getState()) {
	    for (int x = 0; x < board.getWidth(); x++) {
		for (int y = 0; y < board.getHeight(); y++) {
		    if ((Math.abs(x - p.getX()) == 1 && Math.abs(y - p.getY()) == 2) ||
			(Math.abs(x - p.getX()) == 2 && Math.abs(y - p.getY()) == 1)) {
			if (isValidDestination(x,y))
			list.add(new Position(x, y));
		    }
		}
	    }
	}
	return list;
    }
    public void updateLegalMoves(){
        legalMoves.clear();
        legalMoves = addLegalMoves(legalMoves, new Position(pieceX, pieceY));
    }
    public void updatePreviousLegalMoves(){
	previousLegalMoves = legalMoves;
    }
}
