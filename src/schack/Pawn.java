package schack;

import java.net.URL;

public class Pawn extends Pieces
{
    private boolean firstStep;

    public Pawn(final int x, final int y, final PieceType type, final String color, final URL path) {
        super(x, y, type, color, path);
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

//    public static URL getPath() {
//        if(color == "white"){
//            return ClassLoader.getSystemResource("pawn_w.png");
//
//        } else {
//            return ClassLoader.getSystemResource("pawn_b.png");
//
//        }
//    }

    public boolean legalMove(int x, int y) {
        if (getColor() == "black") {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y - 1 || getPieceY() == y - 2) {
                    return true;
                } else {
                    return (!firstStep && getPieceY() == y - 1);
                }
            } else {
                return Math.abs(getPieceX() - x) == 1 && getPieceY() == y - 1;
            }
        } else if (getColor() == "white") {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y + 1 || getPieceY() == y + 2) {
                    return true;
                } else {
                    return (!firstStep && getPieceY() == y + 1);
                }
            } else {
                return Math.abs(getPieceX() - x) == 1 && getPieceY() == y + 1;
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
