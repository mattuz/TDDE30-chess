package schack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class PieceMove extends MouseAdapter
{
    private List<Pieces> pieces;
    private PieceComponent graphics;
    private Board board;

    private int dragOffsetX;
    private int dragOffsetY;
    private Pieces dragPiece = null;

    public PieceMove(Board board, final PieceComponent graphics) {
	this.pieces = board.piecesList;
	this.graphics = graphics;
	this.board = board;
    }

    @Override public void mouseReleased(final MouseEvent mouseEvent) {
	System.out.println("Mouse released!!");
        this.dragPiece = null;
    }



    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	System.out.println("Mouse dragged!");
	if (this.dragPiece != null){
	    this.dragPiece.newX(mouseEvent.getPoint().x - this.dragOffsetX);
	    this.dragPiece.newY(mouseEvent.getPoint().y - this.dragOffsetY);
	    board.notifyListeners();
	}
    }


    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x;
	int y = mouseEvent.getPoint().y;
	System.out.println("Mouse pressed!");
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Pieces piece = this.pieces.get(i);


	    if (mouseOverPiece(piece, x, y)) {
		this.dragOffsetX = x - piece.getPieceX();
		this.dragOffsetY = y - piece.getPieceY();
		this.dragPiece = piece;
	    }
	}
	if (this.dragPiece != null){
	    this.pieces.remove(this.dragPiece);
	    this.pieces.add(this.dragPiece);
	}
    }

    private boolean mouseOverPiece(Pieces piece, int x, int y) {
        return piece.getPieceX() <= x &&
	       piece.getPieceX() + 100 >= x &&
	       piece.getPieceY() <= y &&
	       piece.getPieceY() + 100 >= y;
    }

}
