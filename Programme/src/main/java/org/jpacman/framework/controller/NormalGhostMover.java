package org.jpacman.framework.controller;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.GhostPinky;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.model.Tile;

public class NormalGhostMover  extends AbstractGhostMover {

	private int possibleDirection = 0;
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
					if(atNode(theGhost)){
						Direction dir;
						switch(theGhost.getSpriteType()){
						case GHOSTBLINKY:
							dir = theGhost.getStrategy().moveBlinky(theGhost);
							gameInteraction().moveGhost(theGhost, dir);
						case GHOSTCLYDE:
							dir = theGhost.getStrategy().moveClyde();
							gameInteraction().moveGhost(theGhost, dir);
							break;
						case GHOSTINKY:
							if (!(((GhostPinky)theGhost).getMove() == 0)){
								dir = theGhost.getStrategy().moveInky();
								gameInteraction().moveGhost(theGhost, dir);
							}
							((GhostPinky)theGhost).setMove(((GhostPinky)theGhost).getMove()+1);
							break;
						case GHOSTPINKY:
							dir = theGhost.getStrategy().movePinky();
							gameInteraction().moveGhost(theGhost, dir);
							break;
						default:
							break;
						}
					}
				}	

			}else
				return;

			//int dirIndex = getRandomizer().nextInt(Direction.values().length);
			//final Direction dir = Direction.values()[dirIndex];
			//gameInteraction().moveGhost(theGhost, dir);
		}

	}
	
	private boolean atNode(Ghost g){
		Tile tile = g.getTile();
		for(Direction dir : Direction.values()){
			Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
			if (target.tileCanBeOccupied()){
				this.possibleDirection ++;
			}
		}
		if(this.possibleDirection > 2){
			return true;
		}		
		return false;
	}
}
