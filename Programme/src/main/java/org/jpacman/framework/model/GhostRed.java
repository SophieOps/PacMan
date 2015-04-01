package org.jpacman.framework.model;

import java.awt.*;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostRed extends Ghost {

    public GhostRed() {
        super();
        this.color = Color.RED;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTRED;
    }
}
