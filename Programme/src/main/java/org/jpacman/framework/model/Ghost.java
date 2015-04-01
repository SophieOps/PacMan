package org.jpacman.framework.model;

import java.awt.Color;

/**
 * A ghost element on the board.
 * 
 * @author Arie van Deursen, TU Delft, Feb 10, 2012
 */
public class Ghost extends Sprite {

	/**
	 * Constructor
	 */
	public Ghost() {
		super();
		this.color = Color.blue;
	}
	
	/**
	 * @return That this sprite is a ghost.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.GHOST;
	}



}
