package schack;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the panels in the frame. This changes depending on which player's
 * turn it is (state in board).
 */
public class Panel extends JPanel
    {

	private String player1 = "White"; //TODO se om dessa kan ändras från static. Om inte får vi kommentera att de ska vara static för att de inte ska ändras när de
		  		  	     //väl satts..
	private String player2 = "Black"; //TODO ta bort dessa och "tvinga" White/Black som namn.
	private JPanel panel = new JPanel(); //TODO ändrade dessa till final enligt kodanalysen.
	private JLabel label = new JLabel("White goes first", SwingConstants.LEFT);

	public Panel() {
	    panel.setLayout(new FlowLayout());
	    panel.add(label);
	    add(panel);
	}

		/**
		 * Updates the label that shows who's turn it is.
		 */
	public void setTurn(PieceColor color) {
	    if (color == PieceColor.BLACK) {
	        label.setText(player2);
	    } else {
		label.setText(player1);
	    }
	}

	public void setPlayer1(String name) {
	    player1 = name + "'s turn";
	}

	public void setPlayer2(String name) {
	    player2 = name + "'s turn";
	}

    }

