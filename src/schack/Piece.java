package schack;

import java.net.URL;

public abstract class Piece
{
    protected static String color = null;
    protected final URL path;
    protected int pieceX;
    protected int pieceY;
    protected final PieceType type;

    protected Piece(int x, int y, PieceType type, String color, URL path){
        Piece.color = color;
        this.pieceX = x;
        this.pieceY = y;
        this.type = type;
        this.path = path;
    }

    public static String getColor() {
	return color;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public void newX(int x) {
        this.pieceX = x;
    }

    public void newY(int y) {
        this.pieceX = y;
    }

    public URL getPath() {
        return path;
    }

    public PieceType getType() {
        return type;
    }
}
