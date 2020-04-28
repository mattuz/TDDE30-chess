package schack;

public class BoardToText
{
    public static String convertToTextBoard(Board board) {
	StringBuilder textboard = new StringBuilder();
	for (int i = 0; i < board.getHeight(); i++) {
	    for (int j = 0; j < board.getWidth(); j++) {
		switch (board.getPieceTypeAt(j, i)) {
		    case EMPTY:
			textboard.append(" - ");
			break;
		    case ROOK:
			textboard.append(" R ");
			break;
		    case KNIGHT:
			textboard.append(" k ");
			break;
		    case KING:
			textboard.append(" K ");
			break;
		    case QUEEN:
			textboard.append(" Q ");
			break;
		    case PAWN:
			textboard.append(" P ");
			break;
		    case BISHOP:
			textboard.append(" B ");
			break;
		    default:
			return "Detta bör ej ske.";
		}
	    }
	    textboard.append("\n");
	}
	textboard.append("\n");
	String result = textboard.toString();
	return result;
    }
}
