package schack;

import java.net.URL;

public class Rook extends Piece
{
    public Rook(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

//    public static URL getPath() {
//        if(color == "black"){
//	    return ClassLoader.getSystemResource("rook_b.png");
//
//	} else if(color == "white"){
//	    return ClassLoader.getSystemResource("rook_w.png");
//
//	} else return null;
//    }

    public boolean legalMove(int x, int y){
	return getPieceX() == x || getPieceY() == y;
    }
}
