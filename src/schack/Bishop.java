package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
	super(x, y, type, color, path, board, firstStep);
	updateLegalMoves();
    }


    public void updateLegalMoves(){
        legalMoves.clear();
        legalMoves = addDiagonal(legalMoves,8, new Position(getPieceX(),getPieceY()));
    }

    public void updatePreviousLegalMoves(){
	previousLegalMoves = legalMoves;
    }
}

