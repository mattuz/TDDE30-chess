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

    private List<Position> addLegalMoves(List<Position> list, Position p){
        if (color == board.getState() && isValidDestination(this)) {
            if (color == "white") {
                int x;
                if (Math.abs(x - p.getX()) == 1) { // Såhär kan man ju inte göra..

            }                   //TODO Här måste vi ha en loop också tror jag, annars kan vi inte kolla x till höger/vänster.
                int y = p.getY() - 1;
                list.add(new Position(x, y));
            }
            }
        }



    public void updateLegalMoves(){
        Position position = new Position(getPieceX(), getPieceY());
        legalMoves = addLegalMoves(legalMoves, new Position(pieceX, pieceY));
        legalMoves = addVertical(legalMoves, 1, position); //Frågan är om man kan implementera detta.
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
