package schack;

public class Knight extends Pieces
{
    private final PieceType type;

    public Knight(final String color) {
	super(color);
	this.type = PieceType.KNIGHT;
    }

    public PieceType getType() {
	return type;
    }
}
