package schack;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
    {
	static final String WHITE_LABLE = "White's turn";
	static final String BLACK_LABLE = "Black's turn";
	private static JPanel panel = new JPanel();
	private static JLabel label = new JLabel(WHITE_LABLE, JLabel.LEFT);

	public Panel() {
	    panel.setLayout(new FlowLayout());
	    panel.add(label);
	    add(panel);
	}
    }

