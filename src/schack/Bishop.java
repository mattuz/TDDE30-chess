package schack;

public class Bishop extends Pieces
{
    public Bishop(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public static String getPath() {
        if(color == "white"){
	    //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\bishop_w.png";
	    return "C:\\Users\\lisac\\IdeaProjects\\Pics\\bishop_w.png";
	} else {
	    //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\bishop_b.png";
	    return "C:\\Users\\lisac\\IdeaProjects\\Pics\\bishop_b.png";
	}
    }


    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return Math.abs(getPieceX() - x) == Math.abs(getPieceY() - y);
    }
}
