package schack;

import javax.swing.*;
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
    private Piece whiteKing = null;
    private Piece blackKing = null;
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
	/**
	 * This is the main checker of our game.
	 * Checks if conditions for a move is met.
	 */
	int x = dragPiece.getPieceX();
	int y = dragPiece.getPieceY();

	 if (board.isChecked(dragPiece)) {
	    dragPiece.newX(oldX);
	    dragPiece.newY(oldY);
	    board.changeState();
	    JOptionPane.showMessageDialog(null, "Illegal move of the king.");

	}
	else if (board.getState() == "white" && board.isChecked(whiteKing) && dragPiece.getType() != PieceType.KING) {
	    if (board.interruptChecked(whiteKing, x, y)) {
	        movePiece(x, y);
	    } else {
		dragPiece.newX(oldX);
		dragPiece.newY(oldY);
		board.changeState();
		JOptionPane.showMessageDialog(null, "The king is checked.");
	    }

	}
	else if (board.getState() == "black" && board.isChecked(blackKing) && dragPiece.getType() != PieceType.KING) {
	    if (board.interruptChecked(blackKing, x, y)) {
		movePiece(x, y);
	    } else {
		dragPiece.newX(oldX);
		dragPiece.newY(oldY);
		board.changeState();
		JOptionPane.showMessageDialog(null, "The king is checked.");
	    }

	} if (board.containsPosition(dragPiece.getlegalMoves(), new Position(x, y))){
	    System.out.println("tre");
            if (!board.interruptChecked(whiteKing, x, y)) {
		movePiece(x,y);
	    }

            if (board.pawnUpgradePossible(dragPiece, y)){ //TODO borde denna ligga i Pawn egentligen? Hur skulle det funka?
                upgradePawn(x, y);
	    }
            if (dragPiece.getType() == PieceType.KING && x - 2 == oldX) {
		board.getSquare()[x-1][y] = board.getSquare()[7][y];
		board.removePiece(7, y);
	    }
            if (dragPiece.getType() == PieceType.KING && x + 2 == oldX) {
		board.getSquare()[x+1][y] = board.getSquare()[0][y];
		board.removePiece(0, y);
	    }

	} else {
	    dragPiece.newX(oldX);
	    dragPiece.newY(oldY);
	    board.changeState();
	} this.dragPiece = null;
	updateAllLegalMoves();
        board.notifyListeners();
        board.changeState();
    }

    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	/**
	 * If a Piece is currently being dragged, Set its new(x,y)-values to the new square.
	 */
	if (this.dragPiece != null){
	    this.dragPiece.newX((mouseEvent.getPoint().x - 7)/PieceComponent.getBOARDCONSTANT());
	    this.dragPiece.newY((mouseEvent.getPoint().y - WINDOWOFFSET)/PieceComponent.getBOARDCONSTANT());
	}
    }

    @Override public void mousePressed(final MouseEvent mouseEvent) {
	/**
	 * Choses a Piece by looping through our list of Pieces - checks if it's been pressed.
	 * Sets this to current dragPiece.
	 */
	int x = mouseEvent.getPoint().x - XOFFSET;
	int y = mouseEvent.getPoint().y - WINDOWOFFSET;
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    if (piece.getColor() == board.getState() && piece.getType() == PieceType.KING) {
	        if (board.getState() == "white") {
		    whiteKing = piece;
		} else {
	            blackKing = piece;
		}
	    }
	    if (mouseOverPiece(piece, x, y)) {
		this.dragOffsetX = x - piece.getPieceX() * PieceComponent.getBOARDCONSTANT();
		this.dragOffsetY = y - piece.getPieceY() * PieceComponent.getBOARDCONSTANT();
		this.oldX = (x - this.dragOffsetX)/PieceComponent.getBOARDCONSTANT();
		this.oldY = (y - this.dragOffsetY)/PieceComponent.getBOARDCONSTANT();
		this.dragPiece = piece;
		checkFirstStep = dragPiece.isFirstStep();
		dragPiece.updateLegalMoves();
		System.out.println(dragPiece.getType()); //TODO for-loop här kanske, som kollar om samma colors kung är checkad. Är den det måste dragPiece ändras.

	    }
	}
	if (this.dragPiece != null){
	    this.pieces.remove(this.dragPiece);
	    this.pieces.add(this.dragPiece);
	}
    }

    private boolean mouseOverPiece(Piece piece, int x, int y) {
	/**
	 * Checks if chosen Piece is currently being hovered over by mouse.
	 */
	return piece.getPieceX() * PieceComponent.getBOARDCONSTANT()  <= x &&
	       piece.getPieceX() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= x &&
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() <= y &&
	       piece.getPieceY() * PieceComponent.getBOARDCONSTANT() + PieceComponent.getBOARDCONSTANT() >= y;
    }

    private void movePiece(int x, int y) {
	/**
	 * Moves a Piece on the board array.
	 * Removes attacked piece from the game if one exists and the move is legal.
	 */
	if (board.getSquare()[x][y] != null) {
	    board.destroyPiece(x, y);
	}
	board.getSquare()[x][y] = board.getSquare()[oldX][oldY];
	dragPiece.setFirstStep(false);

	if (x != oldX || y != oldY) {
	    board.removePiece(oldX, oldY);
	}
    }

    private void updateAllLegalMoves(){
	/**
	 * Updates the legal moves of all Pieces.
	 */
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    piece.setPreviousLegalMoves(piece.getlegalMoves());
	    piece.updateLegalMoves();
	}
    }

    private void revertAllLegalMoves() {
	/**
	 * Is meant to revert all legal moves. Does not work as intended.
	 */
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    piece.setLegalMoves(piece.getPreviousLegalMoves());
	}
    }

    private void upgradePawn(int x, int y) {
	/**
	 * Upgrades Pawn to one of the available pawnUpgrades.
	 * When upgradePawn is available, it shows a JOptionPane where you can chose what to turn the Pawn in to.
	 */
	String[] pawnUpgrades = new String[] {"Queen", "Bishop", "Rook", "Knight"};
	int response = JOptionPane.showOptionDialog(
		null, "Choose one of the following: ", "Upgrade pawn!",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, pawnUpgrades, pawnUpgrades[0]);

	board.destroyPiece(x, y);
	chooseUpgrade(response, x, y);
    }

    private void chooseUpgrade(int response, int x, int y) {
	/**
	 * Creates the Piece and replaces this with the Pawn which is being upgraded.
	 */
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


}
