package schack;

import java.net.URL;

public class King extends Piece
{
    public King( int x,  int y, final PieceType type, final String color, final URL path, final Board board) {
	super(x, y, type, color, path, board);
    }
    public boolean isLegal(int prevX, int prevY){
        if (color == board.getState() && isValidDestination()) {
	    if ((getPieceX() == prevX && Math.abs(getPieceY() - prevY) == 1)){
		return true;
	    }
	    else if ((getPieceY() == prevY && Math.abs(getPieceX() - prevX) == 1)){
		return true;
	    }
	    else if(board.isCastlingPossible()){
		return true;
	    }
	    else return Math.abs(getPieceX() - prevX) == 1 && Math.abs(getPieceY() - prevY) == 1;
	} return false;
    }

    public boolean isValidDestination(){
	return (board.getPieceTypeAt(getPieceX(), getPieceY()) == PieceType.EMPTY ||
		board.getPieceAt(getPieceX(), getPieceY()).getColor() != color);
    }


}

