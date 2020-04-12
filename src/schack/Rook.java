package schack;

public class Rook extends Pieces
{
    private final PieceType type;
    public Rook(final String color) {
	super(color);
	this.type = PieceType.ROOK;
    }

    public static String getPath() {
	//return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\rook_w.png";
	return "C:\\Users\\lisac\\IdeaProjects\\Pics\\rook_w.png";
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return getPieceX() == x || getPieceY() == y;
    }
}
