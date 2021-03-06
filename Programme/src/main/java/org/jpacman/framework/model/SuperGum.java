/**
 * 
 */
package org.jpacman.framework.model;

import java.awt.Color;

/**
 * A super gum element on the board
 * @author Sophie
 *
 */
public class SuperGum extends Sprite {
	
	/**
	 * The default number of points if super gum
	 * gets eaten.
	 */
	public static final int DEFAULT_POINTS = 50;
	
	private int points = DEFAULT_POINTS;
    private static int numberSuperGumEAt = 0;
	
	
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
	 * @return the numberSuperGum
	 */
	public static int getNumberSuperGumEat() {
		return numberSuperGumEAt;
	}

	/**
	 * @param numberSuperGum the numberSuperGum to set
	 */
	public static void setNumberSuperGumEat(int numberSuperGumEat) {
		numberSuperGumEAt = numberSuperGumEat;
	}

	/**
	 * Constructor meets invariant of sub and superclass.
	 */
	public SuperGum() {
        assert getPoints() >= 0 && spriteInvariant();
		this.color = Color.MAGENTA;
	}

    /**
	 * @return That this sprite is a piece of super gum.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.SUPERGUM;
	}
	
	
	
}
