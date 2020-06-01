package schack;

import java.net.URL;
import java.util.List;

public class King extends Piece
{
    public King( int x,  int y, final PieceType type, final String color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }

    public List<Position> addLegalMoves(List<Position> list, Position p) {
        switch (board.castlingPossiblePath(this)) {
            case 0:
                break;
            case 1:
                list.add(new Position(2,pieceY));
                break;
            case 2:
                list.add(new Position(6, pieceY));
                break;
            case 3:
                list.add(new Position(2,pieceY));
                list.add(new Position(6, pieceY));
                break;
        } return list;
    }

    public void updateLegalMoves(){
        //updatePreviousLegalMoves();
        Position position = new Position (getPieceX(), getPieceY());
        legalMoves.clear();
        addLegalMoves(legalMoves, position);
        legalMoves = addHorisontal(legalMoves, 1, position);
        legalMoves = addDiagonal(legalMoves, 1, position);
        legalMoves = addVertical(legalMoves, 1, position);
        /*if (board.isChecked(this)) {
            legalMoves.remove(board.containsPosition(this.getlegalMoves(), position));
        }*/
    }

    public void updatePreviousLegalMoves(){
        previousLegalMoves = legalMoves;
    }

}

