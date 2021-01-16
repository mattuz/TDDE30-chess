package schack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;

public class PiecePainter
{
    //private static URL picturepath = null;
    private EnumMap<PieceType, String> piece = new EnumMap<>(PieceType.class);

    /**
     * Rescales the given image src and gives it the right dimansions.
     */
    public static BufferedImage scale(BufferedImage src, int w, int h) {
	BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	int y;
	int originalW = src.getWidth();
	int originalH = src.getHeight();
	int[] ys = new int[h];
	for (y = 0; y < h; y++)
	    ys[y] = y * originalH / h;
	for (int x = 0; x < w; x++) {
	    int newX = x * originalW / w;
	    for (y = 0; y < h; y++) {
		int col = src.getRGB(newX, ys[y]);
		img.setRGB(x, y, col);
	    }
	}
	return img;
    }

    /**
     * Returns image corresponding to piece.
     */
    public static BufferedImage bufferedImageMaker(Piece piece) {
	BufferedImage myPicture = null;
	try {
	    myPicture = ImageIO.read(piece.getPath());

	} catch (IOException e) {
	    e.printStackTrace();
	}
	return myPicture;
    }
}
