package schack;

import javax.swing.*;
import java.awt.*;

public class Frame
{
    private JFrame frame = new JFrame();
    //private Board board;
    private JTextArea textarea;
    private PieceComponent graphics;


    public Frame(final Board board) {
	this.graphics = new PieceComponent(board);
    }

    public void show() {
        frame.setLayout(new BorderLayout());
        frame.add(graphics, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
