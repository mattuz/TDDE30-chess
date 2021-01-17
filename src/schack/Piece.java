package schack;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass to all the Piece classes (King, Knight, Queen, Bishop, Pawn, Rook).
 */
public abstract class Piece
{
    protected PieceColor color;
    protected final URL path;
    protected int pieceX; //Dessa två har liknande namn, svårt att komma undan
    protected int pieceY; //då vi vill representera både pjäsernas x- och y.
    protected final PieceType type;
    protected final Board board;
    protected List<Position> legalMoves;
    protected boolean firstStep;
    protected List<Position> previousLegalMoves = null;

    protected Piece(int x, int y, PieceType type, PieceColor color, URL path, Board board, boolean firstStep){
        this.color = color;
        this.pieceX = x;
        this.pieceY = y;
        this.type = type;
        this.path = path;
        this.legalMoves = new ArrayList<>();
        this.firstStep = firstStep;
        this.board = board;
    }

    /**
     * Sets a new x value.
     */
    public void setPieceX(int x) { //Dessa två är liknande pga pieceX lik pieceY.
        this.pieceX = x;
    }
    /**
     * Sets a new y value.
     */
    public void setPieceY(int y) {
        this.pieceY = y;
    }

    /**
     * Adds all legal horisontal moves, from and within a maxdistance from p, to legalMoves.
     */
    public List<Position> addHorisontal(List<Position> legalMoves, int maxDistance, Position p) {
        int width = board.getWidth();
        int y = p.getY();
        for (int x = p.getX() - maxDistance; x <= p.getX() + maxDistance; x++) {
            if (x >= 0 && x < width && isLegalHorisontal(this, p.getX(), p.getY(), x, y)){
                legalMoves.add(new Position(x, y));
            }
        }
        return legalMoves;
    }

    /**
     * Adds all legal vertical moves, from and within a maxdistance from position, to legalMoves.
     */
    public List<Position> addVertical(List<Position> legalMoves, int maxDistance, Position position){
        int height = board.getHeight();
        int x = position.getX();
        for (int y = position.getY() - maxDistance; y <= position.getY() + maxDistance; y++){
            if (y >= 0 && y < height && isLegalVertical(this, position.getX(), position.getY(), x, y)) {
                legalMoves.add(new Position(x, y));
            }
        }
        return legalMoves;
    }

    /**
     * Adds all legal diagonal moves, from and within a maxdistance from position, to legalMoves.
     */
    public List<Position> addDiagonal(List<Position> legalMoves, int maxDistance, Position position){
        int width = board.getWidth();
        int height = board.getHeight();

        for (int x = position.getX() - maxDistance; x <= position.getX() + maxDistance; x++){
            for (int y = position.getY()-maxDistance; y <= position.getY() + maxDistance; y++){
                if (x >= 0 && x < width && y >= 0 && y < height &&
                    isLegalDiagonal(this, position.getX(), position.getY(), x, y)) {
                    legalMoves.add(new Position(x, y));
                }
            }
        }
        return legalMoves;
    }

