package schack;

import java.net.URL;

public class Knight extends Piece
{
    public Knight(final int x, final int y, final PieceType type, final String color, final URL path) {
	super(x, y, type, color, path);
    }

//    public static URL getPath() {
//	if(color == "white"){
//	    return ClassLoader.getSystemResource("knight_w.png");
//
//	} else {
//	    return ClassLoader.getSystemResource("knight_b.png");
//
//	}
//    }

    public boolean isLegal(int x, int y){
	return (Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 2) ||
	       (Math.abs(getPieceX() - x) == 2 && Math.abs(getPieceY() - y) == 2);
    }
}
