package schack;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PieceMover extends MouseAdapter
{
    private List<Piece> pieces;
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



    public PieceMover(Board board) {
	this.pieces = board.pieceList;
	this.board = board;
	this.pieces = board.addPieces();
    }

    /**
     * This is the main checker of our game.
     * Checks if conditions for a move is met.
     */
    @Override public void mouseReleased(final MouseEvent mouseEvent) {
        if (dragPiece != null) {
	    int x = dragPiece.getPieceX();
	    int y = dragPiece.getPieceY();

	    if (board.isChecked(dragPiece)) {
		JOptionPane.showMessageDialog(null, "Illegal move of the king.");
		dragPiece.newX(oldX);
		dragPiece.newY(oldY);
		board.swapTurns();
	    }
	    else if (board.getState() == PieceColor.WHITE && board.isChecked(whiteKing) && dragPiece.getType() != PieceType.KING) {
		if (board.interruptChecked(whiteKing, x, y)) {
		    System.out.println("interruptChecked gör att den flyttas.. (vit)");
		    movePiece(x, y);
		} else {
		    JOptionPane.showMessageDialog(null, "The king is checked.");

		    System.out.println("Ska ha resettats (vit)");
		    dragPiece.newX(oldX);
		    dragPiece.newY(oldY);
		    board.swapTurns();

		}

	    }
	    else if (board.getState() == PieceColor.BLACK && board.isChecked(blackKing) && dragPiece.getType() != PieceType.KING) {
		if (board.interruptChecked(blackKing, x, y)) {
		    System.out.println("interruptChecked gör att den flyttas.. (svart)");

		    movePiece(x, y);
		} else {
		    JOptionPane.showMessageDialog(null, "The king is checked.");

		    System.out.println("Ska ha resettats (svart)");

		    dragPiece.newX(oldX);
		    dragPiece.newY(oldY);
		    board.swapTurns();
		}

	    }
	    else if (board.containsPosition(dragPiece.getlegalMoves(), new Position(x, y))){
		if (!board.interruptChecked(whiteKing, x, y)) {
		    movePiece(x,y);
		}

		if (board.isPawnUpgradePossible(dragPiece, y)){
		    upgradePawn(x, y);
		}
		if (dragPiece.getType() == PieceType.KING && x - Board.CASTLING_MOVE_DISTANCE == oldX) {
		    board.getSquare()[x-1][y] = board.getSquare()[Board.RIGHT_ROOK_START_COL][y];
		    board.removePiece(Board.RIGHT_ROOK_START_COL, y);
		}
		if (dragPiece.getType() == PieceType.KING && x + Board.CASTLING_MOVE_DISTANCE == oldX) {
		    board.getSquare()[x+1][y] = board.getSquare()[Board.LEFT_ROOK_START_COL][y];
		    board.removePiece(Board.LEFT_ROOK_START_COL, y);
		}

	    } else {
		dragPiece.newX(oldX);
		dragPiece.newY(oldY);
		board.swapTurns();
	    } this.dragPiece = null;
	    updateAllLegalMoves();
	    board.notifyListeners();
	    if(board.isChecked(whiteKing)){
		System.out.println("vit kung checkad!!");
	    }
	    board.swapTurns();
	}
    }

    /**
     * If a Piece is currently being dragged, Set its new(x,y)-values to the new square.
     */
    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	if (this.dragPiece != null){
	    this.dragPiece.newX((mouseEvent.getPoint().x - XOFFSET) / BoardComponent.getBOARDCONSTANT());
	    this.dragPiece.newY((mouseEvent.getPoint().y - WINDOWOFFSET) / BoardComponent.getBOARDCONSTANT());
	}
    }

    /**
     * Choses a Piece by looping through our list of Pieces - checks if it's been pressed.
     * Sets this to current dragPiece.
     */
    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x - XOFFSET;
	int y = mouseEvent.getPoint().y - WINDOWOFFSET;
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    if (piece.getColor() == board.getState() && piece.getType() == PieceType.KING) {
	        if (board.getState() == PieceColor.WHITE) {
		    whiteKing = piece;
		} else {
	            blackKing = piece;
		}
	    }
	    if (mouseOverPiece(piece, x, y)) {
		this.dragOffsetX = x - piece.getPieceX() * BoardComponent.getBOARDCONSTANT();
		this.dragOffsetY = y - piece.getPieceY() * BoardComponent.getBOARDCONSTANT();
		this.oldX = (x - this.dragOffsetX) / BoardComponent.getBOARDCONSTANT();
		this.oldY = (y - this.dragOffsetY) / BoardComponent.getBOARDCONSTANT();
		this.dragPiece = piece;

		dragPiece.updateLegalMoves();
		//System.out.println(dragPiece.getType()); //TODO for-loop här kanske, som kollar om samma colors kung är checkad. Är den det måste dragPiece ändras.

	    }
	}
	if (this.dragPiece != null){
	    this.pieces.remove(this.dragPiece);
	    this.pieces.add(this.dragPiece);
	}
    }

    /**
     * Checks if chosen Piece is currently being hovered over by mouse.
     */
    private boolean mouseOverPiece(Piece piece, int x, int y) {
	return piece.getPieceX() * BoardComponent.getBOARDCONSTANT() <= x &&
	       piece.getPieceX() * BoardComponent.getBOARDCONSTANT() + BoardComponent.getBOARDCONSTANT() >= x &&
	       piece.getPieceY() * BoardComponent.getBOARDCONSTANT() <= y &&
	       piece.getPieceY() * BoardComponent.getBOARDCONSTANT() + BoardComponent.getBOARDCONSTANT() >= y;
    }

    /**
     * Moves a Piece on the board array.
     * Removes attacked piece from the game if one exists and the move is legal.
     */
    private void movePiece(int x, int y) {
	if (board.getSquare()[x][y] != null) {
	    board.destroyPiece(x, y);
	}
	board.getSquare()[x][y] = board.getSquare()[oldX][oldY];
	dragPiece.setFirstStep(false);

	if (x != oldX || y != oldY) {
	    board.removePiece(oldX, oldY);
	}
    }

    /**
     * Updates the legal moves of all Pieces.
     */
    private void updateAllLegalMoves(){
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    piece.setPreviousLegalMoves(piece.getlegalMoves());
	    piece.updateLegalMoves();
	}
    }

    /**
     * Is meant to revert all legal moves. Does not work as intended.
     * Not used
     */
    private void revertAllLegalMoves() { //TODO ta bort denna?
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    piece.setLegalMoves(piece.getPreviousLegalMoves());
	}
    }

    /**
     * Upgrades Pawn to one of the available pawnUpgrades.
     * When upgradePawn is available, it shows a JOptionPane where you can chose what to turn the Pawn in to.
     */
    private void upgradePawn(int x, int y) {
	String[] pawnUpgrades = new String[] {"Queen", "Bishop", "Rook", "Knight"};
	int response = JOptionPane.showOptionDialog(
		null, "Choose one of the following: ", "Upgrade pawn!",
		JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, pawnUpgrades, pawnUpgrades[0]);

	board.destroyPiece(x, y);
	chooseUpgrade(pawnUpgrades[response], x, y);
    }

    /**
     * Creates the Piece and replaces this with the Pawn which is being upgraded.
     * These choices are defined by a list of possible Pieces as seen in upgradePawn.
     * The index of the chosen piece is returned and then used as "response".
     */
    private void chooseUpgrade(String choice, int x, int y) {
	switch (choice) {
	    case "Queen":
		board.getSquare()[x][y] =
			new Queen(x, y, PieceType.QUEEN, dragPiece.color,
				  board.getPathFor(dragPiece.color, PieceType.QUEEN), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);
		break;
	    case "Bishop":
		board.getSquare()[x][y] =
			new Bishop(x, y, PieceType.BISHOP, dragPiece.color,
				   board.getPathFor(dragPiece.color, PieceType.BISHOP), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);

		break;

	    case "Rook":
		board.getSquare()[x][y] =
			new Rook(x, y, PieceType.ROOK, dragPiece.color,
				 board.getPathFor(dragPiece.color, PieceType.ROOK), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);

		break;

	    case "Knight":
		board.getSquare()[x][y] =
			new Knight(x, y, PieceType.KNIGHT, dragPiece.color,
				   board.getPathFor(dragPiece.color, PieceType.KNIGHT), board, false);
		board.getPieceList().add(board.getSquare()[x][y]);

		break;
	    default:
		break;
   	 }
    }


}