    /**
     * Checks if a move from (currentx, currenty) to (x,y) is a legal horisontal move for piece.
     */
    public boolean isLegalHorisontal(Piece piece, int currentX, int currentY, int x, int y) {
        if (isValidDestination(x,y) && piece.color == board.getState() &&
            (Math.abs(currentX - x) != 0 && Math.abs(currentY - y) == 0)){

            boolean freePath = true;

            if (currentX < x && y == currentY) { //Är "similar" för att de kollar x resp. y. Men det är ju olika axlar,
                                                //så inte särskilt lika egentligen.
                for (int i = x - 1; i > currentX; i--) {
                    if (board.getPieceTypeAt(i, y) != PieceType.EMPTY) { //Vill bara kolla om det går att flytta om det är en spelpjäs.
                        if (board.getPieceTypeAt(i, y) == PieceType.KING && board.getPieceAt(i, y).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX > x && y == currentY) {
                for (int i = x + 1; i < currentX; i++) {
                    if (board.getPieceTypeAt(i, y) != PieceType.EMPTY) { //Samma som ovan
                        if (board.getPieceTypeAt(i, y) == PieceType.KING && board.getPieceAt(i, y).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }	return freePath;

        } else {
            return false;
        }
    }

    /**
     * Checks if a move from (currentx, currenty) to (x,y) is a legal vertical move for piece.
     */
    public boolean isLegalVertical(Piece piece, int currentX, int currentY, int x, int y) {
        if (isValidDestination(x,y) && piece.color == board.getState() &&
             (Math.abs(currentX - x) == 0 && Math.abs(currentY - y) != 0)) {
            boolean freePath = true;

            if (currentY > y && x == currentX) {
                for (int i = y + 1; i < currentY; i++) { //Samma som i tidigare funktionen (errormässigt).
                    if (board.getPieceTypeAt(x, i) != PieceType.EMPTY) {
                        if (board.getPieceTypeAt(x, i) == PieceType.KING && board.getPieceAt(x, i).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentY < y && x == currentX) {
                for (int i = y - 1; i > currentY; i--) {
                    if (board.getPieceTypeAt(x, i) != PieceType.EMPTY) {
                        if (board.getPieceTypeAt(x, i) == PieceType.KING && board.getPieceAt(x, i).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }	return freePath;

        } else {
            return false;
        }
    }

    /**
     * Checks if a move from (currentx, currenty) to (x,y) is a legal diagonal move for piece.
     * Each if statement checks a different diagonal and therefore has different conditions that
     * has to be met by x and y. So the expressions are naturally very similar, but can't
     * really be broken out in to variables.
     */
    public boolean isLegalDiagonal(Piece piece, int currentX, int currentY, int x, int y) { //Försökte lösa komplexiteten
                                                                                //genom att bryta ut i mindre funktioner. Detta
                                                                                //gick inte så vi får behålla det som det är.
        if ((Math.abs(currentX - x) - Math.abs(currentY - y) == 0) &&
            isValidDestination(x,y) && piece.color == board.getState()) {
            boolean freePath = true;
            if (currentX < x && currentY < y) {
                for (int i = currentX+1; i < x; i++) {
                    if (board.getPieceTypeAt(i, currentY + i-currentX ) != PieceType.EMPTY) {
                        if (board.getPieceTypeAt(i, currentY + i-currentX) == PieceType.KING &&
                            board.getPieceAt(i, currentY + i-currentX).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX > x && currentY < y) {
                for (int i = currentX-1; i > x; i--) {
                    if (board.getPieceTypeAt(i, currentY + currentX-i ) != PieceType.EMPTY) {
                        if (board.getPieceTypeAt(i, currentY + currentX-i) == PieceType.KING &&
                            board.getPieceAt(i, currentY + currentX-i).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX < x && currentY > y) {
                for (int i = currentX+1; i < x; i++) {
                    if (board.getPieceTypeAt(i, currentY - i+currentX ) != PieceType.EMPTY) {
                        if (board.getPieceTypeAt(i, currentY - i+currentX) == PieceType.KING &&
                            board.getPieceAt(i, currentY - i+currentX).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX > x && currentY > y) {
                for (int i = currentX-1; i > x; i--) {
                    if (board.getPieceTypeAt(i, currentY - currentX+i ) != PieceType.EMPTY) {
                        if (board.getPieceTypeAt(i, currentY - currentX+i) == PieceType.KING &&
                            board.getPieceAt(i, currentY - currentX+i).color != board.getState()) {
                            break;
                        }
                        freePath = false;
                        break;
                    }
                }
            } return freePath;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the position (newX, newY) is ether empty or has a piece with a different color on it.
     */
    public boolean isValidDestination(int newX, int newY){
        return (board.getPieceAt(newX, newY) == null ||
                board.getPieceAt(newX, newY).color != board.getState());
    }

    /**
     * Updates the lists of the pieces' legal moves
     */
    public abstract void updateLegalMoves();

    public URL getPath() {
        return path;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor(){
        return color;
    }

    public List<Position> getPreviousLegalMoves() {
        return previousLegalMoves;
    }

    public List<Position> getLegalMoves(){
        return legalMoves;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public boolean isFirstStep() {
        return firstStep;
    }

    public void setFirstStep(final boolean firstStep) {
        this.firstStep = firstStep;
    }

    public void setPreviousLegalMoves(final List<Position> previousLegalMoves) {
        this.previousLegalMoves = previousLegalMoves;
    }

    public void setLegalMoves(final List<Position> legalMoves) {
        this.legalMoves = legalMoves;
    }
}
