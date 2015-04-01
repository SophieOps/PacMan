package org.jpacman.framework.model;

import java.awt.*;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostPink extends Ghost {

    public GhostPink() {
        super();
        this.color = Color.PINK;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTPINK;
    }
}
