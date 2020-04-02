package schack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;

import static java.awt.Color.*;
import static java.awt.Color.BLACK;

public class PieceComponent extends JComponent implements BoardListener
{
    public static final int BOARDCONSTANT = 100;
    public static final int CENTERTEXT = 49;
    private Board board;
    private EnumMap<PieceType, String> piece = new EnumMap<>(PieceType.class);
    final private ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("gnome child.png"));
    private String picturepath = null;



    public PieceComponent(final Board board) {
	this.board = board;
    }


    public static BufferedImage scale(BufferedImage src, int w, int h) //Används för att skala om bilden, så att den har rätt dimensioner.
	    								//Behöver kika lite på denna kod, tog ganska mycket hjälp :p
    {
	BufferedImage img =
		new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	int x, y;
	int originalW = src.getWidth();
	int originalH = src.getHeight();
	int[] ys = new int[h];
	for (y = 0; y < h; y++)
	    ys[y] = y * originalH / h;
	for (x = 0; x < w; x++) {
	    int newX = x * originalW / w;
	    for (y = 0; y < h; y++) {
		int col = src.getRGB(newX, ys[y]);
		img.setRGB(x, y, col);
	    }
	}
	return img;
    }

    public String pieceSelector(PieceType piece) {
        switch (piece) {
	    case PAWN:
	         picturepath = "C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\pawn";
	         break;
	     case
	}
    }

    public BufferedImage bufferedImageMaker() { //"Skapar" en bild, tar den från Pics :)
        //Path path = FileSystems.getDefault().getPath("C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\"); //Detta gick inte så bra :(
        //String pathstring = path.toString();
	BufferedImage myPicture = null;
	try {
	    myPicture = ImageIO.read(new File("C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\gnome child.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return myPicture;
    }

    @Override public void boardChanged() {
	repaint();
    }

    @Override public Dimension getPreferredSize() {
	super.getPreferredSize();
	return new Dimension((board.getWidth()) * BOARDCONSTANT,
			     (board.getHeight()) * BOARDCONSTANT);
    }


    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	piece.put(PieceType.EMPTY, " ");
	piece.put(PieceType.PAWN, "P");
	piece.put(PieceType.BISHOP, "B");
	piece.put(PieceType.KING, "K");

	piece.put(PieceType.QUEEN, "Q");       //Vet inte om något av detta kommer att behövas. Vi ska ju ändå ha bilder för varje pjäs.
	piece.put(PieceType.ROOK, "R");
	piece.put(PieceType.KNIGHT, "Kn");

	for (int x = 0; x < board.getWidth(); x++) {
	    for (int y = 0; y < board.getHeight(); y++) {
	        int positionX = x * BOARDCONSTANT;
		int positionY = y * BOARDCONSTANT;

	        if ((x%2 != 0 && y%2 == 0) || (x%2 == 0 && y%2 != 0) ) {
	            g2d.setColor(gray);
		} else {
		    g2d.setColor(white);
		}

		g2d.fillRect(positionX, positionY,
			     positionY + BOARDCONSTANT,
			     positionX + BOARDCONSTANT);
		g2d.setColor(BLACK);
		g2d.drawRect(positionX, positionY, BOARDCONSTANT,
			     BOARDCONSTANT);
		g2d.drawString(piece.get(board.getPieceAt(x,y)), CENTERTEXT + positionX, CENTERTEXT + positionY);

		if (board.getPieceAt(x,y) == PieceType.PAWN) {
		    g2d.drawImage((scale(bufferedImageMaker(), BOARDCONSTANT, BOARDCONSTANT)), positionX, positionY,
				  this);
		}
	    }
	}
    }
}
