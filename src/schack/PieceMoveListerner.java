package schack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PieceMoveListerner extends MouseAdapter
{
    private Pieces pieces;
    private PieceComponent graphics;

    private int dragOffsetX;
    private int dragOffsetY;
    private Pieces dragPiece;

    public PieceMoveListerner(final Pieces pieces, final PieceComponent graphics) {
	this.pieces = pieces;
	this.graphics = graphics;
    }

    @Override public void mouseReleased(final MouseEvent mouseEvent) {

    }

    @Override public void mouseDragged(final MouseEvent mouseEvent) {

    }

    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x;
	int y = mouseEvent.getPoint().y;

	if (mouseOverPiece(pieces,x,y)) {
	    this.dragOffsetX = x - pieces.getPieceX();
	    this.dragOffsetY = y - pieces.getPieceY();
	    this.dragPiece = pieces;
	}

    }

    private boolean mouseOverPiece(Pieces piece, int x, int y) {
        return piece.getPieceX() <= x &&
	       piece.getPieceX() + 100 >= x &&
	       piece.getPieceY() <= y &&
	       piece.getPieceY() + 100 >= y;
    }
}
