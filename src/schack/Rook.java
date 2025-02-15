package schack;

import java.net.URL;
/**
 * Class managing the Rook piece. Allows for creation of the rook piece and manages its movement.
 * The rook has a simple movement pattern (vertical/horizontal), which is described here.
 */
public class Rook extends Piece
{
    public Rook( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }

    public void updateLegalMoves(){
        final int maxDistance = 8;
        Position position = new Position(getPieceX(), getPieceY());
        legalMoves.clear();
        legalMoves = addHorisontal(legalMoves, maxDistance, position);
        legalMoves = addVertical(legalMoves, maxDistance, position);
    }
}
