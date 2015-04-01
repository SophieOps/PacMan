package org.jpacman.framework.controller;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.GhostPinky;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.IGameInteractor;

public class NormalGhostMover  extends AbstractGhostMover {

	/**
	 * @param theEngine
	 */
	public NormalGhostMover(IGameInteractor theEngine) {
		super(theEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doTick() {
		synchronized (gameInteraction()) {
			Ghost theGhost = null;
			if (!ghosts.isEmpty()) {
				for(int i = 0; i < ghosts.size(); i++){
					theGhost = ghosts.get(i);
					switch(theGhost.getSpriteType()){
					case GHOSTBLINKY:
						theGhost.getStrategy().moveBlinky();
					case GHOSTCLYDE:
						theGhost.getStrategy().moveClyde();
						break;
					case GHOSTINKY:
						if (!(((GhostPinky)theGhost).getMove() == 0)){
							theGhost.getStrategy().moveInky();
						}
						((GhostPinky)theGhost).setMove(((GhostPinky)theGhost).getMove()+1);
						break;
					case GHOSTPINKY:
						theGhost.getStrategy().movePinky();
						break;
					default:
						break;
					}
				}

			}else
				return;

			//int dirIndex = getRandomizer().nextInt(Direction.values().length);
			//final Direction dir = Direction.values()[dirIndex];
			//gameInteraction().moveGhost(theGhost, dir);
		}

	}
}
