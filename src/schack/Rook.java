package schack;

import java.net.URL;

public class Rook extends Piece
{
    public Rook(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }
    public boolean isLegal(int prevX, int prevY){
        boolean freePath = true;
        if (prevX < getPieceX() && getPieceY() == prevY && isValidDestination() && color == Board.getState()) {
	    for (int i = getPieceX()-1; i > prevX; i--) {
		if (Board.getPieceTypeAt(i, getPieceY()) != PieceType.EMPTY) {
		    freePath = false;
		}
	    }
	    return freePath;
	}
        if (prevX > getPieceX() && getPieceY() == prevY && isValidDestination() && color == Board.getState()) {
	    for (int i = getPieceX()+1; i < prevX; i++) {
		if (Board.getPieceTypeAt(i, getPieceY()) != PieceType.EMPTY) {
		    freePath = false;
		}
	    }
	    return freePath;
	}
	if (prevY > getPieceY() && getPieceX() == prevX && isValidDestination() && color == Board.getState()) {
	    for (int i = getPieceY()+1; i < prevY; i++) {
		if (Board.getPieceTypeAt(getPieceX(), i) != PieceType.EMPTY) {
		    freePath = false;
		}
	    }
	    return freePath;
	}
	if (prevY < getPieceY() && getPieceX() == prevX && isValidDestination() && color == Board.getState()) {
	    for (int i = getPieceY()-1; i > prevY; i--) {
		if (Board.getPieceTypeAt(getPieceX(), i) != PieceType.EMPTY) {
		    freePath = false;
		}
	    }
	    return freePath;
	} else {
	    return false;
	}
    }

    public boolean isValidDestination(){
	return (Board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY || Board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }
}
