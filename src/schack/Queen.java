package schack;

public class Queen extends Pieces
{
    private final PieceType type;
    public Queen(final String color) {
	super(color);
	this.type = PieceType.QUEEN;
    }

    public static String getPath() {
	//return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\queen.png";
	return "C:\\Users\\lisac\\IdeaProjects\\Pics\\queen.png";
    }

    public PieceType getType() {
	return type;
    }

    public boolean leagalMove(int x, int y){
	if (getPieceX() == x || getPieceY() == y){
	    return true;
	}
	if (Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y)){
	    return true;
	}
	else {
	    return false;
	}
    }
}
