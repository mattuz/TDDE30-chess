package schack;

public class Knight extends Pieces
{
    public Knight(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public String getPath() {
	if(color == "white"){
	    //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\knight_w.png";
	    return "C:\\Users\\lisac\\IdeaProjects\\Pics\\knight_w.png";
	} else {
	    //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\knight_b.png";
	    return "C:\\Users\\lisac\\IdeaProjects\\Pics\\knight_b.png";
	}
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return (Math.abs(getPieceX() - x) == 1 && Math.abs(getPieceY() - y) == 2) ||
	       (Math.abs(getPieceX() - x) == 2 && Math.abs(getPieceY() - y) == 2);
    }
}
