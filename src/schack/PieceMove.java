package schack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PieceMove extends MouseAdapter
{
    private List<Piece> pieces;
    private PieceComponent graphics;
    private Board board;

    private int dragOffsetX;
    private int dragOffsetY;
    private Piece dragPiece = null;

    public PieceMove(Board board, final PieceComponent graphics) {
	this.pieces = board.pieceList;
	this.graphics = graphics;
	this.board = board;
	this.pieces = board.addPieces();
	System.out.println("PieceMove activated!");
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
	System.out.println(x + " " + y);
	//System.out.println("pieces:" +pieces);
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);


	    if (mouseOverPiece(piece, x, y)) {
		//System.out.println(piece);
		this.dragOffsetX = x - piece.getPieceX();
		this.dragOffsetY = y - piece.getPieceY();
		this.dragPiece = piece;
		//System.out.println(dragPiece);
	    }
	}
	if (this.dragPiece != null){
	    this.pieces.remove(this.dragPiece);
	    this.pieces.add(this.dragPiece);
	}
    }

    private boolean mouseOverPiece(Piece piece, int x, int y) {
	//System.out.println(piece);
	System.out.println("P_X: " + piece.getPieceX() + ", P_Y: " + piece.getPieceY());
	System.out.println(piece.getPieceX() * PieceComponent.getBOARDCONSTANT() <= x &&
			   piece.getPieceX() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= x && //* eller + här?
			   piece.getPieceY() * PieceComponent.getBOARDCONSTANT() <= y &&
			   piece.getPieceY() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= y);
        return piece.getPieceX() * PieceComponent.getBOARDCONSTANT() <= x &&
	       piece.getPieceX() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= x && //* eller + här?
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() <= y &&
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= y;
    }

}
