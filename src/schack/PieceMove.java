package schack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PieceMove extends MouseAdapter
{
    private List<Piece> pieces;
    private PieceComponent graphics;
    private Board board;
    private static final int WINDOWOFFSET = 30;
    private int oldX;
    private int oldY;


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
	/*if (dragPiece.getPieceX() != oldX && dragPiece.getPieceY() != oldY){

	}*/
	board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] = board.getSquare()[oldX][oldY];

	this.dragPiece = null;
	board.removePiece(oldX,oldY);
        board.notifyListeners();
    }


    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	System.out.println("Mouse dragged!");
	if (this.dragPiece != null){
	    this.dragPiece.newX((mouseEvent.getPoint().x - this.dragOffsetX)/PieceComponent.getBOARDCONSTANT());
	    this.dragPiece.newY((mouseEvent.getPoint().y - this.dragOffsetY)/PieceComponent.getBOARDCONSTANT());
	    System.out.println((mouseEvent.getPoint().x - this.dragOffsetX)/PieceComponent.getBOARDCONSTANT());
	    System.out.println((mouseEvent.getPoint().y - this.dragOffsetY)/PieceComponent.getBOARDCONSTANT());
	   // board.notifyListeners();
	}
    }


    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x;
	int y = mouseEvent.getPoint().y;
	System.out.println("Mouse pressed!");
	//System.out.println(x + " " + y);
	//System.out.println("pieces:" +pieces);
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);


	    if (mouseOverPiece(piece, x, y)) {
		this.dragOffsetX = x - piece.getPieceX() * PieceComponent.getBOARDCONSTANT() - 7;
		this.dragOffsetY = y - piece.getPieceY() * PieceComponent.getBOARDCONSTANT() - WINDOWOFFSET;
		this.oldX = (mouseEvent.getPoint().x - this.dragOffsetX)/PieceComponent.getBOARDCONSTANT();
		this.oldY = (mouseEvent.getPoint().y - this.dragOffsetY)/PieceComponent.getBOARDCONSTANT();
		this.dragPiece = piece;
		//System.out.println(dragPiece);
		//System.out.println(dragOffsetX + " " + dragOffsetY);
	    }
	}
	if (this.dragPiece != null){
	    this.pieces.remove(this.dragPiece);
	    this.pieces.add(this.dragPiece);
	}
    }

    private boolean mouseOverPiece(Piece piece, int x, int y) {
        return piece.getPieceX() * PieceComponent.getBOARDCONSTANT() <= x &&
	       piece.getPieceX() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= x &&//* eller + här?
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() + WINDOWOFFSET <= y &&
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() + WINDOWOFFSET >= y;
    }

}
