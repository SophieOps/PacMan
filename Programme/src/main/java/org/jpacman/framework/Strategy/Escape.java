package org.jpacman.framework.Strategy;

import java.util.Random;

import org.jpacman.framework.controller.AbstractGhostMover;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;

public class Escape implements IStrategy {
	
	private boolean move = false;
    /**
     * Randomizer used to pick, e.g., a ghost at random.
     */
    private static Random randomizer = new Random();
    /**
     * Obtain the randomizer used for ghost moves.
     * @return the randomizer.
     */
    protected static Random getRandomizer() {
        return randomizer;
    }

	@Override
	public Direction moveBlinky(Ghost g) {
		return move();

	}

	@Override
	public Direction movePinky(Ghost g) {
		return move();
	}

	@Override
	public Direction moveInky(Ghost g, Ghost blinky) {
		return move();
	}

	@Override
	public Direction moveClyde(Ghost g) {
		return move();
	}
	
	private Direction move(){
		Direction dir = Direction.values()[0];
		if (move == false){
			move = true;
			int dirIndex = randomizer.nextInt(Direction.values().length);
			dir = Direction.values()[dirIndex];
		}else{
			move = false;
		}
		return dir;
		
	}
	

}
