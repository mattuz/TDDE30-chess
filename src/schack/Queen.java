package schack;

import java.net.URL;

public class Queen extends Pieces
{
    public Queen(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public static URL getPath() {
	if(color == "white"){
	    return ClassLoader.getSystemResource("queen_w.png");

	} else {
	    return ClassLoader.getSystemResource("queen_b.png");

	}
    }


    public PieceType getType() {
	return type;
    }

    public boolean leagalMove(int x, int y){
	if (getPieceX() == x || getPieceY() == y){
	    return true;
	}
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y);
    }
}
