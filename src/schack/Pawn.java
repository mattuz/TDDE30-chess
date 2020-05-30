package schack;

import java.net.URL;
import java.util.List;

public class Pawn extends Piece
{
    public boolean firstStep;

    public Pawn(int x, int y, final PieceType type, final String color, final URL path, final Board board) {
        super(x, y, type, color, path, board);
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    private List<Position> addLegalMoves(List<Position> list, Position p){ //TODO: Implementera firstStep.
        if (color == board.getState() /*&& isValidDestination(this)*/) { //TODO: Tror inte vi kan kolla isValid här..
            if (color == "white") {
                int y = p.getY() - 1;
                for (int x = 0; x < 8; x++) {
                    if ((Math.abs(x - p.getX()) == 1 && board.getPieceAt(x,y) != null && board.getPieceAt(x,y).getColor() != "white") ||
                        (x == p.getX() && board.getPieceAt(x,y) == null)) {
                        System.out.println(x + ", " + y);
                        list.add(new Position(x, y));
                    }
                }
            } else {
                int y = p.getY() + 1;
                for (int x = 0; x < 8; x++) {
                    if ((Math.abs(x - p.getX()) == 1 && board.getPieceAt(x,y) != null && board.getSquare()[x][y].getColor() != "black") ||
                        (x == p.getX() && board.getSquare()[x][y] == null)) {
                        System.out.println(x + ", " + y);

                        list.add(new Position(x, y));
                    }
                }
            }
            }
        return list;
        }



    public void updateLegalMoves(){
        //firstStep = false;  // tror att detta måste kollas här? - Nope, blir knas eftersom detta måste göras innan en pjäs flyttas..
        legalMoves.clear();
        addLegalMoves(legalMoves, new Position(pieceX, pieceY));
        System.out.println(legalMoves);
    }

    /*public boolean isLegal(int prevX, int prevY) {
        if (color == "black" && color == board.getState()) {
            if (getPieceX() == prevX && board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY) {
                if (firstStep) {
                    if (getPieceY() == prevY + 1 || (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY + 1
                                                     && board.getPieceAt(getPieceX(), getPieceY()).getColor() == "white")) {
                        firstStep = false;
                        return true;
                    } else if (getPieceY() == prevY + 2
                               && board.getPieceTypeAt(getPieceX(), getPieceY() - 1) == PieceType.EMPTY) {
                        firstStep = false;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return (getPieceY() == prevY + 1);
                }
            } else return (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY + 1 &&
                        board.getPieceAt(getPieceX(), getPieceY()).getColor() == "white");
        } else if (color == "white" && color == board.getState()) {
            if (getPieceX() == prevX && board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY) {
                if (firstStep) {
                    if (getPieceY() == prevY - 1 || (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY - 1 &&
                                                     board.getPieceAt(getPieceX(), getPieceY()).getColor() == "black")) {
                        firstStep = false;
                        return true;
                    } else if (getPieceY() == prevY - 2
                               && board.getPieceTypeAt(getPieceX(), getPieceY() + 1) == PieceType.EMPTY) {
                        firstStep = false;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return (getPieceY() == prevY - 1);
                }
            } else return (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY - 1 &&
                           board.getPieceAt(getPieceX(), getPieceY()).getColor() == "black");
        }
        return false;
    }*/


}
