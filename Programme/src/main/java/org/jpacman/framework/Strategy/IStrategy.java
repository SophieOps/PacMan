package org.jpacman.framework.Strategy;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;

/**
 * Created by camro.
 */
public interface IStrategy {

    /**
     * Move with the comportement of Blinky
     * @return 
     */
    public Direction moveBlinky(Ghost g);

    /**
     * Move with the comportement of Pinky
     */
    public Direction movePinky(Ghost g);

    /**
     * Move with the comportement of Inky
     */
    public Direction moveInky(Ghost g, Ghost blinky);
    
    /**
     * Move with the comportement of Clyde
     */
    public Direction moveClyde(Ghost g);
    
}
