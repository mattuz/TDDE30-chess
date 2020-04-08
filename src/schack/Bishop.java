package schack;

public class Bishop extends Pieces
{
    private final PieceType type;

    public Bishop(final String color) {
	super(color);
	this.type = PieceType.BISHOP;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\bishop.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\bishop.png";
    }

    public PieceType getType() {
	return type;
    }
}
