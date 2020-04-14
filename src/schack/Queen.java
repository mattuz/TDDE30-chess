package schack;

public class Queen extends Pieces
{
    private final PieceType type;
    private int posX;
    private int posY;

    public Queen(final String color, int x, int y) {
	super(color);
	this.posX = x;
	this.posY = y;
	this.type = PieceType.QUEEN;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\queen.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\Pics\\queen.png";
    }

    public PieceType getType() {
	return type;
    }

    public boolean leagalMove(int x, int y){
	if (getPieceX() == x || getPieceY() == y){
	    return true;
	}
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y);
    }
}
