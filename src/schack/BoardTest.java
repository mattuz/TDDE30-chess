package schack;

public class BoardTest
{
    public static void main(String[] args) {
	Board b1 = new Board(8, 8);

	Frame f1 = new Frame(b1);
	//PieceMove pm = new PieceMove(b1,f1.getGraphics());


	b1.addBoardListener(f1.getGraphics());
	f1.show();


	System.out.println(b1.getDeadpieces()); //Visar alla "döda" pjäser.
	//System.out.println(BoardToText.convertToTextBoard(b1));
	System.out.println(b1.getPieceTypeAt(0, 1));
	System.out.println(b1.getPieceAt(0,1));
	b1.movePieceTest(0,1);
	System.out.println(b1.getPieceTypeAt(0, 1));
	System.out.println(b1.getPieceAt(0,1));



	f1.getGraphics().repaint();
    }

}
