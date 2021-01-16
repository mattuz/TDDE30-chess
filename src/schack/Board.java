package schack;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Board
{
    private  int width;
    private  int height;
    private Piece[][] square;
    private PieceType[][] enumsquare;
    private List<Piece> deadpieces = new ArrayList<>(); //Vet inte varför den är markerad. Verkar fungera som det ska.
    private List<BoardListener> listeners = new ArrayList<>();
    public List<Piece> pieceList = new ArrayList<>();
    private Piece checkPiece = null;

    private final static PieceColor WHITE_STATE = PieceColor.WHITE;
    private final static PieceColor BLACK_STATE = PieceColor.BLACK;

    private PieceColor state = WHITE_STATE;


    public Board(final int width, final int height) {
	this.width = width;
	this.height = height;
	this.square = new Piece[width][height];
	this.enumsquare = new PieceType[width][height];
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x < width; x++) {
		enumsquare[x][y] = PieceType.EMPTY;
		placePieces(x,y);
	    }
	}
    }

    /**
     * Adds the Pieces to a list.
     * Used to keep track and loop though all available Pieces.
     */
    public List<Piece> addPieces() {
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x < width; x++) {
		if (square[x][y] != null) {
		    pieceList.add(square[x][y]);
		}
	    }
	}
	return pieceList;
    }

    /**
     * Places Pieces on the Board array.
     * Uses pieceSwitcher to determine and create the correct Piece.
     */
    public void placePieces(int x, int y) {
	if (y == 0) {
            switchPieces(x, y, PieceColor.BLACK);
	}
        else if (y == 1) {
	    square[x][y] = new Pawn(x, y, PieceType.PAWN, PieceColor.BLACK, getPathFor(PieceColor.BLACK, PieceType.PAWN),
				    this, true);
	}
        else if (y == 6) {
	    square[x][y] = new Pawn(x, y, PieceType.PAWN, PieceColor.WHITE, getPathFor(PieceColor.WHITE, PieceType.PAWN),
				    this, true);
	}
        else if (y == 7) {
	    switchPieces(x, y, PieceColor.WHITE);
	}
    }

    /**
     * Assigns URL-paths for pictures of their specific Piece.
     * Checks color and PieceType to match URL with correct Piece.
     */
    public URL getPathFor(PieceColor color, PieceType type) {
        return ClassLoader.getSystemResource(type + "_" + color + ".png");
    }

    /**
     * Switches between pieces depending on which should be placed where on the board array.
     * Each placement position is fixed and determined by placePieces.
     */
    public void switchPieces(int x, int y, PieceColor color) {
	switch (x) {
	    case 0:
	    case 7:
		square[x][y] = new Rook(x, y, PieceType.ROOK, color, getPathFor(color, PieceType.ROOK), this,
					true);
	        break;
	    case 1:
	    case 6:
		square[x][y] = new Knight(x, y, PieceType.KNIGHT, color, getPathFor(color, PieceType.KNIGHT), this,
					  true);
	        break;
	    case 2:
	    case 5:
		square[x][y] = new Bishop(x, y, PieceType.BISHOP, color, getPathFor(color, PieceType.BISHOP), this,
					  true);
		break;
	    case 3:
		square[x][y] = new Queen(x, y, PieceType.QUEEN, color, getPathFor(color, PieceType.QUEEN), this,
					 true);
		break;
	    case 4:
		square[x][y] = new King(x, y, PieceType.KING, color, getPathFor(color, PieceType.KING), this,
					true);
		break;
	    default:
	        break;
	}
    }

    /**
     * Adds listeners to the board array.
     */
    public void addBoardListener(BoardListener bl) {
	listeners.add(bl);
    }

    /**
     * Used to notify listeners and execute their respective actions.
     */
    public void notifyListeners() {
	for (BoardListener listeners : listeners) {
	    listeners.boardChanged();
	}
    }

    /**
     * Used to remove a Piece from last position. Does not delete the Piece object.
     */
    public void removePiece(int x, int y) {
	square[x][y] = null;
        notifyListeners();
    }

    /**
     * Destroys/removes a Piece object.
     * This is used when another Piece "takes" the piece on (x,y).
     */
    public void destroyPiece(int x, int y) {
	pieceList.remove(square[x][y]);
	square[x][y] = null;
	notifyListeners();
    }

    /**
     * Changes the current state of board.
     * The state is determined by which players turn it is.
     * Always starts with white player and changes when a legal move has been made.
     */
    public void swapTurns() {
	if (state == WHITE_STATE) {
            state = BLACK_STATE;
            Panel.getjLabel().setText(Panel.getPlayer2()); //TODO ändra dessa
	} else {
            state = WHITE_STATE;
	    Panel.getjLabel().setText(Panel.getPlayer1());
        }
    }

    /**
     * Checks if a Pawn can be upgraded (if it has moved across the entire board).
     *
     */
    public boolean pawnUpgradePossible(Piece piece, int y) {
	if (piece.getType() != PieceType.PAWN) {
	    return false;
	}
        else if (piece.getColor() == PieceColor.WHITE && y == 0) {
	    return true;
	}
	else return piece.getColor() == PieceColor.BLACK && y == 7;
    }

    /**
     * Checks if the castling move is possible and returns a value depending on
     * which paths are possible.
     * 3 if castling available both queen side and king side,
     * 2 if castling only available king side,
     * 1 if castling only avaiable queen side.
     */
    public int castlingPossiblePath(Piece piece){
	if (piece.getType() == PieceType.KING && piece.firstStep && piece.getColor() == state) {
	    if (isCastlingLeft(piece) && isCastlingRight(piece)) {
		System.out.println("Båda!");
	        return 3;
	    }
	    else if (isCastlingLeft(piece)) {
		System.out.println("Vänster!");
		return 1;
	    }
	    else if (isCastlingRight(piece)) {
		System.out.println("Höger!");
	        return 2;
	    }
	} return 0;
    }

    /**
     * Is castling queenside possible?
     */
    private boolean isCastlingLeft(final Piece piece) {
	return getPieceTypeAt(0, piece.getPieceY()) == PieceType.ROOK && square[0][piece.getPieceY()].isFirstStep() &&
	    getPieceTypeAt(3, piece.getPieceY()) == PieceType.EMPTY &&
	    getPieceTypeAt(2, piece.getPieceY()) == PieceType.EMPTY &&
	    getPieceTypeAt(1, piece.getPieceY()) == PieceType.EMPTY;
    }

    /**
     * Is castling kingside possible?
     */
    private boolean isCastlingRight(final Piece piece) {
	return getPieceTypeAt(7, piece.getPieceY()) == PieceType.ROOK && square[7][piece.getPieceY()].isFirstStep() &&
	       getPieceTypeAt(6, piece.getPieceY()) == PieceType.EMPTY &&
	       getPieceTypeAt(5,piece.getPieceY()) == PieceType.EMPTY;
    }

    /**
     * Checks if current piece is checked (used to determine if king is in check).
     * Loops through all pieces on the board to check if any of them has a legal move
     * that attacks the king.
     */
    public boolean isChecked(Piece piece) {
	Position position = new Position(piece.getPieceX(), piece.getPieceY());
        if (piece.getType() == PieceType.KING) {
	    for (Piece p: pieceList) {
		if (p.color != piece.color && containsPosition(p.getlegalMoves(), position)) {
		    checkPiece = p;
		    return true;
		}
	    }
	} return false;
    }

    /**
     * Checks if a piece can interrupt the check of the king, and move inbetween.
     * Compares the values of the piece's eventual x,y and calculates if this would
     * put it in the way of the king's attacker.
     */
    public boolean interruptChecked(Piece piece, int newX, int newY) {
	int pX = piece.getPieceX();
	int pY = piece.getPieceY();
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 8; y++) {
		if (isChecked(piece) && checkPiece.getPieceX() != pX && checkPiece.getPieceY() == pY) {
		    if (checkPiece.getPieceX() < newX && newX < pX) {
		        return true;
		    }
		    else return checkPiece.getPieceX() > newX && newX > pX;
		}
		else if (isChecked(piece) && checkPiece.getPieceX() == pX && checkPiece.getPieceY() != pY) {
		    if (checkPiece.getPieceY() < newY && newY < pY) {
			return true;
		    }
		    else return checkPiece.getPieceY() > newY && newY > pY;
		}
		else if (isChecked(piece) && Math.abs(pX - checkPiece.getPieceX()) - Math.abs(pY - checkPiece.getPieceY()) == 0
		&& Math.abs(newX - pX) - Math.abs(newY - pY) == 0) {
		    return Math.abs(newX - pX) < Math.abs(checkPiece.getPieceX() - pX) &&
			   Math.abs(newY - pY) < Math.abs(checkPiece.getPieceY() - pY);
		}
	    }
	} return false;
    }

    /**
     * Checks if a piece has the location in its legal moves.
     */
    public boolean containsPosition(List<Position> list, Position pos){
	Boolean doesContain = false;
	for (Position elem: list) {
	    if (elem.getX() == pos.getX() && elem.getY() == pos.getY()) {
		doesContain = true;
		break;
	    }
	}
	return doesContain;
    }

    public PieceColor getState(){
	return state;
    }

    public Piece[][] getSquare() {
	return square;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public List<Piece> getDeadpieces() {
	return deadpieces;
    }

    public PieceType getPieceTypeAt(int x, int y) {
	if (square[x][y] != null) {
	    return square[x][y].getType();
	}
	return enumsquare[x][y];
    }

    public Piece getPieceAt(int x, int y) {
	return square[x][y];
    }

    public List<Piece> getPieceList() {
	return pieceList;
    }

}
