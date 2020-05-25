package schack;

import java.net.URL;

public class Pawn extends Piece
{
    //protected static String color = null;
    public boolean firstStep;

    public Pawn(int x, int y, final PieceType type, final String color, final URL path) {
        super(x, y, type, color, path);
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    public boolean isLegal(int prevX, int prevY) {
       // System.out.println(Board.getState());
        if (color == "black" && color == Board.getState()) {
            if (getPieceX() == prevX) {
                if (firstStep && (getPieceY() == prevY + 1 ||
                                  (getPieceY() == prevY + 2) &&
                                  Board.getPieceTypeAt(getPieceX(), getPieceY()-1) == PieceType.EMPTY)) {
                    System.out.println("black firststep = " + firstStep);
                    firstStep = false;
                    return true;
                } else {
                    System.out.println("black firststep = " + firstStep);
                    firstStep = false;
                    return (getPieceY() == prevY + 1);
                }
            } else {
                System.out.println("black firststep = " + firstStep);
                firstStep = false;
                return Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY + 1 &&
                       Board.getPieceAt(getPieceX(), getPieceY()).getColor() == "white";
            }
        } else if (color == "white" && color == Board.getState()) {
            if (getPieceX() == prevX) {
                if (firstStep && (getPieceY() == prevY - 1 || (getPieceY() == prevY - 2)) &&
                    Board.getPieceTypeAt(getPieceX(), getPieceY()+1) == PieceType.EMPTY &&
                    Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY) {
                    System.out.println("White firststep = " + firstStep);
                    firstStep = false;
                    return true;
                } else {
                    System.out.println("White firststep = " + firstStep);
                    System.out.println("KEKW");
                    firstStep = false;
                    return (getPieceY() == prevY - 1);
                }
            } else {
                System.out.println("White firststep = " + firstStep);
                firstStep = false;
                return (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY - 1 &&
                        Board.getPieceAt(getPieceX(), getPieceY()).getColor() == "black");
            }
        } else {
            return false;
        }
    }

    public boolean rookUpgrade() {
        if (color == "white" && pieceY == 0) { //Tänker att vi kollar detta villkor i t.ex component och tar upp en menyval om "true".
            return true;
        }
        else return color == "black" && pieceY == 7;
    }

}
