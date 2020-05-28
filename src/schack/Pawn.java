package schack;

import java.net.URL;

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

    public boolean isLegal(int prevX, int prevY) {
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
    }


}
