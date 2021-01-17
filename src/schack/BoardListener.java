package schack;

/**
 * Listener for the board. Used every
 * time a change is made (i.e. a piece has been moved).
 * This prompts the board to redraw if necessary to correctly display
 * the changed board.
 */
public interface BoardListener
{
    void boardChanged();
}
