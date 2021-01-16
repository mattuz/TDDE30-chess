package schack;

import java.net.URL;
import java.util.List;

public class King extends Piece
{
    public King( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }

    public List<Position> addLegalMoves(List<Position> list) {
        int rightCastlingPosition = 6;
        int leftCastlingPosition = 2;
        switch (board.castlingPossiblePath(this)) {
            case "none":
                break;
            case "left":
                list.add(new Position(leftCastlingPosition,pieceY));
                break;
            case "right":
                list.add(new Position(rightCastlingPosition, pieceY));
                break;
            case "both":
                list.add(new Position(leftCastlingPosition,pieceY));
                list.add(new Position(rightCastlingPosition, pieceY));
                break;
        } return list;
    }

    public void updateLegalMoves(){
        int maxDistance = 1;
        Position position = new Position (getPieceX(), getPieceY());
        legalMoves.clear();
        addLegalMoves(legalMoves);
        legalMoves = addHorisontal(legalMoves, maxDistance, position);
        legalMoves = addDiagonal(legalMoves, maxDistance, position);
        legalMoves = addVertical(legalMoves, maxDistance, position);
        /*if (board.isChecked(this)) {
            legalMoves.remove(board.containsPosition(this.getlegalMoves(), position));
        }*/
    }

    public void updatePreviousLegalMoves(){
        previousLegalMoves = legalMoves;
    }

}

