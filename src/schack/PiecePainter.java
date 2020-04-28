package schack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;

public class PiecePainter
{
    private static URL picturepath = null;
    private EnumMap<PieceType, String> piece = new EnumMap<>(PieceType.class);


    public static BufferedImage scale(BufferedImage src, int w, int h) //Används för att skala om bilden, så att den har rätt dimensioner.
    //Behöver kika lite på denna kod, tog ganska mycket hjälp :p
    {
	BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
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

//    public static URL pieceSelector(PieceType piece) {
//	switch (piece) {
//	    case PAWN:
//		picturepath = Pawn.getPath();
//		break;
//	    case KING:
//		picturepath = King.getPath();
//		break;
//	    case ROOK:
//		picturepath = Rook.getPath();
//		break;
//	    case QUEEN:
//		picturepath = Queen.getPath();
//		break;
//	    case BISHOP:
//		picturepath = Bishop.getPath();
//		break;
//	    case KNIGHT:
//		picturepath = Knight.getPath();
//		break;
//	    default:
//		break;
//	}
//	return picturepath;
//    }

    public static BufferedImage bufferedImageMaker(Pieces piece) { //"Skapar" en bild, tar den från Pics :)
	//Path path = FileSystems.getDefault().getPath("C:\\Users\\Matth\\IdeaProjects\\tdde30-projekt-2020-d1-g23-09\\Pics\\"); //Detta gick inte så bra :(
	//String pathstring = path.toString();
	BufferedImage myPicture = null;
	try {
	    myPicture = ImageIO.read(piece.getPath()/*pieceSelector(piece.getType())*/);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return myPicture;
    }
}
