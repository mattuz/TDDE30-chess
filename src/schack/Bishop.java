package schack;

import java.net.URL;

public class Bishop extends Pieces
{
    public Bishop(int x, int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

//    public static URL getPath() {
//        if(color == "white"){
//	    return ClassLoader.getSystemResource("bishop_w.png");
//	} else {
//	    return ClassLoader.getSystemResource("bishop_b.png");
//	}
//    }


    public boolean legalMove(int x, int y){
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y);
    }
}
