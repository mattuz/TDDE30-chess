package schack;

public class Rook extends Pieces
{
    public Rook(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public String getPath() {
        if(color == "black"){
	    //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\rook_w.png";
	    return "C:\\Users\\lisac\\IdeaProjects\\Pics\\rook_b.png";
	} else if(color == "white"){
	    //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\rook_b.png";
	    return "C:\\Users\\lisac\\IdeaProjects\\Pics\\rook_w.png";
	} else return null;
    }

    public PieceType getType() {
	return type;
    }

    public boolean legalMove(int x, int y){
	return getPieceX() == x || getPieceY() == y;
    }
}
