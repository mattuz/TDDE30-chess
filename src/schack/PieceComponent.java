package schack;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

import static java.awt.Color.*;

public class PieceComponent extends JComponent implements BoardListener
{
    public static final int BOARDCONSTANT = 80;
    public static final int CENTERTEXT = 49;
    private Board board;
    private Piece pieces = null;

    public PieceComponent(final Board board) {
	this.board = board;
    }

    @Override public void boardChanged() {
	repaint();
    }

    @Override public Dimension getPreferredSize() {
	super.getPreferredSize();
	return new Dimension((board.getWidth()) * BOARDCONSTANT,
			     (board.getHeight()) * BOARDCONSTANT);
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int x = 0; x < board.getWidth(); x++) {
	    for (int y = 0; y < board.getHeight(); y++) {
	        int positionX = x * BOARDCONSTANT;
		int positionY = y * BOARDCONSTANT;

	        if ((x%2 != 0 && y%2 == 0) || (x%2 == 0 && y%2 != 0) ) {
	            g2d.setColor(gray);
		} else {
		    g2d.setColor(white);
		}

		g2d.fillRect(positionX, positionY,
			     positionY + BOARDCONSTANT,
			     positionX + BOARDCONSTANT);
		g2d.setColor(BLACK);
		g2d.drawRect(positionX, positionY, BOARDCONSTANT,
			     BOARDCONSTANT);
		if (board.getPieceTypeAt(x, y) != PieceType.EMPTY) {
		    g2d.drawImage((PiecePainter.scale(PiecePainter.bufferedImageMaker(board.getPieceAt(x, y)), BOARDCONSTANT, BOARDCONSTANT)), positionX,
				  positionY, this);
		}
		}
	    }
	}

    public static int getBOARDCONSTANT() {
	return BOARDCONSTANT;
    }
}

