package schack;

public class BoardTest
{
    public static void main(String[] args) {
	Board b1 = new Board(8, 8);
	/*for (int i = 0; i < b1.getWidth(); i++) {
	    for (int j = 0; j < b1.getHeight() ; j++) {
		b1.placePieces(i, j);
	    }

	}*/
	Frame f1 = new Frame(b1);
	b1.addBoardListener(f1.getGraphics());
	f1.show();


	System.out.println(b1.getDeadpieces()); //Visar alla "döda" pjäser.
	System.out.println(BoardToText.convertToTextBoard(b1));
	System.out.println(b1.getPieceAt(0,0));

    }

}
