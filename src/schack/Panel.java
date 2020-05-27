package schack;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
    {
	static final String WHITE_LABEL = "White's turn";
	static final String BLACK_LABEL = "Black's turn";
	private static String player1 = "";
	private static String player2 = null;
	private static final JPanel J_PANEL = new JPanel(); //TODO ändrade dessa till final enligt kodanalysen.
	private static final JLabel J_LABEL = new JLabel("White goes first", SwingConstants.LEFT);

	public Panel() {
	    J_PANEL.setLayout(new FlowLayout());
	    J_PANEL.add(J_LABEL);
	    add(J_PANEL);
	}
	public static JLabel getjLabel(){
	    return J_LABEL;
	}

	public static void setPlayer1(String name) {
	    player1 = name + "'s turn";
	}

	public static void setPlayer2(String name) {
	    player2 = name + "'s turn";
	}

	public static String getPlayer1() {
	    return player1;
	}

	public static String getPlayer2() {
	    return player2;
	}
    }

