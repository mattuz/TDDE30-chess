package schack;

import java.net.URL;

public class King extends Piece
{
    public King(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

//    public static URL getPath() {
//	if(color == "white"){
//	    return ClassLoader.getSystemResource("king_w.png");
//	} else {
//	    return ClassLoader.getSystemResource("king_b.png");
//
//	}
//    }

    public boolean legalMove(int x, int y){
        if (getPieceX() == x && Math.abs(getPieceY() - y) == 1){
            return true;
	}
        else if (getPieceY() == y && Math.abs(getPieceX() - x) == 1){
            return true;
	}
        else return Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 1;
    }
}

