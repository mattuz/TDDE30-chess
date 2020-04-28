package schack;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    private  int width;
    private  int height;
    private Pieces[][] square;
    private PieceType[][] enumsquare;
    private List<PieceType> deadpieces = new ArrayList<>(); //Vet inte varför den är markerad. Verkar fungera som det ska.
    private List<BoardListener> listenerlist = new ArrayList<>();
    private PieceMaker maker = new PieceMaker();

    public Board(final int width, final int height) { //Man ska kunna ändra den om man vill
	this.width = width;
	this.height = height;
	this.square = new Pieces[width][height];
	this.enumsquare = new PieceType[width][height];
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x < width; x++) {
		//square[x][y] = PieceType.EMPTY;
		enumsquare[x][y] = PieceType.EMPTY;
		placePieces(x,y);
		/*if (y < 2){
		    maker.pieceCreator(x, y, getPieceAt(x,y).getType(), "black");
		    System.out.println("svart");
		}else if (y > 5){
		    maker.pieceCreator(x, y, getPieceAt(x,y).getType(), "white");
		    System.out.println("vit");
		}*/
	    }
	}
    }

    public void playerAssign() { //Eller vill man göra något sånt här?
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 2; y++) {
		getPieceTypeAt(x, y);
	    }
	}
    }

    public PieceType getPieceTypeAt(int x, int y) {
        if (square[x][y] != null) {
	    System.out.println("Här finns ett objekt");
	    return square[x][y].getType();
	}
        return enumsquare[x][y];
    }

    public Pieces getPieceAt(int x, int y) {
        return square[x][y];
    }

    public void placePieces(int x, int y) {
        if (y == 0) {
            pieceSwitcher(x, y, "black");
	}
        else if (y == 1) {
	    //square[x][y] = PieceType.PAWN;
	    square[x][y] = new Pawn(x,y,PieceType.PAWN,"black");

	    //square[x][y] = new Pawn("black", x, y); //Vill man göra såhär?
	}
        else if (y == 6) {
	  //  square[x][y] = PieceType.PAWN;
	    square[x][y] = new Pawn(x,y,PieceType.PAWN,"white");
	}
        else if (y == 7) {
	    pieceSwitcher(x, y, "white");
	}

    }

    public void pieceSwitcher(int x, int y, String color) {
        switch (x) {
	    case 0:
	    case 7:
		//square[x][y] = PieceType.ROOK;
		square[x][y] = new Rook(x,y,PieceType.ROOK,color);
	        break;
	    case 1:
	    case 6:
		//square[x][y] = PieceType.KNIGHT;
		square[x][y] = new Knight(x,y,PieceType.KNIGHT,color);
	        break;
	    case 2:
	    case 5:
		//square[x][y] = PieceType.BISHOP;
		square[x][y] = new Bishop(x,y,PieceType.BISHOP,color);
		break;
	    case 3:
	        //square[x][y] = PieceType.QUEEN;
		square[x][y] = new Queen(x,y,PieceType.QUEEN,color);
		break;
	    case 4:
	       // square[x][y] = PieceType.KING;
		square[x][y] = new King(x,y,PieceType.KING,color);
		break;
	    default:
	        break;
	}
    }

    public void addBoardListener(BoardListener bl) {
	listenerlist.add(bl);
	System.out.println("Det finns en listener nu");
    }

    private void notifyListeners() {
	for (BoardListener listeners : listenerlist) {
	    listeners.boardChanged();
	}
    }

    public void removePiece(int x, int y) {
        //deadpieces.add(getPieceAt(x, y));
       // square[x][y] = PieceType.EMPTY;
        notifyListeners();
    }


    public Pieces[][] getSquare() {
	return square;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public List<PieceType> getDeadpieces() {
	return deadpieces;
    }
}
