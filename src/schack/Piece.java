package schack;

import java.net.URL;

public abstract class Piece
{
    protected boolean firstStep;
    protected String color;
    protected final URL path;
    protected int pieceX;
    protected int pieceY;
    protected final PieceType type;

    protected Piece(int x, int y, PieceType type, String color, URL path){
        this.color = color;
        this.pieceX = x;
        this.pieceY = y;
        this.type = type;
        this.path = path;
        this.firstStep = true;
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

    public boolean isFirstStep() {
        return firstStep;
    }

    public abstract boolean isLegal(final int prevX, final int prevY);


}
