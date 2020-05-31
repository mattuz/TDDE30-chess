package schack;

import java.net.URL;

public class King extends Piece
{
    public King( int x,  int y, final PieceType type, final String color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
    }

    public void addLegalMoves() {
        if (board.isCastlingPossible(this)) {

        }
    }

    public void updateLegalMoves(){
        Position position = new Position (getPieceX(), getPieceY());
        legalMoves.clear();
        legalMoves = addHorisontal(legalMoves, 2, position);
        legalMoves = addDiagonal(legalMoves, 2, position);
        legalMoves = addVertical(legalMoves, 2, position);
    }



}

