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
							theGhost.setPreviusDirection(dir);
							gameInteraction().moveGhost(theGhost, dir);
						case GHOSTCLYDE:
							dir = theGhost.getStrategy().moveClyde(theGhost);
							theGhost.setPreviusDirection(dir);
							gameInteraction().moveGhost(theGhost, dir);
							break;
						case GHOSTINKY:
							Ghost blinky = null;
							if (!(((GhostPinky)theGhost).getMove() == 0)){
								for(int j = 0; j < ghosts.size(); j++){
									if(theGhost.getSpriteType() == SpriteType.GHOSTBLINKY){
										blinky = ghosts.get(j);
									}
								}
								dir = theGhost.getStrategy().moveInky(theGhost, blinky);
								theGhost.setPreviusDirection(dir);
								gameInteraction().moveGhost(theGhost, dir);
							}
							((GhostPinky)theGhost).setMove(((GhostPinky)theGhost).getMove()+1);
							break;
						case GHOSTPINKY:
							dir = theGhost.getStrategy().movePinky(theGhost);
							theGhost.setPreviusDirection(dir);
							gameInteraction().moveGhost(theGhost, dir);
							break;
						default:
							break;
						}
					}else{
						Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), theGhost.getPreviusDirection());
						if (target.tileCanBeOccupied()) {
							gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
						}else{
							switch(theGhost.getPreviusDirection()){
							case DOWN:
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.LEFT);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.RIGHT);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.UP);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								break;
							case LEFT:
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.UP);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.DOWN);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.RIGHT);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								break;
							case RIGHT:
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.UP);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.DOWN);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.LEFT);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								break;
							case UP:
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.LEFT);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.RIGHT);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.DOWN);
								if (target.tileCanBeOccupied()) {
									gameInteraction().moveGhost(theGhost, theGhost.getPreviusDirection());
									break;
								}
								break;
							default:
								break;
							}
						}
					}
				}	
			}else
				return;
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