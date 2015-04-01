package org.jpacman.framework.model;

import java.awt.Color;

/**
 * A player, responsible for keeping track of the
 * amount of food eaten, and whether or not he is still
 * alive.
 * 
 * @author Arie van Deursen, TU Delft, 2012.
 */
public class Player extends Sprite {
	
	private int points = 0;
	private boolean alive = true;
	private Direction direction = Direction.LEFT;

	protected boolean playerInvariant() {
		return getPoints() >= 0 && spriteInvariant();
	}

	/**
	 * Constructor
	 */
	public Player() {
		super();
		this.color = Color.yellow;
	}

	/**
	 * @param extraPoints
	 * @return the total of points earned by the player
	 */
	public int addPoints(int extraPoints) {
		assert isAlive();
		assert playerInvariant();
		setPoints(getPoints() + extraPoints);
		assert playerInvariant();
		return getPoints();
	}

	/**
	 * This player dies.
	 */
	public void die() {
		alive = false;
	}
	
	/**
	 * @return true if the player is alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.PLAYER;
	}
	
	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param nextDir 
	 */
	public void setDirection(Direction nextDir) {
		direction = nextDir;
	}

	/**
	 * @return Number of points earned by the player
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
	 * Set the player alive
	 */
	public void resurrect() {
		alive = true;
	}
}
