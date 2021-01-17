package schack;

/**
 * Listener for the board. Used every
 * time a change is made (i.e. a piece has been moved).
 * This prompts the board to redraw if necessary to correctly display
 * the changed board.
 */
public interface BoardListener //Står att vi har "SimilarChildren" pga båda har liknande implementation av board.
                               //Vi anser inte att de är tillräckligt lika för att man ska slå samman dem, då de enligt oss utför
                                //olika saker.
{
    void boardChanged();
}
