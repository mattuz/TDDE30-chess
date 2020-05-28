package schack;

import javax.swing.*;
import java.awt.*;

public class Frame
{
    private JFrame frame = new JFrame();
    private Board board;
    private JTextArea textarea = null;
    private PieceComponent graphics;


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
        frame.add(new Panel(), BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);

    }




}
