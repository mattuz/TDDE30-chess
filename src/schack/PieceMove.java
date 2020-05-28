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
    private static final int XOFFSET = 7;
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
	//System.out.println("PieceMove activated!");
    }

    @Override public void mouseReleased(final MouseEvent mouseEvent) {
        if (isCastlingPossible(oldX, oldY) && dragPiece.getPieceX() == 2){
            if (dragPiece.getColor() == "black" && dragPiece.getPieceY() == 7) {
		board.getSquare()[2][7] = board.getSquare()[0][7];
		board.removePiece(0,7);
	    }
            else if(dragPiece.getColor() == "white" && dragPiece.getPieceY() == 0){
		board.getSquare()[2][0] = board.getSquare()[0][0];
		board.removePiece(0,0);
	    }
            board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] = board.getSquare()[oldX][oldY];
            board.removePiece(oldX, oldY);
        }
        else if (dragPiece.isLegal(oldX,oldY)) {
            board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] = board.getSquare()[oldX][oldY];
            if (dragPiece.getPieceX() != oldX || dragPiece.getPieceY() != oldY) {
                board.removePiece(oldX, oldY);
            }
            else if (dragPiece.getType() == PieceType.PAWN && pawnUpgradePossible(dragPiece.getPieceY())){
                //gör ett meny val
	    }
	} else {
	    dragPiece.newX(oldX);
	    dragPiece.newY(oldY);
	    Board.changeState();
	} this.dragPiece = null;
        board.notifyListeners();
        Board.changeState();
    }

    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	//System.out.println("Mouse dragged!");
	if (this.dragPiece != null){
	    this.dragPiece.newX((mouseEvent.getPoint().x - 7)/PieceComponent.getBOARDCONSTANT());
	    this.dragPiece.newY((mouseEvent.getPoint().y - WINDOWOFFSET)/PieceComponent.getBOARDCONSTANT());
	   // System.out.println((mouseEvent.getPoint().x));
	   // System.out.println((mouseEvent.getPoint().y ));
	   // board.notifyListeners();
	}
    }

    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x - XOFFSET;
	int y = mouseEvent.getPoint().y - WINDOWOFFSET; //Båda dessa konstanter pga x = 7, y = 30 i början av board
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);

	    if (mouseOverPiece(piece, x, y)) {
		this.dragOffsetX = x - piece.getPieceX() * PieceComponent.getBOARDCONSTANT();
		this.dragOffsetY = y - piece.getPieceY() * PieceComponent.getBOARDCONSTANT();
		this.oldX = (x - this.dragOffsetX)/PieceComponent.getBOARDCONSTANT();
		this.oldY = (y - this.dragOffsetY)/PieceComponent.getBOARDCONSTANT();
		this.dragPiece = piece;
		System.out.println(dragPiece.getType());
	    }
	}
	if (this.dragPiece != null){
	    this.pieces.remove(this.dragPiece);
	    this.pieces.add(this.dragPiece);
	}
    }

    private boolean mouseOverPiece(Piece piece, int x, int y) {
        return piece.getPieceX() * PieceComponent.getBOARDCONSTANT()  <= x &&
	       piece.getPieceX() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= x &&
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() <= y &&
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= y;
    }

    public static boolean isCastlingPossible(int prevX, int prevY){
	if (Board.getState() == "white" && prevX == 4 && prevY == 0){
	    if(Board.getPieceTypeAt(0,0) == PieceType.ROOK && Board.getPieceTypeAt(3,0) == PieceType.EMPTY
	       && Board.getPieceTypeAt(2,0) == PieceType.EMPTY && Board.getPieceTypeAt(1,0) == PieceType.EMPTY) {
		return true;
	    }
	} else if(Board.getState() == "black" && prevX == 4 && prevY == 8){
	    if(Board.getPieceTypeAt(0,8) == PieceType.ROOK && Board.getPieceTypeAt(3,8) == PieceType.EMPTY
	       && Board.getPieceTypeAt(2,8) == PieceType.EMPTY
	       && Board.getPieceTypeAt(1,8) == PieceType.EMPTY) {
		return true;
	    }
	} else {
	    return false;
	}
	return false;
    }

    public boolean pawnUpgradePossible(int y) {
	if (dragPiece.getColor() == "white" && y == 0) { //Tänker att vi kollar detta villkor i t.ex component och tar upp en menyval om "true".
	    return true;
	}
	else return dragPiece.getColor() == "black" && y == 7;
    }

    public static void doCastling(){

    }
}
