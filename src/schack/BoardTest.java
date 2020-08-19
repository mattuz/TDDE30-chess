package schack;

public class BoardTest
{
    public static void main(String[] args) {
	Board b1 = new Board(8, 8);


	Frame f1 = new Frame(b1);
	//PieceMove pm = new PieceMove(b1,f1.getGraphics());

	b1.addBoardListener(f1.getGraphics());
	f1.show();

	f1.getGraphics().repaint();
    }

}
