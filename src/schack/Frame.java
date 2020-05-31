package schack;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;

public class Frame
{
    private JFrame frame = new JFrame();
    private Board board;
    private JTextArea textarea = null;
    private PieceComponent graphics;
    private Panel p = new Panel();



    public Frame(final Board board) {
	this.graphics = new PieceComponent(board);
	this.board = board;
	PieceMove pm = new PieceMove(board, graphics);
	frame.addMouseListener(pm);
	frame.addMouseMotionListener(pm);
    }

    public PieceComponent getGraphics() { //Används för att lägga till en listener.
        return graphics;
    }

    public void show() {
        frame.setLayout(new BorderLayout());
        frame.add(graphics, BorderLayout.CENTER);
        frame.add(p, BorderLayout.PAGE_END);
	String p1 = JOptionPane.showInputDialog("White player: ");
	Panel.setPlayer1(p1);
	String p2 = JOptionPane.showInputDialog("Black player: ");
	Panel.setPlayer2(p2);
	frame.pack();
        frame.setVisible(true);

    }




}
