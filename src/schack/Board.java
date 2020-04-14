package schack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board
{
    private  int width;
    private  int height;
    private PieceType[][] square;
    private List<PieceType> deadpieces = new ArrayList<>(); //Vet inte varför den är markerad. Verkar fungera som det ska.
    private List<BoardListener> listenerlist = new ArrayList<>();

    public Board(final int width, final int height) { //Man ska kunna ändra den om man vill
	this.width = width;
	this.height = height;
	this.square = new PieceType[width][height]; //TODO Se till att det finns ett fält i spelpjäsen som bestämmer vilken spelare den tillhör.
	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		square[i][j] = PieceType.EMPTY;
		placePieces(i,j);

	    }
	}

    }

    public void playerAssign() { //Eller vill man göra något sånt här?
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 2; y++) {
		getPieceAt(x, y);
	    }
	}
    }

    public PieceType getPieceAt(int x, int y) {
        return square[x][y];
    }

    public void placePieces(int x, int y) {
        if (y == 0) {
            pieceSwitcher(x, y);
	}
        else if (y == 1) {
	    square[x][y] = PieceType.PAWN;
	    //square[x][y] = new Pawn("black", x, y); //Vill man göra såhär?
	}
        else if (y == 6) {
	    square[x][y] = PieceType.PAWN;
	}
        else if (y == 7) {
	    pieceSwitcher(x, y);
	}

    }

    public void pieceSwitcher(int x, int y) {
        switch (x) {
	    case 0:
	    case 7:
		square[x][y] = PieceType.ROOK;
	        break;
	    case 1:
	    case 6:
		square[x][y] = PieceType.KNIGHT;
	        break;
	    case 2:
	    case 5:
		square[x][y] = PieceType.BISHOP;
	        break;
	    case 3:
	        square[x][y] = PieceType.QUEEN;
	        break;
	    case 4:
	        square[x][y] = PieceType.KING;
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
        deadpieces.add(getPieceAt(x, y));
        square[x][y] = PieceType.EMPTY;
        notifyListeners();
    }


    public PieceType[][] getSquare() {
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
