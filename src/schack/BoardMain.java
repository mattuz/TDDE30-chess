package schack;

/**
 * This is our main class which runs the program.
 * We create a frame with a board and then display it.
 * The program then updates the board when necessary (BoardComponents repaint()).
 */
public class BoardMain
{
    public static void main(String[] args) {

	Frame frame = new Frame(new Board(8, 8));

	frame.show();

	frame.getGraphics().repaint();
    }

}
