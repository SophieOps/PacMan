package org.jpacman.framework.model;

import java.awt.*;

import org.jpacman.framework.Strategy.IStrategy;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostPinky extends Ghost {
	
	private int move = 0;

    /**
	 * @return the move
	 */
	public int getMove() {
		return move;
	}

	/**
	 * @param move the move to set
	 */
	public void setMove(int move) {
		this.move = move%5;
	}

	public GhostPinky(IStrategy strat) {
        super(strat);
        this.color = Color.PINK;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTPINKY;
    }
}
