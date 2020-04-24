package schack;

import java.net.URL;

public class Bishop extends Pieces
{
    public Bishop(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public static URL getPath() {
        if(color == "white"){
	    return ClassLoader.getSystemResource("bishop_w.png");
	} else {
	    return ClassLoader.getSystemResource("bishop_b.png");
	}
    }


    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y);
    }
}
