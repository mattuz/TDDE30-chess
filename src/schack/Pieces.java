package schack;

public abstract class Pieces
{
    protected static String color = null;
    protected int pieceX;
    protected int pieceY;
    protected final PieceType type;

    protected Pieces(int x, int y, PieceType type, String color){
        this.color = color;
        this.pieceX = x;
        this.pieceY = y;
        this.type = type;
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
}
