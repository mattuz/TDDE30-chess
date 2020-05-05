package schack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Frame extends AbstractAction
{
    private JFrame frame = new JFrame();
    private Board board;
    private JTextArea textarea = null;
    private PieceComponent graphics;


    public Frame(final Board board) {
	this.graphics = new PieceComponent(board);
	this.board = board;
    }

    public PieceComponent getGraphics() { //Används för att lägga till en listener.
        return graphics;
    }

    public void show() {
        frame.setLayout(new BorderLayout());
        frame.add(graphics, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void keyBindings() {
        InputMap im = graphics.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = graphics.getActionMap();


        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
        im.put(KeyStroke.getKeyStroke(MouseEvent.BUTTON1, 0), "LeftButton");
    }

    @Override public void actionPerformed(final ActionEvent actionEvent) {

    }
}
