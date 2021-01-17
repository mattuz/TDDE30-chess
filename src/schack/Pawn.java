package schack;

import java.net.URL;
import java.util.List;
/**
 * Class managing the Pawn piece. Allows for creation of pawns and manages their movement.
 * Pawns has a specific set of legal moves, and it changes depending on if a piece can
 * be taken or not. This is managed here.
 */
public class Pawn extends Piece
{

    public Pawn(int x, int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }

    /**
     * Adds the moves that are legal for the Pawn.
     */
    private List<Position> addLegalMoves(List<Position> list, Position p){
        if (color == board.getState()) {
            if (color == PieceColor.WHITE) {
                int y = p.getY() - 1;
                for (int x = 0; x < board.getWidth(); x++) {
                    if ((Math.abs(x - p.getX()) == 1 && board.getPieceAt(x,y) != null &&
                         board.getPieceAt(x,y).getColor() != PieceColor.WHITE) ||
                        (x == p.getX() && board.getPieceAt(x,y) == null)) {
                        if (firstStep && board.getPieceAt(x,y-1) == null) {
                            list.add(new Position(x, y-1));
                        }
                        list.add(new Position(x, y));
                    }
                }
            } else {
                int y = p.getY() + 1;
                for (int x = 0; x < board.getWidth(); x++) {
                    if ((Math.abs(x - p.getX()) == 1 && board.getPieceAt(x,y) != null &&
                         board.getSquare()[x][y].getColor() != PieceColor.BLACK) ||
                        (x == p.getX() && board.getSquare()[x][y] == null)) {
                        if (firstStep && board.getPieceAt(x,y+1) == null) {
                            list.add(new Position(x,y+1));
                        }
                        list.add(new Position(x, y));
                    }
                }
            }
            }
        return list;
        }

    public void updateLegalMoves(){
        legalMoves.clear();
        addLegalMoves(legalMoves, new Position(pieceX, pieceY));
     }

    public void updatePreviousLegalMoves(){
        previousLegalMoves = getLegalMoves();
    }

}
