package org.jpacman.framework.model;

import java.awt.Color;

/**
 * A Wall element.
 * 
 * @author Arie van Deursen, TU Delft, Feb 10, 2012
 */
public class Wall extends Sprite {

	/**
	 * Constructor
	 */
	public Wall() {
		super();
		this.color = Color.green;
	}
	
	/**
	 * @return That this sprite is a wall.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.WALL;
	}

}
