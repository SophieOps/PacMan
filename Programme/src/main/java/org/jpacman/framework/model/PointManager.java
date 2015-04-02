package org.jpacman.framework.model;

import org.jpacman.framework.Strategy.Escape;

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
		if((currentSprite instanceof Ghost) && (((Ghost)currentSprite).getStrategy() instanceof Escape)){
			Ghost ghost = (Ghost) currentSprite;
            this.consumePointsOnBoard(player, ghost.getPoints());
            ghost.setNumberGhostEat(ghost.getNumberGhostEat()+1);
            ghost.deoccupy();
            /**TODO : réapparition*/
		}
		if (currentSprite instanceof Food) {
			Food food = (Food) currentSprite;
            this.consumePointsOnBoard(player, food.getPoints());
			food.deoccupy();
		}else if (currentSprite instanceof SuperGum) {
			SuperGum supergum = (SuperGum) currentSprite;
            this.consumePointsOnBoard(player, supergum.getPoints());
            supergum.setNumberSuperGumEat(supergum.getNumberSuperGumEat()+1);
            supergum.deoccupy();
            Ghost.strategy = new Escape();
		}
		
	}
	
	private boolean invariant(){
		return pointsEarned >= 0 && pointsEarned <= pointsPutOnBoard;
	}

}
