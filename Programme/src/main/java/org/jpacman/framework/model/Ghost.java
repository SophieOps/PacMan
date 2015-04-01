package org.jpacman.framework.model;

/**
 * A ghost element on the board.
 * 
 * @author Arie van Deursen, TU Delft, Feb 10, 2012
 */
public class Ghost extends Sprite {

    private int idGhost;
    private static int nbGhost = 0;

	public Ghost () {
        idGhost = nbGhost;
        nbGhost++;
    }

    /**
	 * @return That this sprite is a ghost.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.GHOST;
	}

    /**
     *
     * @return number of instance
     */
    public static int getNbGhost() {
        return nbGhost;
    }

    /**
     *
     * @return the id of ghost
     */
    public int getIdGhost() {
        return idGhost;
    }
}
