package org.jpacman.framework.model;

import java.awt.*;

/**
 * Created by camro on 31/03/2015.
 */
public class GhostOrange extends Ghost {

    public GhostOrange() {
        super();
        this.color = Color.ORANGE;
    }

    /**
     * @return That this sprite is a ghost.
     */
    @Override
    public IBoardInspector.SpriteType getSpriteType() {
        return IBoardInspector.SpriteType.GHOSTORANGE;
    }
}
