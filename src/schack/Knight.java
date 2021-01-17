package schack;

import java.net.URL;
import java.util.List;
/**
 * Class managing the Knight piece. Allows for creation of knight pieces and the managing of their movement.
 * Knights have a very specific movement pattern, which is described here.
 */
public class Knight extends Piece
{
    public Knight( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
	super(x, y, type, color, path, board, firstStep);
	updateLegalMoves();
    }

	/**
	 * Adds the moves that are legal for the Knight.
	 */
    public List<Position> addLegalMoves(List<Position> list, Position p) {
		if (color == board.getState()) {
	    	final int minDistance = 1; //Den måste gå minst 1 steg i x- eller y-led
	    	final int maxDistance = 2; //Måste gå max 2 steg i x- eller y-led
	    	for (int x = 0; x < board.getWidth(); x++) {
				for (int y = 0; y < board.getHeight(); y++) {
		    		if ((Math.abs(x - p.getX()) == minDistance && Math.abs(y - p.getY()) == maxDistance) ||
						(Math.abs(x - p.getX()) == maxDistance && Math.abs(y - p.getY()) == minDistance)) {
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
