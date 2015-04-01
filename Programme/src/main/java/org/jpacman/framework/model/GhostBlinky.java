package org.jpacman.framework.model;

import java.awt.*;

import org.jpacman.framework.Strategy.IStrategy;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostBlinky extends Ghost {

    public GhostBlinky(IStrategy strat) {
        super(strat);
        this.color = Color.RED;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTBLINKY;
    }
}
