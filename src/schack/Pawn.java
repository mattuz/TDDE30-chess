package schack;

import java.net.URL;
import java.util.List;

public class Pawn extends Piece
{

    public Pawn(int x, int y, final PieceType type, final PieceColor color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }



    private List<Position> addLegalMoves(List<Position> list, Position p){
        if (color == board.getState()) {
            if (color == PieceColor.WHITE) {
                int y = p.getY() - 1;
                for (int x = 0; x < 8; x++) {
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
                for (int x = 0; x < 8; x++) {
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
        System.out.println("innan: " + previousLegalMoves);
        previousLegalMoves = getlegalMoves();
        System.out.println("efter: " + previousLegalMoves);
    }

}
