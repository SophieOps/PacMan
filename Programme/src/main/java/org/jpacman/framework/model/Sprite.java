package org.jpacman.framework.model;

import java.awt.Color;

/**
 * Any entity that occurs in the game.
 * 
 * @author Arie van Deursen, TU Delft, Jan 2, 2012
 */
public class Sprite {
	
	/**
	 * The tile the sprite is occupying -- null if 
	 * the sprite is disconnected.
	 */
	private Tile tile;
	
	/**
	 * The color of the sprite in fonction of his type
	 */
	protected Color color;
	
	/**
	 * Create a new sprite, not on any tile yet.
	 * Real instantation done in concrete subclasses,
	 * hence protected.
	 */
	protected Sprite() {
		tile = null;
		color = Color.black;
		assert spriteInvariant();
	}
	
	/**
	 * Once the tile is set, the tile should indeed
	 * contain this sprite.
	 * @return true iff this invariant holds.
	 */
	protected final boolean spriteInvariant() {
		return
			tile == null 
			||
			tile.containsSprite(this);
	}
	
	/**
	 * @return The tile this sprite is located on.
	 */
	public Tile getTile() {
		return tile;
	}
	
	/**
	 * @return The tile this sprite is located on.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Let the sprite occupy another tile.
	 * @param nextLocation The next tile to be occupied.
	 */
	public void occupy(Tile nextLocation) {
		assert spriteInvariant();
		assert nextLocation != null;
		assert tile == null;
		
		tile = nextLocation;
		nextLocation.addSprite(this);
		
		assert getTile() != null;
		assert getTile().equals(nextLocation);
		assert this.equals(nextLocation.topSprite())
			: "Post: sprite at top of new tile.";
	
		assert spriteInvariant();
	}
	
	/**
	 * Move the sprite away from the tile it was occupying.
	 */
	public void deoccupy() {
		assert tile != null : "PRE: Must occupy a cell already.";
		assert spriteInvariant();
		tile.dropSprite(this);
		tile = null;
		assert spriteInvariant();
	}
	
	/**
	 * @return the type of the sprite
	 * return OTHER when the sprite his not a child of the Sprite object.
	 */
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.OTHER; 
	}
	
	@Override
	public String toString() {
		return getSpriteType().toString() + " occupying " + tile;
				
	}
}
