package org.jpacman.framework.model;

import java.awt.*;

import org.jpacman.framework.Strategy.IStrategy;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostClyde extends Ghost {

    public GhostClyde(IStrategy strat) {
        super(strat);
        this.color = Color.ORANGE;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTCLYDE;
    }
}
