package schack;

public class BoardTest
{
    public static void main(String[] args) {
	//Board b1 = new Board(8, 8);


	Frame frame = new Frame(new Board(8, 8));
	//PieceMove pm = new PieceMove(b1,f1.getGraphics());

	//b1.addBoardListener(f1.getGraphics());
	frame.show();

	frame.getGraphics().repaint();
    }

}
