package schack;

public class Bishop extends Pieces
{
    private final PieceType type;
    private int posX;
    private int posY;

    public Bishop(final String color, int x, int y) {
	super(color);
	this.posX = x;
	this.posY = y;
	this.type = PieceType.BISHOP;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\bishop_b.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\Pics\\bishop.png";

    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y);
    }
}
