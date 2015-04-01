package org.jpacman.framework.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IGameInteractor;


/**
 * Example, simple ghost mover that just moves ghosts randomly.
 *
 * @author Arie van Deursen; Aug 18, 2003
 */
public abstract class AbstractGhostMover implements ActionListener, IController
{
    /**
     * Underlying game engine.
     */
    private final IGameInteractor theGame;

    /**
     * The default delay between ghost moves.
     */
    public static final int DELAY = 40;
//    /**
//     * Randomizer used to pick, e.g., a ghost at random.
//     */
//    private static Random randomizer = new Random();
    /**
     * Timer to be used to trigger ghost moves.
     */
    private final Timer timer;

    /**
     * Vector of ghosts that are to be moved.
     */
    protected List<Ghost> ghosts;

    /**
     * Start a new mover with the given engine.
     *
     * @param theEngine Engine used.
     */
    public AbstractGhostMover(final IGameInteractor theEngine)
    {
        theGame = theEngine;
        timer = new Timer(DELAY, this);
        assert controllerInvariant();
    }

//    /**
//     * Obtain the randomizer used for ghost moves.
//     * @return the randomizer.
//     */
//    protected static Random getRandomizer() {
//        return randomizer;
//    }

    /**
     * Actually conduct a random move in the underlying engine.
     */
	public abstract void doTick();

    /**
     * Variable that should always be set.
     * @return true iff all vars non-null.
     */
    protected final boolean controllerInvariant() {
        return timer != null && theGame != null;
    }

    /**
     * ActionListener event caught when timer ticks.
     * @param e Event caught.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        assert controllerInvariant();
        synchronized (theGame) {
            doTick();
        }
        assert controllerInvariant();
    }

    @Override
    public void start() {
        assert controllerInvariant();
        // the game may have been restarted -- refresh the ghost list
        // contained.
        synchronized (theGame) {
            ghosts = theGame.getGhosts();
            timer.start();
            assert ghosts != null;
        }
        assert controllerInvariant();
    }

    @Override
    public void stop() {
        assert controllerInvariant();
        timer.stop();
        assert controllerInvariant();
    }

//    /**
//     * Return a randomly chosen ghost, or null if there
//     * are no ghosts in this game.
//     * @return Random ghost or null;
//     */
//    protected Ghost getRandomGhost() {
//        Ghost theGhost = null;
//        if (!ghosts.isEmpty()) {
//            final int ghostIndex = AbstractGhostMover.randomizer.nextInt(ghosts.size());
//            theGhost = ghosts.get(ghostIndex);
//        }
//        return theGhost;
//    }

    /**
     * @return The object to manipulate the game model.
     */
    protected IGameInteractor gameInteraction() {
        return theGame;
    }
}
