/**
 * 
 */
package org.jpacman.framework.controller;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IGameInteractor;

/**
 * @author Sophie
 *
 */
public class RandomGhostMover extends AbstractGhostMover {

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
