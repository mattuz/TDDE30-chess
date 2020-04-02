package schack;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

import static java.awt.Color.*;
import static java.awt.Color.BLACK;

public class PieceComponent extends JComponent implements BoardListener
{
    public static final int BOARDCONSTANT = 100;
    public static final int CENTERTEXT = 49;
    private Board board;
    private EnumMap<PieceType, String> piece = new EnumMap<>(PieceType.class);
    final private ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("gnome child.png"));

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



    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	piece.put(PieceType.EMPTY, "");
	piece.put(PieceType.PAWN, "P");
	piece.put(PieceType.BISHOP, "B");
	piece.put(PieceType.KING, "K");

	piece.put(PieceType.QUEEN, "Q");       //Vet inte om något av detta kommer att behövas. Vi ska ju ändå ha bilder för varje pjäs.
	piece.put(PieceType.ROOK, "R");
	piece.put(PieceType.KNIGHT, "Kn");

	for (int x = 0; x < board.getWidth(); x++) {
	    for (int y = 0; y < board.getHeight(); y++) {
	        if ((x%2 != 0 && y%2 == 0) || (x%2 == 0 && y%2 != 0) ) {
	            g2d.setColor(gray);
		} else {
		    g2d.setColor(white);
		}
		g2d.fillRect(x * BOARDCONSTANT, y * BOARDCONSTANT,
			     y * BOARDCONSTANT + BOARDCONSTANT, x * BOARDCONSTANT + BOARDCONSTANT);
		g2d.setColor(BLACK);
		g2d.drawRect(x * BOARDCONSTANT, y * BOARDCONSTANT, BOARDCONSTANT,
			     BOARDCONSTANT);
		g2d.drawString(piece.get(board.getPieceAt(x,y)), CENTERTEXT + x * BOARDCONSTANT, CENTERTEXT + y * BOARDCONSTANT);
	    }
	}
    }
}
