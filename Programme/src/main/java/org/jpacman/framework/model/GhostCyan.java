package org.jpacman.framework.model;

import java.awt.*;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostCyan extends Ghost {

    public GhostCyan() {
        super();
        this.color = Color.CYAN;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTCYAN;
    }
}
