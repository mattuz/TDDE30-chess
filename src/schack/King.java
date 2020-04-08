package schack;

public class King extends Pieces
{
    private final PieceType type;

    public King(final String color) {
	super(color);
	this.type = PieceType.KING;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\king.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\Pics\\king.png";
    }

    public PieceType getType() {
	return type;
    }
}
