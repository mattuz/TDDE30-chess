package schack;

/**
 * Enum for the different pieces that can be placed on the board (including empty pieces).
 * This is the core of our Piece creation and is used to, amongst other things, keep track
 * of the piece's legal moves. 
 */
public enum PieceType
{
    EMPTY, PAWN, KING, QUEEN, KNIGHT, ROOK, BISHOP
}