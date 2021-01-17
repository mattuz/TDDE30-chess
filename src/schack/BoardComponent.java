package schack;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

/**
 * Graphical component for the board. Used to draw the board and show the different
 * colored squares. Also draws the pieces by scaling the picture belonging
 * to the specific piece.
 */
public class BoardComponent extends JComponent implements BoardListener {
	private static final int BOARD_CONSTANT = 80;
	private Board board;

	public BoardComponent(final Board board) {
		this.board = board;
	}

	/**
	 * Repaints the board.
	 */
	@Override
	public void boardChanged() {
		repaint();
	}

	/**
	 *Generates the appropriate dimensions of the board in the frame.
	 */
	@Override
	public Dimension getPreferredSize() {
		super.getPreferredSize();
		return new Dimension((board.getWidth()) * BOARD_CONSTANT,
				     (board.getHeight()) * BOARD_CONSTANT);
	}

	/**
	 * Paints the graphical component representing the board.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				int positionX = x * BOARD_CONSTANT;
				int positionY = y * BOARD_CONSTANT;

				if ((x % 2 != 0 && y % 2 == 0) || (x % 2 == 0 && y % 2 != 0)) {
					g2d.setColor(gray);
				} else {
					g2d.setColor(white);
				}

				g2d.fillRect(positionX, positionY,
					     positionY + BOARD_CONSTANT,
					     positionX + BOARD_CONSTANT);
				g2d.setColor(BLACK);
				g2d.drawRect(positionX, positionY, BOARD_CONSTANT, BOARD_CONSTANT);
				if (board.getPieceTypeAt(x, y) != PieceType.EMPTY) { //Vill bara måla om det finns en pjäs där.
					g2d.drawImage((PiecePainter.scale(PiecePainter.bufferedImageMaker(board.getPieceAt(x, y)
						      ), BOARD_CONSTANT, BOARD_CONSTANT)), positionX,
						      positionY, this);
				}
			}
		}
	}

	public static int getboardconstant() {
		return BOARD_CONSTANT;
	}
}

