package org.jpacman.framework.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.jpacman.framework.Strategy.Dispersion;
import org.jpacman.framework.Strategy.Escape;
import org.jpacman.framework.Strategy.Tracking;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.model.SuperGum;


/**
 * Example, simple ghost mover that just moves ghosts randomly.
 *
 * @author Arie van Deursen; Aug 18, 2003
 */
public abstract class AbstractGhostMover implements IController, ActionListener
{
    /**
     * Underlying game engine.
     */
    protected final IGameInteractor theGame;
	/**
     * Vector of ghosts that are to be moved.
     */
    protected List<Ghost> ghosts;
    /**
     * The default delay between ghost moves.
     */
    public static final int DELAY = 40;
    public static final int DELAY_SCARED = 1000;
    public static final int DELAY_RESURECT = 5000;
//    /**
//     * Randomizer used to pick, e.g., a ghost at random.
//     */
//    private static Random randomizer = new Random();
    /**
     * Timer to be used to trigger ghost moves.
     */
    protected MyTimer timer;
    protected MyTimer timer_scared;
    protected GhostTimer timer_Blinky;
    protected GhostTimer timer_Inky;
    protected GhostTimer timer_Pinky;
    protected GhostTimer timer_Clyde;
    protected String e1 = "timer";
    protected String e2 = "timer_scared";
    protected String e3 = "timer_Blinky";
    protected String e4 = "timer_Inky";
    protected String e5 = "timer_Pinky";
    protected String e6 = "timer_Clyde"; 
    protected int delayEscape = 0;
    protected int delay = 0;
    
    /**
     * @return The object to manipulate the game model.
     */
    protected IGameInteractor getTheGame() {
        return theGame;
    }
    protected List<Ghost> getGhosts()
    {
        return theGame.getGhosts();
    }
    
    
//    private TimerTask tasknew = new TimerTask() {
//		@Override
//		public void run() {
//	        assert controllerInvariant();
//	        synchronized (theGame) {
//	            doTick();
//	        }
//	        assert controllerInvariant();
//		}
//	};
    
    

    /**
	 * @param timer_scared the timer_scared to set
	 */
	public void setTimer_scared() {
		assert controllerInvariant();
        synchronized (theGame) {
            timer_scared.start();
        }
        assert controllerInvariant();
        
//		Timer timer_scared
//      TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				 if ((Ghost.getStrategy() instanceof Escape)){
//					 delayEscape ++;
//					 switch(SuperGum.getNumberSuperGumEat()){
//					 case 1 :
//						 if (delayEscape == 7){
//							 exitEscape();
//						 }
//						 break;
//					 case 2 :
//						 if (delayEscape == 14){
//							 exitEscape();
//						 }
//						 break;
//					 case 3 :
//						 if (delayEscape == 19){
//							 exitEscape();
//						 }
//						 break;
//					 case 4 :
//						 if (delayEscape < 24){
//							 exitEscape();
//						 }
//						 break;
//					 }			 
//				 }else{
//					 delay ++;
//					 switch(delay){
//					 case 7 :
//						 Ghost.setStrategy(new Tracking());
//						 break;
//					 case 27 :
//						 Ghost.setStrategy(new Dispersion());
//						 break;
//					 case 34 :
//						 Ghost.setStrategy(new Tracking());
//						 break;
//					 case 54 :
//						 Ghost.setStrategy(new Dispersion());
//						 break;
//					 case 59 :
//						 Ghost.setStrategy(new Tracking());
//						 break;
//					 case 79 :
//						 Ghost.setStrategy(new Dispersion());
//						 break;
//					 case 84 :
//						 Ghost.setStrategy(new Tracking());
//						 break;
//					 }
//				 }
//
//			}
//		};
//		timer_scared = new Timer();
//		timer_scared.schedule(task, 0, DELAY_SCARED);
        
	}
	
    /**
     * Start a new mover with the given engine.
     * @param theEngine Engine used.
     */
    public AbstractGhostMover(final IGameInteractor theEngine)
    {
        theGame = theEngine;
        ghosts = theGame.getGhosts();
    }

    /**
     * Actually conduct a random move in the underlying engine.
     */
	public abstract void doTick();

    /**
     * Variable that should always be set.
     * @return true iff all vars non-null.
     */
    protected final boolean controllerInvariant() {
        return (timer != null && theGame != null 
        		&& timer_scared != null && timer_Blinky != null 
        		&& timer_Clyde != null && timer_Inky != null 
        		&& timer_Pinky != null);
    }

}
