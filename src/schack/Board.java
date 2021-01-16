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

    /**
     * Constant for the black pawns starting row.
     */
    public static final int BLACK_PAWN_STARTING_ROW = 1;
    /**
     * Constant for the white pawns starting row.
     */
    public static final int WHITE_PAWN_STARTING_ROW = 6;
    /**
     * Black pieces starting row (excluding pawns).
     */
    public static final int BLACK_STARTING_ROW = 0;
    /**
     * White pieces starting row (excluding pawns).
     */
    public static final int WHITE_STARTING_ROW = 7;
    /**
     * Starting position of left rook.
     */
    public static final int LEFT_ROOK_START_COL = 0;
    /**
     * Starting position of right rook.
     */
    public static final int RIGHT_ROOK_START_COL = 7;
    /**
     * Starting position of left knight.
     */
    public static final int LEFT_KNIGHT_START_COL = 1;
    /**
     * Starting position of right knight.
     */
    public static final int RIGHT_KNIGHT_START_COL = 6;
    /**
     * Starting position of left bishop.
     */
    public static final int LEFT_BISHOP_START_COL = 2;
    /**
     * Starting position of right bishop.
     */
    public static final int RIGHT_BISHOP_START_COL = 5;
    /**
     * Starting position of queen
     */
    public static final int QUEEN_START_COL = 3;
    /**
     * Starting position of king.
     */
    public static final int KING_START_COL = 4;
    /**
     * The amount of positions in x-axis the king moves while castling.
     */
    public static final int CASTLING_MOVE_DISTANCE = 2;

    /**
     * Represents the state of the game. White's turn.
     */
    private static final PieceColor WHITE_STATE = PieceColor.WHITE;
    /**
     * Represents the state of the game. Black's turn.
     */
    private static final PieceColor BLACK_STATE = PieceColor.BLACK;

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
	if (y == BLACK_STARTING_ROW) {
            switchPieces(x, y, PieceColor.BLACK);
	}
        else if (y == BLACK_PAWN_STARTING_ROW) {
	    square[x][y] = new Pawn(x, y, PieceType.PAWN, PieceColor.BLACK, getPathFor(PieceColor.BLACK, PieceType.PAWN),
				    this, true);
	}
        else if (y == WHITE_PAWN_STARTING_ROW) {
	    square[x][y] = new Pawn(x, y, PieceType.PAWN, PieceColor.WHITE, getPathFor(PieceColor.WHITE, PieceType.PAWN),
				    this, true);
	}
        else if (y == WHITE_STARTING_ROW) {
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
	    case LEFT_ROOK_START_COL:
	    case RIGHT_ROOK_START_COL:
		square[x][y] = new Rook(x, y, PieceType.ROOK, color, getPathFor(color, PieceType.ROOK), this,
					true);
	        break;
	    case LEFT_KNIGHT_START_COL:
	    case RIGHT_KNIGHT_START_COL:
		square[x][y] = new Knight(x, y, PieceType.KNIGHT, color, getPathFor(color, PieceType.KNIGHT), this,
					  true);
	        break;
	    case LEFT_BISHOP_START_COL:
	    case RIGHT_BISHOP_START_COL:
		square[x][y] = new Bishop(x, y, PieceType.BISHOP, color, getPathFor(color, PieceType.BISHOP), this,
					  true);
		break;
	    case QUEEN_START_COL:
		square[x][y] = new Queen(x, y, PieceType.QUEEN, color, getPathFor(color, PieceType.QUEEN), this,
					 true);
		break;
	    case KING_START_COL:
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
	} else {
            state = WHITE_STATE;
        }
	Panel.setTurn(state);
    }

    /**
     * Checks if a Pawn can be upgraded (if it has moved across the entire board).
     *
     */
    public boolean isPawnUpgradePossible(Piece piece, int y) {
	if (piece.getType() != PieceType.PAWN) {
	    return false;
	}
        else if (piece.getColor() == PieceColor.WHITE && y == BLACK_STARTING_ROW) {
	    return true;
	}
	else return piece.getColor() == PieceColor.BLACK && y == WHITE_STARTING_ROW;
    }

    /**
     * Checks if the castling move is possible and returns a value depending on
     * which paths are possible.
     * 3 if castling available both queen side and king side,
     * 2 if castling only available king side,
     * 1 if castling only avaiable queen side.
     */
    public String castlingPossiblePath(Piece piece){
	if (piece.getType() == PieceType.KING && piece.firstStep && piece.getColor() == state) {
	    if (isCastlingLeft(piece) && isCastlingRight(piece)) {
		System.out.println("Båda!");
	        return "both";
	    }
	    else if (isCastlingLeft(piece)) {
		System.out.println("Vänster!");
		return "left";
	    }
	    else if (isCastlingRight(piece)) {
		System.out.println("Höger!");
	        return "right";
	    }
	} return "none";
    }

    /**
     * Is castling queenside possible?
     */
    private boolean isCastlingLeft(final Piece piece) {
	boolean isRookAtStartPosition = getPieceTypeAt(LEFT_ROOK_START_COL, piece.getPieceY()) == PieceType.ROOK;
	boolean isRookFirstStep = square[LEFT_ROOK_START_COL][piece.getPieceY()].isFirstStep();
	boolean isQueenPositionFree = getPieceTypeAt(QUEEN_START_COL, piece.getPieceY()) == PieceType.EMPTY;
	boolean isBishopPositionFree = getPieceTypeAt(LEFT_BISHOP_START_COL, piece.getPieceY()) == PieceType.EMPTY;
	boolean isKnightPositionFree = getPieceTypeAt(LEFT_KNIGHT_START_COL, piece.getPieceY()) == PieceType.EMPTY;

	return isRookAtStartPosition && isRookFirstStep &&
	       isQueenPositionFree && isBishopPositionFree &&
	       isKnightPositionFree;
    }

    /**
     * Is castling kingside possible?
     */
    private boolean isCastlingRight(final Piece piece) {
	boolean isRookAtStartPosition = getPieceTypeAt(RIGHT_ROOK_START_COL, piece.getPieceY()) == PieceType.ROOK;
	//boolean isRookFirstStep = square[RIGHT_ROOK_START_COL][piece.getPieceY()].isFirstStep(); //TODO den här funkar inte???
	boolean isBishopPositionFree = getPieceTypeAt(RIGHT_BISHOP_START_COL, piece.getPieceY()) == PieceType.EMPTY;
	boolean isKnightPositionFree = getPieceTypeAt(RIGHT_KNIGHT_START_COL, piece.getPieceY()) == PieceType.EMPTY;
	boolean test = square[RIGHT_ROOK_START_COL][piece.getPieceY()].isFirstStep();


	return isRookAtStartPosition && square[RIGHT_ROOK_START_COL][piece.getPieceY()].isFirstStep() &&
	       isBishopPositionFree && isKnightPositionFree;
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
	for (int x = 0; x < width; x++) {
	    for (int y = 0; y < height; y++) {
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
