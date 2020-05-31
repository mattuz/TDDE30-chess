package schack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    }

    @Override public void mouseReleased(final MouseEvent mouseEvent) {
	for (int i = 0; i < dragPiece.getlegalMoves().size()-1; i++) {
	    System.out.println(dragPiece.getlegalMoves().get(i).getX() + ", " + dragPiece.getlegalMoves().get(i).getY());
	}

        /*if (isCastlingPossible(oldX, oldY) && dragPiece.getPieceX() == 2){ //TODO fixa så att denna endast kollas om det "blir möjligt".
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
        }*/
        if (containsPosition(dragPiece.getlegalMoves(), new Position(dragPiece.pieceX, dragPiece.pieceY))){
            if (board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] != null) {
                board.removePiece(dragPiece.getPieceX(), dragPiece.getPieceY());
	    }
            board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] = board.getSquare()[oldX][oldY];

            if (dragPiece.getPieceX() != oldX || dragPiece.getPieceY() != oldY) {
                board.removePiece(oldX, oldY);
            }
            else if (dragPiece.getType() == PieceType.PAWN && pawnUpgradePossible(dragPiece.getPieceY())){
                //gör ett menyval
            }
	} else {
	    dragPiece.newX(oldX);
	    dragPiece.newY(oldY);
	    board.changeState();
	} this.dragPiece = null;
        board.notifyListeners();
        board.changeState();
    }

    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	if (this.dragPiece != null){
	    this.dragPiece.newX((mouseEvent.getPoint().x - 7)/PieceComponent.getBOARDCONSTANT());
	    this.dragPiece.newY((mouseEvent.getPoint().y - WINDOWOFFSET)/PieceComponent.getBOARDCONSTANT());
	}
    }

    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x - XOFFSET;
	int y = mouseEvent.getPoint().y - WINDOWOFFSET;
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);

	    if (mouseOverPiece(piece, x, y)) {
		this.dragOffsetX = x - piece.getPieceX() * PieceComponent.getBOARDCONSTANT();
		this.dragOffsetY = y - piece.getPieceY() * PieceComponent.getBOARDCONSTANT();
		this.oldX = (x - this.dragOffsetX)/PieceComponent.getBOARDCONSTANT();
		this.oldY = (y - this.dragOffsetY)/PieceComponent.getBOARDCONSTANT();
		this.dragPiece = piece;
		dragPiece.updateLegalMoves();
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

    public boolean isCastlingPossible(int prevX, int prevY){ //TODO: Denna bör inte ligga i PieceMove
	if (board.getState() == "white" && prevX == 4 && prevY == 0){
	    return board.getPieceTypeAt(0, 0) == PieceType.ROOK && board.getPieceTypeAt(3, 0) == PieceType.EMPTY &&
		   board.getPieceTypeAt(2, 0) == PieceType.EMPTY && board.getPieceTypeAt(1, 0) == PieceType.EMPTY;
	} else if(board.getState() == "black" && prevX == 4 && prevY == 8){
	    return board.getPieceTypeAt(0, 8) == PieceType.ROOK && board.getPieceTypeAt(3, 8) == PieceType.EMPTY &&
		   board.getPieceTypeAt(2, 8) == PieceType.EMPTY && board.getPieceTypeAt(1, 8) == PieceType.EMPTY;
	} else {
	    return false;
	}
    }

    public boolean pawnUpgradePossible(int y) { //TODO: Denna bör inte ligga i PieceMove
	if (dragPiece.getColor() == "white" && y == 0) { //Tänker att vi kollar detta villkor i t.ex component och tar upp en menyval om "true".
	    return true;
	}
	else return dragPiece.getColor() == "black" && y == 7;
    }

    public static void doCastling(){

    }
    private boolean containsPosition(List<Position> list, Position pos){ //TODO: Denna kanske inte bör ligga i PieceMove
        Boolean doesContain = false;
        for (Position elem: list) {
	    if (elem.getX() == pos.getX() && elem.getY() == pos.getY()) {
		doesContain = true;
		break;
	    }
	}
	System.out.println(doesContain);
        return doesContain;
    }
}
