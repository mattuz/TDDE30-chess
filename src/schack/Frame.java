package schack;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the frame that displays the board.
 */
public class Frame implements BoardListener
{
    private JFrame frame = new JFrame();
    private BoardComponent graphics;
    private Panel panel;
    private Board board;


    public Frame(final Board board) {
        this.board = board;
	this.graphics = new BoardComponent(board);
	PieceMover pm = new PieceMover(board);
	frame.addMouseListener(pm);
	frame.addMouseMotionListener(pm);
	panel = new Panel();
    }

    public BoardComponent getGraphics() { //Används för att lägga till en listener.
        return graphics;
    }

    /**
     * Makes the frame with the board visible.
     */
    public void show() {
        frame.setLayout(new BorderLayout());
        frame.add(graphics, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.PAGE_END);
        String player1Name = JOptionPane.showInputDialog("White player: ");
        panel.setPlayer1(player1Name);
        String player2Name = JOptionPane.showInputDialog("Black player: ");
        panel.setPlayer2(player2Name);
        frame.pack();
        frame.setVisible(true);
    }

    @Override public void boardChanged() {
	panel.setTurn(board.getState());
    }
}
