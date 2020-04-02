package schack;

public class Pawn extends Pieces
{
    private boolean firstStep;
    private final PieceType type;

    public Pawn(final String color) {
        super(color);
        this.type = PieceType.PAWN;
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    public PieceType getType() {
        return type;
    }
}
