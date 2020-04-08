package schack;

public abstract class Pieces
{
    protected String color;
    protected int pieceX;
    protected int pieceY;

    protected Pieces(final String color){
        this.color = color;
    }

    public String getColor() {
	return color;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }
}
