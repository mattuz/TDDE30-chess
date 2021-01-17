package schack;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Class that manages input from the mouse and moves the pieces accordingly.
 */
public class PieceMover extends MouseAdapter
{
    private List<Piece> pieces;
    private final Board board;
    private static final int WINDOW_OFFSET = 30;
    private static final int X_OFFSET = 7;
    private int oldX; //Dessa två har liknande namn av uppenbar anledning. (Precis som alla gånger innan med x/y).
    private int oldY;
    private Piece dragPiece = null;

    public PieceMover(Board board) {
	this.pieces = board.getPieces();
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
		dragPiece.setPieceX(oldX);
		dragPiece.setPieceY(oldY);
		board.swapTurns();

	    } else if (board.getState() == PieceColor.WHITE && board.isChecked(board.getWhiteKing()) &&
		       dragPiece.getType() != PieceType.KING) { //Typcheckarna i denna klass kommer alla ha samma funktion.
	        						//De används för att dubbelkolla så att inte fel pjäs tittas på
								//för något som inte kan hända. Vi vet att polymorfism kanske skulle
								//vara att föredra här, men vi fick inte någon idé om hur vi skulle implementera det
								//då vi inte har samma, straight-forward-syfte, som i exemplet på kurshemsidan:
								//"om monster, gör monstergång, om människa gör människogång".
								//Detta applicerar på samtliga upprepningar av "MaybeTypeCheck".
		if (board.interruptChecked(board.getWhiteKing(), dragPiece, x, y)) {
		    movePiece(x, y);
		} else {
		    JOptionPane.showMessageDialog(null, "The king is checked.");
		    dragPiece.setPieceX(oldX);
		    dragPiece.setPieceY(oldY);
		    board.swapTurns();
		}
	    } else if (board.getState() == PieceColor.BLACK && board.isChecked(board.getBlackKing()) &&
		       dragPiece.getType() != PieceType.KING) {

		if (board.interruptChecked(board.getBlackKing(), dragPiece, x, y)) {
		    movePiece(x, y);
		} else {
		    JOptionPane.showMessageDialog(null, "The king is checked.");
		    dragPiece.setPieceX(oldX);
		    dragPiece.setPieceY(oldY);
		    board.swapTurns();
		}
	    } else if (board.containsPosition(dragPiece.getLegalMoves(), new Position(x, y))) {
		if (!board.interruptChecked(board.getWhiteKing(), dragPiece, x, y)) {
		    movePiece(x, y);
		}
		if (board.isPawnUpgradePossible(dragPiece, y)) {
		    upgradePawn(x, y);
		}
		if (dragPiece.getType() == PieceType.KING && x - Board.CASTLING_MOVE_DISTANCE == oldX) { //Vi har också liknande expressions
		    										//här. Men som i tidigare fall har de små skillnader
		    										//och vi anser inte att det ger särskilt mycket att
		    										//bryta ut en variabel.
		    board.getSquare()[x - 1][y] = board.getSquare()[Board.RIGHT_ROOK_START_COL][y];
		    board.removePiece(Board.RIGHT_ROOK_START_COL, y);
		}
		if (dragPiece.getType() == PieceType.KING && x + Board.CASTLING_MOVE_DISTANCE == oldX) {
		    board.getSquare()[x + 1][y] = board.getSquare()[Board.LEFT_ROOK_START_COL][y];
		    board.removePiece(Board.LEFT_ROOK_START_COL, y);
		}
	    } else {
		dragPiece.setPieceX(oldX);
		dragPiece.setPieceY(oldY);
		board.swapTurns();
	    }
	    this.dragPiece = null;
	    updateAllLegalMoves();
	    board.notifyListeners();
	    gameOverMessage();
	    board.swapTurns();

	}
    }

    /**
     * Prints the message that either the black or white king has been checked.
     * The game is over if this is displayed.
     */
    private void gameOverMessage() {
	if(board.isChecked(board.getWhiteKing())) {
	    if (board.isCheckMate(board.getWhiteKing())) {
		JOptionPane.showMessageDialog(null, "Check mate! White has lost!!");
	    }
	} else if (board.isChecked(board.getBlackKing())) {
	    if (board.isCheckMate(board.getBlackKing())) {
		JOptionPane.showMessageDialog(null, "Check mate! Black has lost!!");
	    }
	}
    }

    /**
     * If a Piece is currently being dragged, Set its new(x,y)-values to the new square.
     */
    @Override public void mouseDragged(final MouseEvent mouseEvent) {
	if (this.dragPiece != null) {
	    this.dragPiece.setPieceX((mouseEvent.getPoint().x - X_OFFSET) / BoardComponent.getboardconstant());
	    this.dragPiece.setPieceY((mouseEvent.getPoint().y - WINDOW_OFFSET) / BoardComponent.getboardconstant());
	}
    }

    /**
     * Choses a Piece by looping through our list of Pieces - checks if it's been pressed.
     * Sets this to current dragPiece.
     */
    @Override public void mousePressed(final MouseEvent mouseEvent) {
	int x = mouseEvent.getPoint().x - X_OFFSET;
	int y = mouseEvent.getPoint().y - WINDOW_OFFSET;
	for (int i = this.pieces.size()-1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    if (isMouseOverPiece(piece, x, y)) {
			int dragOffsetX = x - piece.getPieceX() * BoardComponent.getboardconstant();
			int dragOffsetY = y - piece.getPieceY() * BoardComponent.getboardconstant();
			this.oldX = (x - dragOffsetX) / BoardComponent.getboardconstant();
			this.oldY = (y - dragOffsetY) / BoardComponent.getboardconstant();
			this.dragPiece = piece;
			dragPiece.updateLegalMoves();
		}
	}
		if (this.dragPiece != null) {
			this.pieces.remove(this.dragPiece);
			this.pieces.add(this.dragPiece);
		}
	}

    /**
     * Checks if chosen Piece is currently being hovered over by mouse.
     */
    private boolean isMouseOverPiece(Piece piece, int x, int y) { //"RepeatedExpression", samm här, lika men ändå olika. Ser inte
        							//att det skulle ge oss mycket att bryta ut variabler.
	return piece.getPieceX() * BoardComponent.getboardconstant() <= x &&
	       piece.getPieceX() * BoardComponent.getboardconstant() + BoardComponent.getboardconstant() >= x &&
	       piece.getPieceY() * BoardComponent.getboardconstant() <= y &&
	       piece.getPieceY() * BoardComponent.getboardconstant() + BoardComponent.getboardconstant() >= y;
    }

    /**
     * Moves a Piece on the board array. Removes attacked piece from the game if one exists and the move is legal.
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
    private void updateAllLegalMoves() {
	for (int i = this.pieces.size() - 1; i >= 0; i--) {
	    Piece piece = this.pieces.get(i);
	    piece.setPreviousLegalMoves(piece.getLegalMoves());
	    piece.updateLegalMoves();
	}
    }


    /**
     * Upgrades Pawn to one of the available pawnUpgrades. When upgradePawn is available, it shows a JOptionPane where you can
     * chose what to turn the Pawn in to.
     */
    private void upgradePawn(int x, int y) {
	String[] pawnUpgrades = new String[] { "Queen", "Bishop", "Rook", "Knight" };
	int response = JOptionPane
		.showOptionDialog(null, "Choose one of the following: ", "Upgrade pawn!", JOptionPane.DEFAULT_OPTION,
				  JOptionPane.PLAIN_MESSAGE, null, pawnUpgrades, pawnUpgrades[0]);

	board.destroyPiece(x, y);
	chooseUpgrade(pawnUpgrades[response], x, y);
    }

    /**
     * Creates the Piece and replaces this with the Pawn which is being upgraded. These choices are defined by a list of
     * possible Pieces as seen in upgradePawn. The index of the chosen piece is returned and then used as "response".
     */
    private void chooseUpgrade(String choice, int x, int y) { //Vi tyckte att det blev smidigast att representera switchen med strängar
        						//eftersom pawnUpgrades (i upgradePawn()) innehåller strängar. Dessa skrivs ut på
							//valpanelen och man väljer därefter.
	switch (choice) {
	    case "Queen":
		board.getSquare()[x][y] =
			new Queen(x, y, PieceType.QUEEN, dragPiece.color, board.getPathFor(dragPiece.color, PieceType.QUEEN),
				  board, false);
		board.getPieces().add(board.getSquare()[x][y]);
		break;
	    case "Bishop":
		board.getSquare()[x][y] =
			new Bishop(x, y, PieceType.BISHOP, dragPiece.color, board.getPathFor(dragPiece.color, PieceType.BISHOP),
				   board, false);
		board.getPieces().add(board.getSquare()[x][y]);

		break;

	    case "Rook":
		board.getSquare()[x][y] =
			new Rook(x, y, PieceType.ROOK, dragPiece.color, board.getPathFor(dragPiece.color, PieceType.ROOK),
				 board, false);
		board.getPieces().add(board.getSquare()[x][y]);

		break;

	    case "Knight":
		board.getSquare()[x][y] =
			new Knight(x, y, PieceType.KNIGHT, dragPiece.color, board.getPathFor(dragPiece.color, PieceType.KNIGHT),
				   board, false);
		board.getPieces().add(board.getSquare()[x][y]);

		break;
	    default:
		break;
	}
    }


}
