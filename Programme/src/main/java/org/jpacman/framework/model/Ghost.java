package org.jpacman.framework.model;

import java.awt.Color;

/**
 * A ghost element on the board.
 * 
 * @author Arie van Deursen, TU Delft, Feb 10, 2012
 */
public class Ghost extends Sprite {

    private int idGhost;
    private static int nbGhost = 0;

	/**
	 * Constructor
	 */
	public Ghost() {
		super();
		this.color = Color.blue;
        idGhost = nbGhost;
        nbGhost++;
	}

    /**
     *
     * @return the number of instance
     */
    public static int getNbGhost() {
        return nbGhost;
    }

    /**
     *
     * @return Id of the Ghost
     */
    public int getIdGhost() {
        return idGhost;
    }

    /**
	 * @return That this sprite is a ghost.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.GHOST;
	}

}
