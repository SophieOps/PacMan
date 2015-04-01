/**
 * 
 */
package org.jpacman.framework.model;

/**
 * @author Sophie
 *
 */
public class RandomGhostMover extends GhostMover {

	/**
	 * @param theEngine
	 */
	public RandomGhostMover(IGameInteractor theEngine) {
		super(theEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doTick() {
        synchronized (gameInteraction()) {
            Ghost theGhost = getRandomGhost();
            if (theGhost == null) {
                return;
            }
            int dirIndex = getRandomizer().nextInt(Direction.values().length);
            final Direction dir = Direction.values()[dirIndex];
            gameInteraction().moveGhost(theGhost, dir);
        }
		
	}

}
