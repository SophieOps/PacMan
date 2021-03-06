package org.jpacman.framework.factory;

import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.GhostBlinky;
import org.jpacman.framework.model.GhostClyde;
import org.jpacman.framework.model.GhostInky;
import org.jpacman.framework.model.GhostPinky;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.SuperGum;
import org.jpacman.framework.model.Wall;

/**
 * Interface to create objects for the various
 * sprites participating in a game.
 * Implements the Abstract Factory Design Pattern.
 * 
 * @author Arie van Deursen, TU Delft, December, 2011
 */
public interface IGameFactory {
	
	/**
	 * @return A newly created game.
	 */
	Game makeGame();
	
	/**
	 * Create a new board of given width and height.
	 * @param w Width of the board
	 * @param h Height of the board
	 * @return A new board of requested width and height.
	 */
	Board makeBoard(int w, int h);
	
	/**
	 * @return A newly created player.
	 */
	Player makePlayer();
	
	/**
	 * @return A newly created ghost.
	 */
	Ghost makeGhost();

    /**
     * @return A newly created ghost.
     */
    GhostBlinky makeGhostRed();

    /**
     * @return A newly created ghost.
     */
    GhostClyde makeGhostOrange();

    /**
     * @return A newly created ghost.
     */
    GhostInky makeGhostCyan();

    /**
     * @return A newly created ghost.
     */
    GhostPinky makeGhostPink();

	/**
	 * @return A newly created food sprite.
	 */
	Food makeFood();
	
	/**
	 * @return A newly created wall sprite.
	 */	
	Wall makeWall();

	/**
	 * @return A newly created super gum sprite.
	 */
	SuperGum makeSuperGum();
}
