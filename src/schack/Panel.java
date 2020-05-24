package schack;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
    {
	static final String WHITE_LABEL = "White's turn";
	static final String BLACK_LABEL = "Black's turn";
	private static String player1 = "";
	private static String player2 = null;
	private static JPanel panel = new JPanel();
	private static JLabel label = new JLabel("White goes first", JLabel.LEFT);

	public Panel() {
	    panel.setLayout(new FlowLayout());
	    panel.add(label);
	    add(panel);
	}
	public static JLabel getLabel(){
	    return label;
	}

	public static void setPlayer1(String name) {
	    player1 = name + "'s trurn";
	}

	public static void setPlayer2(String name) {
	    player2 = name + "'s trurn";
	}

	public static String getPlayer1() {
	    return player1;
	}

	public static String getPlayer2() {
	    return player2;
	}
    }

