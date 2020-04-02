package schack;

public class Bishop extends Pieces
{
    private final PieceType type;

    public Bishop(final String color) {
	super(color);
	this.type = PieceType.BISHOP;
    }

    public PieceType getType() {
	return type;
    }
}
