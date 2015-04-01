package org.jpacman.framework.Strategy;

/**
 * Created by camro.
 */
public interface IStrategy {

    /**
     * Move with the comportement of Blinky
     */
    public void moveBlinky();

    /**
     * Move with the comportement of Pinky
     */
    public void movePinky();

    /**
     * Move with the comportement of Inky
     */
    public void moveInky();
    
    /**
     * Move with the comportement of Clyde
     */
    public void moveClyde();
}
