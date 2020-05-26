package schack;

import java.net.URL;

public class Bishop extends Piece
{
    public Bishop(int x, int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

    public boolean istLegal(int prevX, int prevY){
	return Math.abs(getPieceX() - prevX) == Math.abs(getPieceY() - prevY) && color == Board.getState();
    }

    public boolean isLegal(int prevX, int prevY){
	boolean freePath = true;
	if (prevX < getPieceX() && prevY < getPieceY() && isValidDestination() && color == Board.getState()) {
	    for (int i = prevX; i < getPieceX(); i++) {
	        if (Board.getPieceTypeAt(i, prevY + i-prevX ) != PieceType.EMPTY) {
	            freePath = false;
	        }
	    }
	    return freePath;
	}
	if (prevX > getPieceX() && prevY < getPieceY() && isValidDestination() && color == Board.getState()) {
	    for (int i = prevX; i > getPieceX(); i--) {
	        if (Board.getPieceTypeAt(i, prevY + prevX-i ) != PieceType.EMPTY) {
	            freePath = false;
		}
	    }
	    return freePath;
	}
	if (prevX < getPieceX() && prevY > getPieceY()&& isValidDestination() && color == Board.getState()) {
	    for (int i = prevX; i < getPieceX(); i++) {
	        if (Board.getPieceTypeAt(i, prevY - i+prevX ) != PieceType.EMPTY) {
	            freePath = false;
		}
	    }
	    return freePath;
	}
	if (prevX > getPieceX() && prevY > getPieceY() && isValidDestination() && color == Board.getState()) {
	    for (int i = prevX; i > getPieceX(); i--) {
	        if (Board.getPieceTypeAt(i, prevY - prevX+i ) != PieceType.EMPTY) {
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
