package schack;

import javax.swing.*;
import java.awt.*;

public class Frame
{
    private JFrame frame = new JFrame();
    private Board board;
    private JTextArea textarea = null;
    private BoardComponent graphics;
    private Panel panel = new Panel();



    public Frame(final Board board) {
	this.graphics = new BoardComponent(board);
	this.board = board;
	PieceMove pm = new PieceMove(board, graphics);
	frame.addMouseListener(pm);
	frame.addMouseMotionListener(pm);
    }

    public BoardComponent getGraphics() { //Används för att lägga till en listener.
        return graphics;
    }

    public void show() {
        frame.setLayout(new BorderLayout());
        frame.add(graphics, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.PAGE_END);
	String player1Name = JOptionPane.showInputDialog("White player: ");
	Panel.setPlayer1(player1Name);
	String player2Name = JOptionPane.showInputDialog("Black player: ");
	Panel.setPlayer2(player2Name);
	frame.pack();
        frame.setVisible(true);

    }




}
