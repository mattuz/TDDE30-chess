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

    public static String getPath() {
        return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\pawn.png";
        //return "C:\\Users\\lisac\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\pawn.png";
    }

    public PieceType getType() {
        return type;
    }
}
