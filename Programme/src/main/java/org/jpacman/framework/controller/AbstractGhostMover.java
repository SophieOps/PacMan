package org.jpacman.framework.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import org.jpacman.framework.Strategy.Dispersion;
import org.jpacman.framework.Strategy.Escape;
import org.jpacman.framework.Strategy.Tracking;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.model.SuperGum;


/**
 * Example, simple ghost mover that just moves ghosts randomly.
 *
 * @author Arie van Deursen; Aug 18, 2003
 */
public abstract class AbstractGhostMover implements IController, ActionListener
{
    /**
     * Underlying game engine.
     */
    protected final IGameInteractor theGame;
	/**
     * Vector of ghosts that are to be moved.
     */
    protected List<Ghost> ghosts;
    /**
     * The default delay between ghost moves.
     */
    public static final int DELAY = 40;
    public static final int DELAY_SCARED = 40;
    public static final int DELAY_RESURECT = 5000;
    /**
     * Randomizer used to pick, e.g., a ghost at random.
     */
    private static Random randomizer = new Random();

    /**
     * Timer to be used to trigger ghost moves.
     */
    protected MyTimer timer;
    protected MyTimer timer_scared;
    protected int delay_Blinky = 0;
    protected int delay_Inky = 0;
    protected int delay_Pinky = 0;
    protected int delay_Clyde = 0;
    protected String e1 = "timer";
    protected String e2 = "timer_scared";
    protected String e3 = "timer_Blinky";
    protected String e4 = "timer_Inky";
    protected String e5 = "timer_Pinky";
    protected String e6 = "timer_Clyde"; 
    protected int delayEscape = 0;
    protected int delay = 0;
    
    /**
     * @return The object to manipulate the game model.
     */
    protected IGameInteractor getTheGame() {
        return theGame;
    }
    protected List<Ghost> getGhosts()
    {
        return theGame.getGhosts();
    }
    
    /**
	 * @param timer_scared the timer_scared to set
	 */
	public void setTimer_scared() {
		assert controllerInvariant();
        synchronized (theGame) {
        	timer.stop();
            timer_scared.start();
        }
        assert controllerInvariant();        
	}
	
	/**
     * Obtain the randomizer used for ghost moves.
     * @return the randomizer.
     */
    protected static Random getRandomizer() {
        return randomizer;
    }
    
    /**
     * Return a randomly chosen ghost, or null if there
     * are no ghosts in this game.
     * @return Random ghost or null;
     */
    protected Ghost getRandomGhost() {
        Ghost theGhost = null;
        if (!ghosts.isEmpty()) {
            final int ghostIndex = randomizer.nextInt(ghosts.size());
            theGhost = ghosts.get(ghostIndex);
        }
        return theGhost;
    }
    
    /**
     * Start a new mover with the given engine.
     * @param theEngine Engine used.
     */
    public AbstractGhostMover(final IGameInteractor theEngine)
    {
        theGame = theEngine;
        ghosts = theGame.getGhosts();
    }

    /**
     * Actually conduct a random move in the underlying engine.
     */
	public abstract void doTick();

    /**
     * Variable that should always be set.
     * @return true iff all vars non-null.
     */
    protected final boolean controllerInvariant() {
        return (timer != null && theGame != null 
        		&& timer_scared != null);
    }
}
