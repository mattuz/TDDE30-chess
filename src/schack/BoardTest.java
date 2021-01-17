package schack;

public class BoardTest
{
    public static void main(String[] args) {

	Frame frame = new Frame(new Board(8, 8));

	frame.show();

	frame.getGraphics().repaint();
    }

}
