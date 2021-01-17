package schack;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the panels in the frame. This changes depending on which player's
 * turn it is (state in board).
 */
public class Panel extends JPanel
    {

	//private static String player1 = "White"; //TODO se om dessa kan ändras från static. Om inte får vi kommentera att de ska vara static för att de inte ska ändras när de
		  		  	     //väl satts..
	//private static String player2 = "Black"; //TODO ta bort dessa och "tvinga" White/Black som namn.
	private static final JPanel PANEL = new JPanel(); //TODO ändrade dessa till final enligt kodanalysen.
	private static final JLabel LABEL = new JLabel("White goes first", SwingConstants.LEFT);

	public Panel() {
	    PANEL.setLayout(new FlowLayout());
	    PANEL.add(LABEL);
	    add(PANEL);
	}

		/**
		 * Updates the label that shows who's turn it is.
		 */
	public static void setTurn(PieceColor state) {
	    if (state == PieceColor.BLACK) {
	        LABEL.setText("Black's turn");
	    } else {
		LABEL.setText("White's turn");
	    }
	}

	/*public static void setPlayer1(String name) {
	    player1 = name + "'s turn";
	}

	public static void setPlayer2(String name) {
	    player2 = name + "'s turn";
	}*/

    }

