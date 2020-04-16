package schack;

public class Knight extends Pieces
{
    private final PieceType type;
    private int posX;
    private int posY;

    public Knight(final String color, int x, int y) {
	super(color);
	this.posX = x;
	this.posY = y;
	this.type = PieceType.KNIGHT;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\knight_b.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\Pics\\knight.png";
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return (Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 2) ||
	       (Math.abs(getPieceX() - x) == 2 && Math.abs(getPieceY() - y) == 2);
    }
}
