package schack;

public class Rook extends Pieces
{
    private final PieceType type;
    public Rook(final String color) {
	super(color);
	this.type = PieceType.ROOK;
    }

    public PieceType getType() {
	return type;
    }
}
