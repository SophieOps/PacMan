package org.jpacman.framework.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.jpacman.framework.Strategy.*;

/**
 * A ghost element on the board.
 * 
 * @author Arie van Deursen, TU Delft, Feb 10, 2012
 */
public class Ghost extends Sprite implements ActionListener {

	protected int idGhost;
    protected static int nbGhost = 0;
    protected IStrategy strategy;
    protected static final int DELAY = 1;
    protected final Timer timer;
    protected int delay = 0;
    protected int delayEscape = 0;
    protected char previusStrategy;
    protected Direction previusDirection;

	/**
	 * @return the strategy
	 */
	public IStrategy getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * @param idGhost the idGhost to set
	 */
	public void setIdGhost(int idGhost) {
		this.idGhost = idGhost;
	}

	    /**
     *
     * @return the number of instance
     */
    public static int getNbGhost() {
        return nbGhost;
    }

    /**
     *
     * @return Id of the Ghost
     */
    public int getIdGhost() {
        return idGhost;
    }

    /**
	 * @return That this sprite is a ghost.
	 */
	@Override
	public IBoardInspector.SpriteType getSpriteType() {
		return IBoardInspector.SpriteType.GHOST;
	}

	
	/**
	 * @return the previusDirection
	 */
	public Direction getPreviusDirection() {
		return previusDirection;
	}

	/**
	 * @param previusDirection the previusDirection to set
	 */
	public void setPreviusDirection(Direction previusDirection) {
		this.previusDirection = previusDirection;
	}

	/**
	 * Constructor
	 */
	public Ghost(IStrategy strat) {
		super();
		this.color = Color.blue;
		this.strategy = strat;
        idGhost = nbGhost;
        nbGhost++;
        this.timer = new Timer(DELAY, this);
        assert timer != null;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		 assert timer != null;
		 if ((this.strategy instanceof Escape)){
			 this.delayEscape ++;
			 switch(SuperGum.getNumberSuperGumEat()){
			 case 1 :
				 if (this.delayEscape == 7){
					 exitEscape();
				 }
				 break;
			 case 2 :
				 if (this.delayEscape == 14){
					 exitEscape();
				 }
				 break;
			 case 3 :
				 if (this.delayEscape == 19){
					 exitEscape();
				 }
				 break;
			 case 4 :
				 if (this.delayEscape == 24){
					 exitEscape();
				 }
				 break;
			 }			 
		 }else{
			 this.delay ++;
			 switch(delay){
			 case 7 :
				 this.strategy = new Tracking();
				 break;
			 case 27 :
				 this.strategy = new Dispersion();
				 break;
			 case 34 :
				 this.strategy = new Tracking();
				 break;
			 case 54 :
				 this.strategy = new Dispersion();
				 break;
			 case 59 :
				 this.strategy = new Tracking();
				 break;
			 case 79 :
				 this.strategy = new Dispersion();
				 break;
			 case 84 :
				 this.strategy = new Tracking();
				 break;
			 }
		 }

		
	}
	
	private void exitEscape(){
		switch(this.previusStrategy){
		case 't' :
			this.strategy = new Tracking();
			break;
		case 'd' :
			this.strategy = new Dispersion();
			break;
		}
	}
	

}
