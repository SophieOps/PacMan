package org.jpacman.framework.model;

import java.awt.Color;

/**
 * @author Sophie
 *
 */
public interface IBoardInspector {


	/**
	 * @return the width of the board
	 */
	int getWidth();
	/**
	 * @returnthe height of the board
	 */
	int getHeight();
	
	/**
	 * Enumeration of the type of sprite
	 * @author Sophie
	 *
	 */
	public enum SpriteType { 
		PLAYER, 
		GHOST, 
		FOOD, 
		EMPTY, 
		WALL, 
		OTHER
	}
		
	/**
	 * Check what are at the location in parameter
	 * @param x the x-coordinate
	 * @param y the y-coordonate
	 * @return the sprite at the position
	 */
	Sprite spriteAt(int x, int y);	

	/**
	 * Check what is the type of the sprite at the location in parameter
	 * @param x the x-coordinate
	 * @param y the y-coordonate
	 * @return the type of the sprite at the position
	 */
	SpriteType spriteTypeAt(int x, int y);
	
	/**
	 * Check what is the type of the sprite at the location in parameter
	 * @param x the x-coordinate
	 * @param y the y-coordonate
	 * @return the type of the sprite at the position
	 */
	Color spriteColor(int x, int y);

	/**
	 * Check what are at the location in parameter
	 * @param x the x-coordinate
	 * @param y the y-coordonate
	 * @return the tile at the position
	 */
	Tile tileAt(int x, int y);	
}
