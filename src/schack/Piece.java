package schack;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece
{
    //protected boolean firstStep;
    protected String color;
    protected final URL path;
    protected int pieceX;
    protected int pieceY;
    protected final PieceType type;
    protected final Board board;
    protected List<Position> legalMoves;

    protected Piece(int x, int y, PieceType type, String color, URL path, Board board){
        this.color = color;
        this.pieceX = x;
        this.pieceY = y;
        this.type = type;
        this.path = path;
        this.legalMoves = new ArrayList<>();
        //this.firstStep = true;
        this.board = board;
    }
    public ArrayList<Position> getlegalMoves(){
        return (ArrayList<Position>) legalMoves;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public void newX(int x) {
        this.pieceX = x;
    }

    public void newY(int y) {
        this.pieceY = y;
    }

    public URL getPath() {
        return path;
    }

    public PieceType getType() {
        return type;
    }

    public String getColor(){
        return color;
    }

   // public abstract boolean isLegal(final int prevX, final int prevY);

    /*public boolean isFirstStep() {
        return firstStep;
    }
*/
    public List<Position> addHorisontal(List<Position> list, int maxDistance, Position p) {
        int y = p.getY();
        for (int x = p.getX() - maxDistance; x < p.getX() + maxDistance; x++) {
            if (x >= 0 && x < 8 && isLegalHorisontal(this, p.getX(), p.getY(), x, y)){
                list.add(new Position(x, y));
            }
        }
        return list;
    }

    public List<Position> addVertical(List<Position> list, int maxDistance, Position p){
        int x = p.getX();
        for (int y = p.getY() - maxDistance; y < p.getY() + maxDistance; y++){
            if (y >= 0 && y < 8 && isLegalVertical(this, p.getX(), p.getY(), x, y)) {
                list.add(new Position(x, y));
            }
        }
        return list;
    }
    public List<Position> addDiagonal(List<Position> list, int maxDistance, Position p){
        for (int x = p.getX() - maxDistance; x < p.getX() + maxDistance; x++){
            for (int y = p.getY()-maxDistance; y < p.getY() + maxDistance; y++){
                if (x >= 0 && x < 8 && y >= 0 && y < 8 &&
                    isLegalDiagonal(this, p.getX(), p.getY(), x, y)) {
                    list.add(new Position(x, y));
                }
            }
        }
        return list;
    }

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

    public boolean isValidDestination(int newX, int newY){
        return (board.getPieceAt(newX, newY) == null ||
                board.getPieceAt(newX, newY).color != board.getState());
    }

    public abstract void updateLegalMoves();
}
