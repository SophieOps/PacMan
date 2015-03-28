package org.jpacman.framework.model;

/**
 * A food element on the board.
 * @author Arie van Deursen, TU Delft, Feb 16, 2012
 */
public class Food extends Sprite {
	
	/**
	 * The default number of points if food
	 * gets eaten.
	 */
	public static final int DEFAULT_POINTS = 10;
	
	public int points = DEFAULT_POINTS;

    /**
	 * Constructor meets invariant of sub and superclass.
	 */
	public Food() {
        assert points >= 0 && spriteInvariant();
	}

    /**
	 * @return That this sprite is a piece of Food.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.FOOD;
	}

}
