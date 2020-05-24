package schack;

import java.net.URL;

public class Pawn extends Piece
{
    protected static String color = null;
    private static boolean firstStep;

    public Pawn(int x, int y, final PieceType type, final String color, final URL path) {
        super(x, y, type, color, path);
        this.firstStep = true;
    }

    public static boolean isFirstStep() {
        return firstStep;
    }

    public boolean isLegal(int x, int y) {
        if (color == "black" && color == Board.getState()) {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y - 1 || getPieceY() == y - 2) {
                    return true;
                } else {
                    return (!firstStep && getPieceY() == y - 1);
                }
            } else {
                return Math.abs(getPieceX() - x) == 1 && getPieceY() == y - 1;
            }
        } else if (color == "white" && color == Board.getState()) {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y + 1 || getPieceY() == y + 2) {
                    return true;
                } else {
                    return (!firstStep && getPieceY() == y + 1);
                }
            } else {
                return ((Math.abs(getPieceX() - x) == 1 && getPieceY() == y + 1) && color == Board.getState());
            }
        }
        else{
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
