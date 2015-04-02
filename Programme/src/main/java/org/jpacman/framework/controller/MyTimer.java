package org.jpacman.framework.controller;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The timer used to compute the time of the fear mode and the death timer of the ghosts
 */
public class MyTimer extends Timer
{
	private static final long serialVersionUID = -59470379321937183L;
	private long time, newTime;
    private int delay;

    public MyTimer(int delay, ActionListener listener)
    {
        super(delay,listener);
        this.delay = getDelay();
    }

    /**
     * Start the timer
     */
    @Override
	public void start()
    {
        time = System.currentTimeMillis();
        delay = getDelay();
        super.start();
    }

    /**
     * Restart the timer
     */
    @Override
	public void restart()
    {
        if(delay>0)
        {
            super.setInitialDelay(delay);
            time = System.currentTimeMillis();
            super.start();
        }
    }

    /**
     * Pause the timer
     */
    public void pause()
    {
        if (isRunning())
        {
            super.stop();
            newTime = System.currentTimeMillis() - time;
            delay = (int)(super.getInitialDelay() - newTime);
            if(delay<0)
                delay = 0;
        }
    }

    /**
     *
     * @return The time left in fear state
     */
    public int getTimeLeft()
    {
        int timeLeft = getInitialDelay()-(int)(System.currentTimeMillis() - time);
        if(timeLeft > 0)
            return  timeLeft;
		return 0;
    }
}


