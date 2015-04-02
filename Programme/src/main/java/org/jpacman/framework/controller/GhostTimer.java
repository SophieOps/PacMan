package org.jpacman.framework.controller;


import org.jpacman.framework.model.Ghost;

import java.awt.event.ActionListener;

/**
 * The death timer of the ghosts
 */
public class GhostTimer extends MyTimer
{
    /**
     * The ghost of this timer
     */
    private Ghost ghost;

    public GhostTimer(int delay, ActionListener listener, Ghost ghost) {
        super(delay, listener);
        this.ghost = ghost;
    }

    public Ghost getGhost() {
        return ghost;
    }


}
