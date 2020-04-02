package schack;

public class Queen extends Pieces
{
    private final PieceType type;
    public Queen(final String color) {
	super(color);
	this.type = PieceType.QUEEN;
    }

    public PieceType getType() {
	return type;
    }
}
