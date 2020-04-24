package schack;

import java.net.URL;

public class Rook extends Pieces
{
    public Rook(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public static URL getPath() {
        if(color == "black"){
	    return ClassLoader.getSystemResource("rook_b.png");

	} else if(color == "white"){
	    return ClassLoader.getSystemResource("rook_w.png");

	} else return null;
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return getPieceX() == x || getPieceY() == y;
    }
}
