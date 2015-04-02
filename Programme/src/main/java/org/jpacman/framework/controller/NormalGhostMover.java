package org.jpacman.framework.controller;

import java.awt.event.ActionEvent;

import org.jpacman.framework.Strategy.Dispersion;
import org.jpacman.framework.Strategy.Escape;
import org.jpacman.framework.Strategy.IStrategy;
import org.jpacman.framework.Strategy.Tracking;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.GhostPinky;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.model.SuperGum;
import org.jpacman.framework.model.Tile;

public class NormalGhostMover  extends AbstractGhostMover {

	private int possibleDirection = 0;

	/**
	 * @param theEngine
	 */
	public NormalGhostMover(IGameInteractor theEngine) {
		super(theEngine);
		this.timer = new MyTimer(DELAY, this);
		this.timer.setActionCommand("timer");
		this.timer_scared = new MyTimer(DELAY_SCARED, this);
		this.timer_scared.setActionCommand("timer_scared");
		assert controllerInvariant();
	}

	@Override
	public void doTick() {
		synchronized (getTheGame()) {
			Ghost theGhost = null;
			IStrategy strat = Ghost.getStrategy();
			if (!ghosts.isEmpty()) {
				theGhost = getRandomGhost();
				if (theGhost == null) {
					return;
				}
				if(atNode(theGhost)){
					Direction dir;
					switch(theGhost.getSpriteType()){
					case GHOSTBLINKY:
						dir = Ghost.getStrategy().moveBlinky(theGhost);
						theGhost.setPreviusDirection(dir);
						getTheGame().moveGhost(theGhost, dir);
						break;
					case GHOSTCLYDE:
						dir = Ghost.getStrategy().moveClyde(theGhost);
						theGhost.setPreviusDirection(dir);
						getTheGame().moveGhost(theGhost, dir);
						break;
					case GHOSTPINKY:
						if (!(((GhostPinky)theGhost).getMove() == 0)){
							dir = Ghost.getStrategy().movePinky(theGhost);
							theGhost.setPreviusDirection(dir);
							getTheGame().moveGhost(theGhost, dir);
						}
						((GhostPinky)theGhost).setMove(((GhostPinky)theGhost).getMove()+1);
						break;
					case GHOSTINKY:
						Ghost blinky = null;
						for(int j = 0; j < ghosts.size(); j++){
							if(ghosts.get(j).getSpriteType() == SpriteType.GHOSTBLINKY){
								blinky = ghosts.get(j);
							}
						}
						dir = Ghost.getStrategy().moveInky(theGhost, blinky);
						theGhost.setPreviusDirection(dir);
						getTheGame().moveGhost(theGhost, dir);
						break;
					default:
						break;
					}
				}else{
					Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), theGhost.getPreviusDirection());
					if (target.tileCanBeOccupied()) {
						theGhost.setPreviusDirection(theGhost.getPreviusDirection());
						getTheGame().moveGhost(theGhost, theGhost.getPreviusDirection());
					}else{
						switch(theGhost.getPreviusDirection()){
						case DOWN:
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.LEFT);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.LEFT);
								getTheGame().moveGhost(theGhost, Direction.LEFT);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.RIGHT);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.RIGHT);
								getTheGame().moveGhost(theGhost, Direction.RIGHT);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.UP);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.UP);
								getTheGame().moveGhost(theGhost, Direction.UP);
								break;
							}
							break;
						case LEFT:
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.UP);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.UP);
								getTheGame().moveGhost(theGhost, Direction.UP);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.DOWN);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.DOWN);
								getTheGame().moveGhost(theGhost, Direction.DOWN);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.RIGHT);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.RIGHT);
								getTheGame().moveGhost(theGhost, Direction.RIGHT);
								break;
							}
							break;
						case RIGHT:
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.UP);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.UP);
								getTheGame().moveGhost(theGhost, Direction.UP);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.DOWN);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.DOWN);
								getTheGame().moveGhost(theGhost, Direction.DOWN);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.LEFT);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.LEFT);
								getTheGame().moveGhost(theGhost, Direction.LEFT);
								break;
							}
							break;
						case UP:
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.LEFT);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.LEFT);
								getTheGame().moveGhost(theGhost, Direction.LEFT);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.RIGHT);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.RIGHT);
								getTheGame().moveGhost(theGhost, Direction.RIGHT);
								break;
							}
							target = Game.getInstanceOfGame().getBoard().tileAtDirection(theGhost.getTile(), Direction.DOWN);
							if (target.tileCanBeOccupied()) {
								theGhost.setPreviusDirection(Direction.DOWN);
								getTheGame().moveGhost(theGhost, Direction.DOWN);
								break;
							}
							break;
						default:
							break;
						}
					}
				}	
			}else
				return;
		}
	}

	private boolean atNode(Ghost g){
		this.possibleDirection = 0;
		Tile tile = g.getTile();
		for(Direction dir : Direction.values()){
			Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
			if (target.tileCanBeOccupied()){
				this.possibleDirection ++;
			}
		}
		if(this.possibleDirection != 2){
			return true;
		}		
		return false;
	}

	@Override
	public void start() {
		assert controllerInvariant();
		synchronized (theGame) {
			timer.start();
			}
		assert controllerInvariant();
	}

	@Override
	public void stop()
	{
		assert controllerInvariant();

		if(timer.isRunning()){
			timer.stop();
		}
		if(timer_scared.isRunning()){
			timer_scared.stop();
		}
		assert controllerInvariant();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		assert controllerInvariant();
		synchronized (theGame) {
//			for (Ghost gt : ghosts)
//			{
//				switch(gt.getSpriteType()){
//				case GHOSTBLINKY:
//					if(!gt.isAlive()){
//						delay_Blinky++;
//						if(delay_Blinky >=5){
//							gt.resurrect();
//							delay_Blinky = 0;
//						}
//					}
//					break;
//				case GHOSTCLYDE:
//					if(!gt.isAlive()){
//						delay_Clyde++;
//						if(delay_Clyde >=5){
//							gt.resurrect();
//							delay_Clyde = 0;
//						}
//					}
//					break;
//				case GHOSTINKY:
//					if(!gt.isAlive()){
//						delay_Inky++;
//						if(delay_Inky >=5){
//							gt.resurrect();
//							delay_Inky = 0;
//						}
//					}
//					break;
//				case GHOSTPINKY:
//					if(!gt.isAlive()){
//						delay_Pinky++;
//						if(delay_Pinky >=5){
//							gt.resurrect();
//							delay_Pinky = 0;
//						}
//					}
//					break;
//				default:
//					break;
//				}
//			}
			String str = e.getActionCommand().toString();
			if (str.compareToIgnoreCase(e1) == 0){
				if (Ghost.getStrategy() instanceof Escape){
					setTimer_scared();
				}else{
					delay ++;
					switch(delay){
					case 175 :
						Ghost.setStrategy(new Tracking());
						Ghost.setPreviusStrategy('t');
						break;
					case 675 :
						Ghost.setStrategy(new Dispersion());
						Ghost.setPreviusStrategy('d');
						break;
					case 850 :
						Ghost.setStrategy(new Tracking());
						Ghost.setPreviusStrategy('t');
						break;
					case 1350 :
						Ghost.setStrategy(new Dispersion());
						Ghost.setPreviusStrategy('d');
						break;
					case 1475 :
						Ghost.setStrategy(new Tracking());
						Ghost.setPreviusStrategy('t');
						break;
					case 1975 :
						Ghost.setStrategy(new Dispersion());
						Ghost.setPreviusStrategy('d');
						break;
					case 2100 :
						Ghost.setStrategy(new Tracking());
						Ghost.setPreviusStrategy('t');
						break;
					}
				}
				doTick();
			}else if (str.compareToIgnoreCase(e2) == 0){
				if ((Ghost.getStrategy() instanceof Escape)){
					delayEscape ++;
					switch(SuperGum.getNumberSuperGumEat()){
					case 1 :
						if (delayEscape == 175){
							exitEscape();
							timer_scared.stop();
							timer_scared = new MyTimer(DELAY_SCARED, this);
							timer_scared.setActionCommand("timer_scared"); 
							timer.start();
						}
						break;
					case 2 :
						if (delayEscape == 350){
							exitEscape();
							timer_scared.stop();
							timer_scared = new MyTimer(DELAY_SCARED, this);
							timer_scared.setActionCommand("timer_scared"); 
							timer.start();
						}
						break;
					case 3 :
						if (delayEscape == 475){
							exitEscape();
							timer_scared.stop();
							timer_scared = new MyTimer(DELAY_SCARED, this);
							timer_scared.setActionCommand("timer_scared"); 
							timer.start();
						}
						break;
					case 4 :
						if (delayEscape < 600){
							exitEscape();
							timer_scared.stop();
							timer_scared = new MyTimer(DELAY_SCARED, this);
							timer_scared.setActionCommand("timer_scared"); 
							timer.start();
						}
						break;
					}
				}
				doTick();
			}else if (str.compareToIgnoreCase(e3) == 0){
				resurect(SpriteType.GHOSTBLINKY);
			}else if (str.compareToIgnoreCase(e4) == 0){
				resurect(SpriteType.GHOSTINKY);
			}else if (str.compareToIgnoreCase(e5) == 0){
				resurect(SpriteType.GHOSTPINKY);
			}else if (str.compareToIgnoreCase(e6) == 0){
				resurect(SpriteType.GHOSTCLYDE);
			}

		}
		assert controllerInvariant();
	}

	static void exitEscape(){
		switch(Ghost.getPreviusStrategy()){
		case 't' :
			Ghost.setStrategy(new Tracking());
			break;
		case 'd' :
			Ghost.setStrategy(new Dispersion());
			break;
		}
	}

	public void resurect(SpriteType sprType){
		for (Ghost gt : ghosts)
		{
			if(sprType == gt.getSpriteType()){
				//gt. //is alive
			}
		}
	}




}
