package schack;

public class Pawn extends Pieces
{
    private boolean firstStep;
    private final PieceType type;
    private int posX;
    private int posY;

    public Pawn(final String color, int x, int y) {
        super(color);
        this.posX = x;
        this.posY = y;
        this.type = PieceType.PAWN;
        this.firstStep = true;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    public static String getPath() {
        return "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\pawn.png";
        //return "C:\\Users\\lisac\\IdeaProjects\\Pics\\pawn.png";
    }

    public PieceType getType() {
        return type;
    }

    public boolean legalMove(int x, int y) {
        if (getColor() == "black") {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y - 1 || getPieceY() == y - 2) {
                    return true;
                } else {
                    return (!firstStep && getPieceY() == y - 1);
                }
            } else {
                return Math.abs(getPieceX() - x) == 1 && getPieceY() == y - 1;
            }
        } else if (getColor() == "white") {
            if (getPieceX() == x) {
                if (firstStep && getPieceY() == y + 1 || getPieceY() == y + 2) {
                    return true;
                } else {
                    return (!firstStep && getPieceY() == y + 1);
                }
            } else {
                return Math.abs(getPieceX() - x) == 1 && getPieceY() == y + 1;
            }
        }
        else{
            return false;
        }
    }

    public boolean rookUpgrade() { //TODO Lägg till riktiga färger här sen. :)
        if (this.color == "white" && pieceY == 0) { //Tänker att vi kollar detta villkor i t.ex component och tar upp en menyval om "true".
            return true;
        }
        else return this.color == "black" && pieceY == 7;
    }

}
