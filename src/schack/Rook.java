package schack;

public class Rook extends Pieces
{
    private final PieceType type;
    private int posX;
    private int posY;

    public Rook(final String color, int x, int y) {
	super(color);
	this.posX = x;
	this.posY = y;
	this.type = PieceType.ROOK;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\rook_b.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\Pics\\rook_w.png";
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return getPieceX() == x || getPieceY() == y;
    }
}
