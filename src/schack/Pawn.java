package schack;

import java.net.URL;
import java.util.List;

public class Pawn extends Piece
{

    public Pawn(int x, int y, final PieceType type, final String color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
        updateLegalMoves();
    }



    private List<Position> addLegalMoves(List<Position> list, Position p){
        if (color == board.getState()) {
            if (color == "white") {
                int y = p.getY() - 1;
                for (int x = 0; x < 8; x++) {
                    if ((Math.abs(x - p.getX()) == 1 && board.getPieceAt(x,y) != null &&
                         board.getPieceAt(x,y).getColor() != "white") ||
                        (x == p.getX() && board.getPieceAt(x,y) == null)) {
                        System.out.println(x + ", " + y);
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
                         board.getSquare()[x][y].getColor() != "black") ||
                        (x == p.getX() && board.getSquare()[x][y] == null)) {
                        System.out.println(x + ", " + y);
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




}
