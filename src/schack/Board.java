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
    private List<BoardListener> listenerlist = new ArrayList<>();
    private PieceMaker maker = new PieceMaker();
    public List<Piece> pieceList = new ArrayList<>();

    private final static String WHITE_STATE = "white";
    private final static String BLACK_STATE = "black";

    private String state = WHITE_STATE;


    public Board(final int width, final int height) { //Man ska kunna ändra den om man vill
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

    public void playerAssign() { //Eller vill man göra något sånt här?
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 2; y++) {
		getPieceTypeAt(x, y);
	    }
	}
    }

    public void placePieces(int x, int y) {
        if (y == 0) {
            pieceSwitcher(x, y, "black");
	}
        else if (y == 1) {
	    square[x][y] = new Pawn(x,y,PieceType.PAWN,"black",assignPaths("black",PieceType.PAWN), this );
	}
        else if (y == 6) {
	    square[x][y] = new Pawn(x,y,PieceType.PAWN,"white",assignPaths("white",PieceType.PAWN), this);
	}
        else if (y == 7) {
	    pieceSwitcher(x, y, "white");
	}
    }

    public URL assignPaths(String color, PieceType type) { //TODO: Denna ska nog läggas separat. Passar ej in i board.
        URL path;
        switch (type) {
	    case KING:
	        if (color == "white") {
		    path = ClassLoader.getSystemResource("king_w.png");
		} else {
	            path = ClassLoader.getSystemResource("king_b_bra.png");
		}
	        break;
	    case PAWN:
		if (color == "white") {
		    path = ClassLoader.getSystemResource("pawn_w.png");
		} else {
		    path = ClassLoader.getSystemResource("pawn_b.png");
		}
		break;
	    case ROOK:
		if (color == "white") {
		    path = ClassLoader.getSystemResource("rook_w.png");
		} else {
		    path = ClassLoader.getSystemResource("rook_b.png");
		}
		break;
	    case QUEEN:
		if (color == "white") {
		    path = ClassLoader.getSystemResource("queen_w.png");
		} else {
		    path = ClassLoader.getSystemResource("queen_b_bra.png");
		}
		break;
	    case BISHOP:
		if (color == "white") {
		    path = ClassLoader.getSystemResource("bishop_w.png");
		} else {
		    path = ClassLoader.getSystemResource("bishop_b.png");
		}
		break;
	    case KNIGHT:
		if (color == "white") {
		    path = ClassLoader.getSystemResource("knight_w.png");
		} else {
		    path = ClassLoader.getSystemResource("knight_b.png");
		}
		break;
	    default:
		throw new IllegalStateException("Unexpected value: " + type);
	}
	return path;
    }

    public void pieceSwitcher(int x, int y, String color) {
        switch (x) {
	    case 0:
	    case 7:
		square[x][y] = new Rook(x,y,PieceType.ROOK,color, assignPaths(color,PieceType.ROOK), this);
	        break;
	    case 1:
	    case 6:
		square[x][y] = new Knight(x,y,PieceType.KNIGHT,color, assignPaths(color,PieceType.KNIGHT), this);
	        break;
	    case 2:
	    case 5:
		square[x][y] = new Bishop(x,y,PieceType.BISHOP,color,assignPaths(color,PieceType.BISHOP), this);
		break;
	    case 3:
		square[x][y] = new Queen(x,y,PieceType.QUEEN,color,assignPaths(color,PieceType.QUEEN), this);
		break;
	    case 4:
		square[x][y] = new King(x,y,PieceType.KING,color,assignPaths(color,PieceType.KING), this);
		break;
	    default:
	        break;
	}
    }

    public void addBoardListener(BoardListener bl) {
	listenerlist.add(bl);
    }

    public void notifyListeners() {
	for (BoardListener listeners : listenerlist) {
	    listeners.boardChanged();
	}
    }

    public void removePiece(int x, int y) {
        square[x][y] = null;
        notifyListeners();
    }

    public void deadPiece(int x, int y) {
        deadpieces.add(square[x][y]);
    }

    public void changeState() {
        if (state == WHITE_STATE) {
            state = BLACK_STATE;
            Panel.getjLabel().setText(Panel.getPlayer2());
	} else {
            state = WHITE_STATE;
	    Panel.getjLabel().setText(Panel.getPlayer1());
        }
    }

    public boolean isCastlingPossible(){
        if (state == "white"){
	    return getPieceTypeAt(0, 0) == PieceType.ROOK && getPieceTypeAt(4, 0) == PieceType.KING &&
		   getPieceTypeAt(3, 0) == PieceType.EMPTY && getPieceTypeAt(2, 0) == PieceType.EMPTY &&
		   getPieceTypeAt(1, 0) == PieceType.EMPTY;
        } else if(state == "black"){
	    return getPieceTypeAt(0, 7) == PieceType.ROOK && getPieceTypeAt(4, 7) == PieceType.KING &&
		   getPieceTypeAt(3, 7) == PieceType.EMPTY && getPieceTypeAt(2, 7) == PieceType.EMPTY &&
		   getPieceTypeAt(1, 7) == PieceType.EMPTY;
        } else {
            return false;
        }
    }

    public String getState(){
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

}
