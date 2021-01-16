package schack;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
    {
	//private static final String WHITE_LABEL = "White's turn"; //TODO Används inte?
	//private static final String BLACK_LABEL = "Black's turn";
	private static String player1 = ""; //TODO se om dessa kan ändras från static. Om inte får vi kommentera att de ska vara static för att de inte ska ändras när de
		  		  	     //väl satts..
	private static String player2 = ""; //TODO ta bort dessa och "tvinga" White/Black som namn.
	private static final JPanel J_PANEL = new JPanel(); //TODO ändrade dessa till final enligt kodanalysen.
	private static JLabel jLabel = new JLabel("White goes first", SwingConstants.LEFT);

	public Panel() {
	    J_PANEL.setLayout(new FlowLayout());
	    J_PANEL.add(jLabel);
	    add(J_PANEL);
	}

	public static void setTurn(PieceColor state) {
	    if (state == PieceColor.BLACK) {
	        jLabel.setText(player2);
	    } else {
		jLabel.setText(player1);
	    }
	}

	public static JLabel getJLabel(){
	    return jLabel;
	}

	public static void setPlayer1(String name) {
	    player1 = name + "'s turn";
	}

	public static void setPlayer2(String name) {
	    player2 = name + "'s turn";
	}

	/*public static String getPlayer1() {
	    return player1;
	}*/ //TODO TA BORT DESSA

	/*public static String getPlayer2() {
	    return player2;
	}*/
    }

