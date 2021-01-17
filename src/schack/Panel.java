package schack;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the panels in the frame. This changes depending on which player's
 * turn it is (state in board).
 */
public class Panel extends JPanel
    {

	private String player1 = ""; //Dessa har ändrats så att de inte längre är static.
	private String player2 = ""; //Kodanalysen klagar på att namnen är lika, men vi känner att det inte är nödvändigt att
				     // ändra till något annat då det är pedagogiskt nog som det är med player1 och player2.
					//De används för övrigt inte mer än här och i frame (när man ska bestämma namnen).
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel("White starts.", SwingConstants.LEFT);

	public Panel() {
	    panel.setLayout(new FlowLayout());
	    panel.add(label);
	    add(panel);
	}

		/**
		 * Updates the label that shows who's turn it is.
		 */
	public void setTurn(PieceColor color) {
	    if (color != PieceColor.BLACK) {
	        label.setText(player2 + "'s turn");
	    } else {
		label.setText(player1 + "'s turn");
	    }
	}

	public void setPlayer1(String name) {
	    player1 = name;
	}

	public void setPlayer2(String name) {
	    player2 = name;
	}

    }

