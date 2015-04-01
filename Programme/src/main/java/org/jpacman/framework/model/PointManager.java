package org.jpacman.framework.model;

/**
 * Keep track of points (still) in the game.
 * 
 * @author Arie van Deursen, TU Delft, Jan 21, 2012
 */
public class PointManager implements IPointInspector {
	
	private int pointsPutOnBoard = 0;
	private int pointsEarned = 0;
	

	/**
	 * Add 'delta' points at the variable pointtsPutOnBoard
	 * @param delta
	 */
	public void addPointsToBoard(int delta) {
		assert delta >= 0;
		pointsPutOnBoard += delta;
		assert invariant();
	}
	
	private void consumePointsOnBoard(int delta) {
		pointsEarned += delta;
        assert invariant();
	}
	
	/**
	 * While playing, let the player consume food.
	 * @param p Player actually eating.
	 * @param delta Amount of food eaten.
	 */
	public void consumePointsOnBoard(Player p, int delta) {
		p.addPoints(delta);
		consumePointsOnBoard(delta);
        assert invariant();
	}
		
	
	/**
	 * The game is over if everything has been eaten.
	 * @return Whether all points have been consumed.
	 */
	@Override
	public boolean allEaten() {
        assert invariant();
		return pointsEarned == pointsPutOnBoard;
	}

	@Override
	public int getFoodEaten() {
		return pointsEarned;
	}
	
	@Override
	public int totalFoodInGame() {
		return pointsPutOnBoard;
	}
	
	/**
	 * Add point at the player when he eat a food 
	 * @param player
	 * @param currentSprite
	 */
	public void eatFood(Player player, Sprite currentSprite) {
		if (currentSprite instanceof Food) {
			Food food = (Food) currentSprite;
            this.consumePointsOnBoard(player, food.getPoints());
			food.deoccupy();
		}
	}
	
	private boolean invariant(){
		return pointsEarned >= 0 && pointsEarned <= pointsPutOnBoard;
	}

}
