package schack;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the chessboard and managing its components.
 * Places pieces on board so that they can be drawn out. Keeps track of pieces positioning and
 * allows for, for example, deletion of pieces that are taken.
 */
public class Board
{
    private  int width; //Klagar på "Many fields" i kodanalysen. Vi ser ingen väg runt detta
    private  int height; //då vi anser att samtliga fält är nödvändiga.
    private Piece[][] square;
    private PieceType[][] enumsquare;
    private List<Piece> deadPieces = new ArrayList<>();
    private List<BoardListener> listeners = new ArrayList<>();
    private List<Piece> pieces = new ArrayList<>();
    private Piece checkPiece = null;
    private Piece blackKing = null; //"RelatedFieldNames" med nedan. Kommer inte undan detta
    private Piece whiteKing = null; //då vi vill kunna representera både den vita och svarta kungen.
    private PieceColor state = WHITE_STATE;

    //Nedan har liknande namn för att de behöver beskriva sin position.
    //Går inte att inte ha liknande namn, och vi ser inte hur det skulle ge något
    //att kombinera dem till ett namn (som det står i kodanalysen).
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
    private static final PieceColor WHITE_STATE = PieceColor.WHITE; //Kommer inte undan att denna och nedan har liknande
    								    //namn. Vi vill ha det så att vi har ett white och ett
    								    //black state.
    /**
     * Represents the state of the game. Black's turn.
     */
    private static final PieceColor BLACK_STATE = PieceColor.BLACK;




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
		    pieces.add(square[x][y]);
		}
	    }
	}
	return pieces;
    }

    /**
     * Places Pieces on the Board array.
     * Uses pieceSwitcher to determine and create the correct Piece.
     */
    public void placePieces(int x, int y) {
	if (y == BLACK_STARTING_ROW) {
            switchPieces(x, y, PieceColor.BLACK);
	}
        else if (y == BLACK_PAWN_STARTING_ROW) { //Liknande grenar nedan, blir likt då det är bönder som ska placeras
            					 // på svart respektive vit sida.
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
		if (color == PieceColor.BLACK) {
		    blackKing = square[x][y];
		} else if (color == PieceColor.WHITE) {
		    whiteKing = square[x][y];
		}
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
	pieces.remove(square[x][y]);
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
    }

    /**
     * Checks if a Pawn can be upgraded (if it has moved across the entire board).
     *
     */
    public boolean isPawnUpgradePossible(Piece piece, int y) {
	if (piece.getType() != PieceType.PAWN) { //Måste vara en pawn för att det ska gå. Därför vi har denna check.
	    return false;
	}
        else if (piece.getColor() == PieceColor.WHITE && y == BLACK_STARTING_ROW) {
	    return true;
	}
	else return piece.getColor() == PieceColor.BLACK && y == WHITE_STARTING_ROW;
    }

    /**
     * Checks if the castling move is possible and returns an accurate
     * string depending on which paths are possible.
     *
     */
    public String castlingPossiblePath(Piece piece){
	if (piece.getType() == PieceType.KING && piece.firstStep && piece.getColor() == state) { //Måste vara en kung, annars
	    										//får man inte göra castling.
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
    private boolean isCastlingLeft(final Piece piece) { //Vi bröt upp så att vi kollar castling åt det ena eller det andra hållet.
        						//Därför har de lika namn, vilket vi då inte ändrat på.
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
	boolean isBishopPositionFree = getPieceTypeAt(RIGHT_BISHOP_START_COL, piece.getPieceY()) == PieceType.EMPTY;
	boolean isKnightPositionFree = getPieceTypeAt(RIGHT_KNIGHT_START_COL, piece.getPieceY()) == PieceType.EMPTY;


	return isRookAtStartPosition &&
	       square[RIGHT_ROOK_START_COL][piece.getPieceY()].isFirstStep() //Försökte göra som ovan med denna, men av någon anledning gick det inte.
	       && isBishopPositionFree && isKnightPositionFree;		     // Så det fick bli såhär.
    }

    /**
     * Checks if current piece is checked (used to determine if king is in check).
     * Loops through all pieces on the board to check if any of them has a legal move
     * that attacks the king.
     */
    public boolean isChecked(Piece piece) {
	Position position = new Position(piece.getPieceX(), piece.getPieceY());
	if (piece.getType() == PieceType.KING) {
	    for (Piece p : pieces) {
		if (p.getType() == PieceType.PAWN && p.getColor() != piece.getColor()) {
		    if (isCheckedByPawn(p, piece)) {
			return true;
		    }
		} else if (p.color != piece.color && containsPosition(p.getLegalMoves(), position)) {
		    checkPiece = p;
		    if (checkPiece.getType() == PieceType.PAWN && checkPiece.getPieceX() == piece.getPieceX()) {
			return false;
		    } else {
			return true;
		    }
		}
	    }
	}
	return false;
    }

	/**
	 * Checks if the king will be checked by a pawn(piece).
	 */
	private boolean isCheckedByPawn(Piece piece, Piece king) {
	    if (king.getColor() == PieceColor.WHITE) {
		if (piece.getPieceY() == king.getPieceY() - 1 && Math.abs(piece.getPieceX() - king.getPieceX()) == 1) {
		    checkPiece = piece;
		    return true;
		}
	    } else if (king.getColor() == PieceColor.BLACK) {
		if (piece.getPieceY() == king.getPieceY() + 1 && Math.abs(piece.getPieceX() - king.getPieceX()) == 1) {
		    checkPiece = piece;
		    return true;
		}
	    }
	    return false;
	}


    /**
     * Checks if a piece can interrupt the check of the king, and move inbetween.
     * Compares the values of the piece's eventual x,y and calculates if this would
     * put it in the way of the king's attacker.
     */
    public boolean interruptChecked(Piece king, Piece interruptPiece, int newX, int newY) { //Försökte bryta ut för att minska
        									//komplexitet. Lyckades inte..
	int kingX = king.getPieceX();
	int kingY = king.getPieceY();
	boolean horizontalCondition = isChecked(king) && checkPiece.getPieceX() != kingX && checkPiece.getPieceY() == kingY;
	boolean verticalCondition = isChecked(king) && checkPiece.getPieceX() == kingX && checkPiece.getPieceY() != kingY;
	boolean diagonalCondition = isChecked(king) &&  Math.abs(kingX - checkPiece.getPieceX()) -
							Math.abs(kingY - checkPiece.getPieceY()) == 0
				    		    && Math.abs(newX - kingX) - Math.abs(newY - kingY) == 0;

	Position interruptPosition = new Position(newX, newY);
	if (interruptPiece.legalMoves.contains(interruptPosition)) {
	    for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
		    if (horizontalCondition) {
			if (checkPiece.getPieceX() <= newX && newX < kingX && newY == kingY) {
			    return true;
			} else return checkPiece.getPieceX() >= newX && newX > kingX && newY == kingY;
		    } else if (verticalCondition) {
			if (checkPiece.getPieceY() <= newY && newY < kingY && newX == kingX) {
			    return true;
			} else return checkPiece.getPieceY() >= newY && newY > kingY && newX == kingX;
		    } else if (diagonalCondition) {
			return Math.abs(newX - kingX) < Math.abs(checkPiece.getPieceX() - kingX) &&
			       Math.abs(newY - kingY) < Math.abs(checkPiece.getPieceY() - kingY);
		    }
		}
	    }
	}
	return false;
    }


    public boolean isCheckMate(Piece king){
        boolean checkMateInterrupt = true;
        boolean checkMateKing = false;
	if (king.legalMoves.isEmpty()) {
	    checkMateKing = true;
	}
	for (Piece interruptPiece : pieces) {
	    if (interruptPiece.color == king.color) {
		for (int i = 0; i < interruptPiece.legalMoves.size(); i++) {
		    if (interruptChecked(king, interruptPiece, interruptPiece.getLegalMoves().get(i).getX(),
				     interruptPiece.getLegalMoves().get(i).getY())) {
		        checkMateInterrupt = false;
		    }
		}
	    }
	}
	return checkMateInterrupt && checkMateKing;
    }

    /**
     * Checks if a piece has the location in its legal moves.
     */
    public boolean containsPosition(List<Position> positions, Position pos){
	Boolean doesContain = false;
	for (Position elem: positions) {
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


    public PieceType getPieceTypeAt(int x, int y) {
	if (square[x][y] != null) {
	    return square[x][y].getType();
	}
	return enumsquare[x][y];
    }

    public Piece getPieceAt(int x, int y) {
	return square[x][y];
    }

    public List<Piece> getPieces() {
	return pieces;
    }

    public Piece getWhiteKing() { //Liknande namn pga samma som står i deklarationen ovan.
	return whiteKing;
    }

    public Piece getBlackKing() {
	return blackKing;
    }
}
