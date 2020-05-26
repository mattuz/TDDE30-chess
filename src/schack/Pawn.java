package schack;

import java.net.URL;

public class Pawn extends Piece
{
    public boolean firstStep;

    public Pawn(int x, int y, final PieceType type, final String color, final URL path) {
        super(x, y, type, color, path);
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    public boolean isLegal(int prevX, int prevY) {
        if (color == "black" && color == Board.getState()) {
            if (getPieceX() == prevX && Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY) {
                if (firstStep) {
                    if (getPieceY() == prevY + 1 || (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY + 1
                                                     && Board.getPieceAt(getPieceX(), getPieceY()).getColor() == "white")) {
                        firstStep = false;
                        return true;
                    } else if (getPieceY() == prevY + 2
                               && Board.getPieceTypeAt(getPieceX(), getPieceY() - 1) == PieceType.EMPTY) {
                        firstStep = false;
                        return true;
                    } else {
                        return false;
                    }
                } else if (!firstStep) {
                    return (getPieceY() == prevY + 1);
                }
            } else return (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY + 1 &&
                        Board.getPieceAt(getPieceX(), getPieceY()).getColor() == "white");
        } else if (color == "white" && color == Board.getState()) {
            if (getPieceX() == prevX && Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY) {
                if (firstStep) {
                    if (getPieceY() == prevY - 1 || (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY - 1 &&
                                                     Board.getPieceAt(getPieceX(), getPieceY()).getColor() == "black")) {
                        firstStep = false;
                        return true;
                    } else if (getPieceY() == prevY - 2
                               && Board.getPieceTypeAt(getPieceX(), getPieceY() + 1) == PieceType.EMPTY) {
                        firstStep = false;
                        return true;
                    } else {
                        return false;
                    }
                } else if (!firstStep) {
                    return (getPieceY() == prevY - 1);
                }
            } else return (Math.abs(getPieceX() - prevX) == 1 && getPieceY() == prevY - 1 &&
                           Board.getPieceAt(getPieceX(), getPieceY()).getColor() == "black");
        }
        return false;
    }

    public static boolean pawnUpgradePossible() {
        if (color == "white" && pieceY == 0) { //Tänker att vi kollar detta villkor i t.ex component och tar upp en menyval om "true".
            return true;
        }
        else return color == "black" && pieceY == 7;
    }

}
