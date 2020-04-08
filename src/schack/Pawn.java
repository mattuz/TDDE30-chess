package schack;

public class Pawn extends Pieces
{
    private boolean firstStep;
    private final PieceType type;

    public Pawn(final String color) {
        super(color);
        this.type = PieceType.PAWN;
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    public static String getPath() {
        //return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\pawn.png";
        return "C:\\Users\\lisac\\IdeaProjects\\Pics\\pawn.png";
    }

    public PieceType getType() {
        return type;
    }

    public boolean leagalMove(int x, int y){
        if (getColor() == "black") {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y - 1 || getPieceY() == y - 2) {
                    return true;
                }
                else if (!firstStep && getPieceY() == y - 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (getPieceX() == x + 1 || getPieceX() == x - 1) {
                if (getPieceY() == y - 1){
                    return true;}
                else {
                    return false;
                }
            }
            else {
                return false;}
        }
        else if (getColor() == "white") {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y + 1 || getPieceY() == y + 2) {
                    return true;
                }
                else if (!firstStep && getPieceY() == y + 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (getPieceX() == x + 1 || getPieceX() == x - 1) {
                if (getPieceY() == y + 1){
                    return true;}
                else {
                    return false;
                }
            }
            else {
                return false;}
        }
        else {
            return false;}
    }
}
