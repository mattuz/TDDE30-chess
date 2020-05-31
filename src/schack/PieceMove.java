package schack;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

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
    private boolean checkFirstStep;



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
        if (containsPosition(dragPiece.getlegalMoves(), new Position(dragPiece.pieceX, dragPiece.pieceY))){
            if (board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] != null) {
                board.destroyPiece(dragPiece.getPieceX(), dragPiece.getPieceY());
	    }
            board.getSquare()[dragPiece.getPieceX()][dragPiece.getPieceY()] = board.getSquare()[oldX][oldY];
            dragPiece.setFirstStep(false);

            if (dragPiece.getPieceX() != oldX || dragPiece.getPieceY() != oldY) {
                board.removePiece(oldX, oldY);
            }
            if (board.pawnUpgradePossible(dragPiece, dragPiece.getPieceY())){ //TODO borde denna ligga i Pawn egentligen? Hur skulle det funka?
		int x = dragPiece.getPieceX();
		int y = dragPiece.getPieceY();

		String[] pawnUpgrades = new String[] {"Queen", "Bishop", "Rook", "Knight"};
		int response = JOptionPane.showOptionDialog(
			null, "Choose one of the following: ", "Upgrade pawn!",
			JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, pawnUpgrades, pawnUpgrades[0]);

		board.destroyPiece(x, y);
		chooseUpgrade(response, x, y);
	    }
            if (dragPiece.getType() == PieceType.KING && dragPiece.getPieceX() - 2 == oldX) {
		board.getSquare()[dragPiece.getPieceX()-1][dragPiece.getPieceY()] = board.getSquare()[7][dragPiece.getPieceY()];
		board.removePiece(7, dragPiece.getPieceY());
	    }
            if (dragPiece.getType() == PieceType.KING && dragPiece.getPieceX() + 2 == oldX) {
		board.getSquare()[dragPiece.getPieceX()+1][dragPiece.getPieceY()] = board.getSquare()[0][dragPiece.getPieceY()];
		board.removePiece(0, dragPiece.getPieceY());
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
		checkFirstStep = dragPiece.isFirstStep();
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

    private void chooseUpgrade(int response, int x, int y) {
	switch (response) {
	    case 0:
		board.getSquare()[x][y] =
			new Queen(x,y,PieceType.QUEEN,dragPiece.color,
				  board.assignPaths(dragPiece.color, PieceType.QUEEN), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);
		break;
	    case 1:
		board.getSquare()[x][y] =
			new Bishop(x,y,PieceType.BISHOP,dragPiece.color,
				   board.assignPaths(dragPiece.color, PieceType.BISHOP), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);

		break;

	    case 2:
		board.getSquare()[x][y] =
			new Rook(x,y,PieceType.ROOK,dragPiece.color,
				 board.assignPaths(dragPiece.color, PieceType.ROOK), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);

		break;

	    case 3:
		board.getSquare()[x][y] =
			new Knight(x,y,PieceType.KNIGHT,dragPiece.color,
				   board.assignPaths(dragPiece.color, PieceType.KNIGHT), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);

		break;
	    default:
		break;
   	 }
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
