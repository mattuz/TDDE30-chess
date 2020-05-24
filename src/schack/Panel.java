package schack;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
    {
	static final String WHITE_LABEL = "White's turn";
	static final String BLACK_LABEL = "Black's turn";
	private static JPanel panel = new JPanel();
	private static JLabel label = new JLabel(WHITE_LABEL, JLabel.LEFT);

	public Panel() {
	    panel.setLayout(new FlowLayout());
	    panel.add(label);
	    add(panel);
	}
	public static JLabel getLabel(){
	    return label;
	}
    }

