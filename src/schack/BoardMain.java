package schack;

/**
 * This is our main class which runs the program.
 * We create a frame with a board and then display it.
 * The program then updates the board when necessary (BoardComponents repaint()).
 */
public class BoardMain
{
    public static void main(String[] args) {
	Board board = new Board(8, 8);
	GameFrame gameFrame = new GameFrame(board);
	board.addBoardListener(gameFrame.getGraphics());
	board.addBoardListener(gameFrame);
	gameFrame.show();

	gameFrame.getGraphics().repaint();
    }

}
