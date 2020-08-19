package schack;

import java.net.URL;

public class Rook extends Piece
{
    public Rook( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }

    public void updateLegalMoves(){
        //updatePreviousLegalMoves();
        Position position = new Position(getPieceX(), getPieceY());
        legalMoves.clear();
        legalMoves = addHorisontal(legalMoves, 8, position);
        legalMoves = addVertical(legalMoves, 8, position);
    }

    public void updatePreviousLegalMoves(){
        previousLegalMoves = legalMoves;
    }

}
