package schack;

public class Queen extends Pieces
{
    public Queen(final int x, final int y, final PieceType type, final String color) {
	super(x, y, type, color);
    }

    public static String getPath() {
	if(color == "white"){
	    return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\queen_w.png";
	    //return "C:\\Users\\lisac\\IdeaProjects\\Pics\\queen_w.png";
	} else {
	    return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\queen_b.png";
	  //  return "C:\\Users\\lisac\\IdeaProjects\\Pics\\queen_b.png";
	}
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
