package org.jpacman.framework.model;

/**
 * Directions in which sprites can move.
 * 
 * @author Arie van Deursen, TU Delft, Jan 23, 2012
 */
public enum Direction {
	/**
	 * vertical downward move 
	 */
	UP(0, -1), 
	/**
	 * horizontal move to the left
	 */
	LEFT(-1, 0),
	/**
	 * vertical upward move
	 */
	DOWN(0, 1),
	/**
	 * horizontal move to the right
	 */
	RIGHT(1, 0);
	
	/**
	 * delta of the horizontal movement
	 * if positive, to the right
	 */
	private int dx;
	/**
	 * delta of the vertical movement
	 * if positive, upward
	 */
	private int dy;
	
	/**
	 * @return delta of the horizontal movement
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the delta of the horizontal movement to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return delta of the vertical movement
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the delta of the vertical movement to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * Create a new direction.
	 * @param dx horizontal move
	 * @param dy vertical move
	 */
	Direction(int dx, int dy) {
		this.setDx(dx);
		this.setDy(dy);
	}
}
