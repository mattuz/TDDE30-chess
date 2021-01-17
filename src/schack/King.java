package schack;

import java.net.URL;
import java.util.List;
/**
 * Class managing the King piece. Allows for creaton of kings and their movement handling.
 * King has a specific legal move which corresponds to the castling-move. This is also
 * represented here.
 */
public class King extends Piece
{
    public King( int x,  int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }

    /**
     * Adds the moves (castling) that are specific for the king.
     */
    public List<Position> addLegalMoves(List<Position> legalMoves) {
        final int rightCastlingPosition = 6;
        final int leftCastlingPosition = 2;
        Position leftCastling = new Position(leftCastlingPosition, pieceY);
        Position rightCastling = new Position(rightCastlingPosition, pieceY);
        switch (board.castlingPossiblePath(this)) { //Vi tyckte inte att enums var nödvändigt för detta då det är en så pass
                                                        //simpel switch. Vi tycker att det är pedagogiskt nog med strängar.
            case "none":
                break;
            case "left":
                legalMoves.add(leftCastling);
                break;
            case "right":
                legalMoves.add(rightCastling);
                break;
            case "both":
                legalMoves.add(leftCastling);
                legalMoves.add(rightCastling);
                break;
        } return legalMoves;
    }

    public void updateLegalMoves(){
        int maxDistance = 1;
        Position position = new Position (getPieceX(), getPieceY());
        legalMoves.clear();
        addLegalMoves(legalMoves);
        legalMoves = addHorisontal(legalMoves, maxDistance, position);
        legalMoves = addDiagonal(legalMoves, maxDistance, position);
        legalMoves = addVertical(legalMoves, maxDistance, position);
    }


}

