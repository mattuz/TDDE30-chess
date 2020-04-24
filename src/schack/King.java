package schack;

public class King extends Pieces
{
    public King(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public static String getPath() {
	if(color == "white"){
	    return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\king_w.png";
	    //return "C:\\Users\\lisac\\IdeaProjects\\Pics\\king_w.png";
	} else {
	    return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\king_b.png";
	    //return "C:\\Users\\lisac\\IdeaProjects\\Pics\\king_b.png";
	}
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

