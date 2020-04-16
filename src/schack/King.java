package schack;

public class King extends Pieces
{
    private final PieceType type;
    private int posX;
    private int posY;

    public King(final String color, int x, int y) {
	super(color);
	this.posX = x;
	this.posY = y;
	this.type = PieceType.KING;
    }

    public static String getPath() {
	return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\king_b.png";
	//return "C:\\Users\\lisac\\IdeaProjects\\Pics\\king.png";
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
        if (getPieceX() == x && Math.abs(getPieceY() - y) == 1){
            return true;
	}
        else if (getPieceY() == y && Math.abs(getPieceX() - x) == 1){
            return true;
	}
        else return Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 1;
    }
}

