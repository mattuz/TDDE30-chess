package schack;

public class Pawn extends Pieces
{
    private boolean firstStep;

    public Pawn(final String color) {
        super(color);
        this.firstStep = true;
    }
}
