package schack;

import java.net.URL;

public class King extends Piece
{
    public King( int x,  int y, final PieceType type, final String color, final URL path, final Board board, boolean firstStep) {
        super(x, y, type, color, path, board, firstStep);
    }
    /*public boolean isLegal(int prevX, int prevY){
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
    } */

    public void updateLegalMoves(){
        Position position = new Position (getPieceX(), getPieceY());
        legalMoves.clear();
        legalMoves = addHorisontal(legalMoves, 1, position);
        legalMoves = addDiagonal(legalMoves, 1, position);
        legalMoves = addVertical(legalMoves, 1, position);
    }



}

