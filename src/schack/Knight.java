package schack;

import java.net.URL;
import java.util.List;

public class Knight extends Piece
{
   // protected static String color = null;

    public Knight( int x,  int y, final PieceType type, final String color, final URL path, final Board board) {
	super(x, y, type, color, path, board);
    }
    /*public boolean isLegal(int prevX, int prevY){
	return ((Math.abs(getPieceX() - prevX) == 1 && Math.abs(getPieceY() - prevY) == 2) ||
		(Math.abs(getPieceX() - prevX) == 2 && Math.abs(getPieceY() - prevY) == 1))
	       && color == board.getState() && isValidDestination();
    }*/
    public List<Position> addLegalMoves(List<Position> list, Position p) {
	if (color == board.getState()) {
	    for (int x = 0; x < 8; x++) {
		for (int y = 0; y < 8; y++) {
		    if ((Math.abs(x - p.getX()) == 1 && Math.abs(y - p.getY()) == 2) ||
			(Math.abs(x - p.getX()) == 2 && Math.abs(y - p.getY()) == 1)) {
			System.out.println(x + ", " + y);
			if (isValidDestination(x,y)/*board.getPieceAt(x,y) == null || board.getPieceAt(x,y).color != board.getState()*/)
			list.add(new Position(x, y));
		    }
		}
	    }
	}
	return list;
    }
    public void updateLegalMoves(){
        legalMoves.clear();
        legalMoves = addLegalMoves(legalMoves, new Position(pieceX, pieceY));
    }

}
