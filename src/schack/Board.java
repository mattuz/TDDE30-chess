package schack;

public class Board
{
    private  int width;
    private  int height;
    private PieceType[][] square;

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

    public PieceType getPieceAt(int x, int y) {
        return square[x][y];
    }

    public void placePieces(int x, int y) {
        if (y == 0) {
            pieceSwitcher(x, y);
	}
        else if (y == 1) {
	    square[x][y] = PieceType.PAWN;
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
	    case 5:
		square[x][y] = PieceType.ROOK;
	        break;
	    case 1:
	    case 6:
		square[x][y] = PieceType.KNIGHT;
	        break;
	    case 2:
	    case 7:
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

    public PieceType[][] getSquare() {
	return square;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }
}
