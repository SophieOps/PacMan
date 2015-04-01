package org.jpacman.framework.model;

/**
 * Get informed about the number of points
 * earned and the points still available.
 * 
 * @author Arie van Deursen, TU Delft, Jan 21, 2012
 */
public interface IPointInspector {

	/**
	 * @return the number of food which have been eaten
	 */
	int getFoodEaten();
	/**
	 * @return the total number of food in the current game
	 */
	int totalFoodInGame();
	/**
	 * @return true if all the food in the game have been eaten
	 */
	boolean allEaten();
}
