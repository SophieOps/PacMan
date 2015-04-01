package org.jpacman.framework.Strategy;

/**
 * Created by camro.
 */
public interface GhostIA {

    /**
     * Start the timer
     */
    public void start();

    /**
     * Stop the timer
     */
    public void stop();

    /**
     *The action to do
     */
    public void doTick();
}
