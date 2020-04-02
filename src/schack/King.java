package schack;

public class King extends Pieces
{
    private final PieceType type;

    public King(final String color) {
	super(color);
	this.type = PieceType.KING;
    }

    public PieceType getType() {
	return type;
    }
}
