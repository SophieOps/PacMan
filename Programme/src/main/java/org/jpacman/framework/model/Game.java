package org.jpacman.framework.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//cette classe est un singloton
public final class Game extends Observable implements IGameInteractor {
	
	//pour le singloton
	private static volatile Game instance = null;

	private Board theBoard;
	private final PointManager pointManager = new PointManager();
	private Player thePlayer;
	private final List<Ghost> ghosts = new ArrayList<Ghost>();

	/**
	 * Set a new board
	 * @param b
	 */
	public void setBoard(Board b) {
		assert b != null : "New board should not be null.";
		theBoard = b;
	}
	
	/**
     * Constructor
     */
    private Game() {
        // La présence d'un constructeur privé supprime le constructeur public par défaut.
        // De plus, seul le singleton peut s'instancier lui-même.
        super();
    }
    
    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static Game getInstanceOfGame() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (Game.instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation
           // multiple même par différents "threads".
           // Il est TRES important.
           synchronized(Game.class) {
             if (Game.instance == null) {
            	 Game.instance = new Game();
             }
           }
        }
        return Game.instance;
    }

	@Override
	public void movePlayer(Direction dir) {
		assert theBoard != null : "Board can't be null when moving";
		Tile target = theBoard.tileAtDirection(thePlayer.getTile(), dir);
		if (target.tileCanBeOccupied() && thePlayer.isAlive()) {
			Sprite currentContent = target.topSprite();
			pointManager.eatFood(thePlayer, currentContent);
			dieIfGhost(thePlayer, currentContent);
			thePlayer.deoccupy();
			thePlayer.occupy(target);
			thePlayer.setDirection(dir);
			notifyViewers();
		}
	}


	/**
	 * if the current sprite is a ghost, the player die
	 * @param p player
	 * @param currentSprite sprite
	 */
	private void dieIfGhost(Player p, Sprite currentSprite) {
		if (currentSprite instanceof Ghost) {
			p.die();
		}
	}
	
	@Override
	public void moveGhost(Ghost theGhost, Direction dir) {
		Tile target = theBoard.tileAtDirection(theGhost.getTile(), dir);
		if (target.tileCanBeOccupied()) {
			Sprite currentContent = target.topSprite();
			if (currentContent instanceof Player) { /**TODO : et que pas en mode fuite*/
				((Player) currentContent).die();
			}/**TODO : si ne mode fuite, fantome qui meurt*/
			theGhost.deoccupy();
			theGhost.occupy(target);
			notifyViewers();
		} 
	}


	
	/**
	 * @param p the player
	 */
	public void addPlayer(Player p) {
		thePlayer = p;
	}
	
	/**
	 * @param g the ghost
	 */
	public void addGhost(Ghost g) {
		ghosts.add(g);
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return theBoard;
	}
	
	@Override
	public Player getPlayer() {
		return thePlayer;
	}
	
	@Override
	public void attach(Observer o) {
		assert o != null : "Can't add a null observer.";
		addObserver(o);
	}
	
 
    protected void notifyViewers() {
        setChanged();
        notifyObservers();
    }

	@Override
	public PointManager getPointManager() {
		return pointManager;
	}

	@Override
	public List<Ghost> getGhosts() {
        List<Ghost> result = new ArrayList<Ghost>();
        result.addAll(ghosts);
		return result;
	}

	@Override
	public IBoardInspector getBoardInspector() {
		return getBoard();
	}

	@Override
	public boolean died() {
		return !getPlayer().isAlive();
	}

	@Override
	public boolean won() {
		return pointManager.allEaten();
	}

}
