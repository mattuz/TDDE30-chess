package schack;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece
{
    protected PieceColor color;
    protected final URL path;
    protected int pieceX;
    protected int pieceY;
    protected final PieceType type;
    protected final Board board;
    protected List<Position> legalMoves;
    protected boolean firstStep;
    protected List<Position> previousLegalMoves = null;

    protected Piece(int x, int y, PieceType type, PieceColor color, URL path, Board board, Boolean firstStep){
        this.color = color;
        this.pieceX = x;
        this.pieceY = y;
        this.type = type;
        this.path = path;
        this.legalMoves = new ArrayList<>();
        this.firstStep = firstStep;
        this.board = board;
    }

    public void newX(int x) {
        this.pieceX = x;
    }

    public void newY(int y) {
        this.pieceY = y;
    }

    /**
     * Adds all legal horisontal moves, from and within a maxdistance from p, to list.
     */
    public List<Position> addHorisontal(List<Position> list, int maxDistance, Position p) {
        int y = p.getY();
        for (int x = p.getX() - maxDistance; x <= p.getX() + maxDistance; x++) {
            if (x >= 0 && x < 8 && isLegalHorisontal(this, p.getX(), p.getY(), x, y)){
                list.add(new Position(x, y));
            }
        }
        return list;
    }

    /**
     * Adds all legal vertical moves, from and within a maxdistance from position, to list.
     */
    public List<Position> addVertical(List<Position> list, int maxDistance, Position position){
        int x = position.getX();
        for (int y = position.getY() - maxDistance; y <= position.getY() + maxDistance; y++){
            if (y >= 0 && y < 8 && isLegalVertical(this, position.getX(), position.getY(), x, y)) {
                list.add(new Position(x, y));
            }
        }
        return list;
    }

    /**
     * Adds all legal diagonal moves, from and within a maxdistance from position, to list.
     */
    public List<Position> addDiagonal(List<Position> list, int maxDistance, Position position){
        for (int x = position.getX() - maxDistance; x <= position.getX() + maxDistance; x++){
            for (int y = position.getY()-maxDistance; y <= position.getY() + maxDistance; y++){
                if (x >= 0 && x < 8 && y >= 0 && y < 8 &&
                    isLegalDiagonal(this, position.getX(), position.getY(), x, y)) {
                    list.add(new Position(x, y));
                }
            }
        }
        return list;
    }

    /**
     * Checks if a move from (currentx, currenty) to (x,y) is a legal horisontal move for piece.
     */
    public boolean isLegalHorisontal(Piece piece, int currentX, int currentY, int x, int y) {
        if (isValidDestination(x,y) && piece.color == board.getState() &&
            (Math.abs(currentX - x) != 0 && Math.abs(currentY - y) == 0)){

            boolean freePath = true;

            if (currentX < x && y == currentY) {
                for (int i = x - 1; i > currentX; i--) {
                    if (board.getPieceTypeAt(i, y) != PieceType.EMPTY) {
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX > x && y == currentY) {
                for (int i = x + 1; i < currentX; i++) {
                    if (board.getPieceTypeAt(i, y) != PieceType.EMPTY) {
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
                for (int i = y + 1; i < currentY; i++) {
                    if (board.getPieceTypeAt(x, i) != PieceType.EMPTY) {
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentY < y && x == currentX) {
                for (int i = y - 1; i > currentY; i--) {
                    if (board.getPieceTypeAt(x, i) != PieceType.EMPTY) {
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
     */
    public boolean isLegalDiagonal(Piece piece, int currentX, int currentY, int x, int y) {
        if ((Math.abs(currentX - x) - Math.abs(currentY - y) == 0) &&
            isValidDestination(x,y) && piece.color == board.getState()) {
            boolean freePath = true;
            if (currentX < x && currentY < y) {
                for (int i = currentX+1; i < x; i++) {
                    if (board.getPieceTypeAt(i, currentY + i-currentX ) != PieceType.EMPTY) {
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX > x && currentY < y) {
                for (int i = currentX-1; i > x; i--) {
                    if (board.getPieceTypeAt(i, currentY + currentX-i ) != PieceType.EMPTY) {
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX < x && currentY > y) {
                for (int i = currentX+1; i < x; i++) {
                    if (board.getPieceTypeAt(i, currentY - i+currentX ) != PieceType.EMPTY) {
                        freePath = false;
                        break;
                    }
                }
            }
            if (currentX > x && currentY > y) {
                for (int i = currentX-1; i > x; i--) {
                    if (board.getPieceTypeAt(i, currentY - currentX+i ) != PieceType.EMPTY) {
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

    public List<Position> getlegalMoves(){
        return (ArrayList<Position>) legalMoves;
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
