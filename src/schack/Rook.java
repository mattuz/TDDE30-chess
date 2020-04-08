package schack;

public class Rook extends Pieces
{
    private final PieceType type;
    public Rook(final String color) {
	super(color);
	this.type = PieceType.ROOK;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\rook.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\rook.png";
    }

    public PieceType getType() {
	return type;
    }
}
