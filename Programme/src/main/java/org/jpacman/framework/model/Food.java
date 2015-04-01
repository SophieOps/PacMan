package org.jpacman.framework.model;

import java.awt.Color;

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
	
	private int points = DEFAULT_POINTS;
	
    /**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param pts the points to set
	 */
	public void setPoints(int pts) {
		this.points = pts;
	}

	/**
	 * Constructor meets invariant of sub and superclass.
	 */
	public Food() {
        assert getPoints() >= 0 && spriteInvariant();
		this.color = Color.orange;
	}

    /**
	 * @return That this sprite is a piece of Food.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.FOOD;
	}
	
	
	

}
