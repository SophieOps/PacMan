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
public class Ghost extends Sprite {
	

    /**
	 * The default number of points if food
	 * gets eaten.
	 */
	public static final int DEFAULT_POINTS_1 = 200;
	public static final int DEFAULT_POINTS_2 = 400;
	public static final int DEFAULT_POINTS_3 = 800;
	public static final int DEFAULT_POINTS_4 = 1600;
	
	protected int idGhost;
    protected static int nbGhost = 0;
    protected static IStrategy strategy;
    protected static char previusStrategy;
    protected Direction previusDirection;
	private boolean alive = true; ////////////////////////////////////////////////
    protected int numberGhostEat = 0;
	
	private int points = DEFAULT_POINTS_1;
	
	/**
	 * @return the strategy
	 */
	public static IStrategy getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public static void setStrategy(IStrategy strat) {
		strategy = null;
		strategy = strat;
	}

	/**
	 * @return the previusStrategy
	 */
	public static char getPreviusStrategy() {
		return previusStrategy;
	}

	/**
	 * @param previusStrategy the previusStrategy to set
	 */
	public static void setPreviusStrategy(char previusStrategy) {
		Ghost.previusStrategy = previusStrategy;
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
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @return the numberGhostEat
	 */
	public int getNumberGhostEat() {
		return numberGhostEat;
	}

	/**
	 * @param numberGhostEat the numberGhostEat to set
	 */
	public void setNumberGhostEat(int numberGhostEat) {
		this.numberGhostEat = numberGhostEat;
		switch(numberGhostEat){
		case 1 :
			this.points = DEFAULT_POINTS_2;
			break;
		case 2 :
			this.points = DEFAULT_POINTS_3;
			break;
		case 3 :
			this.points = DEFAULT_POINTS_4;
			break;
		}
	}

	/**
	 * Constructor
	 */
	public Ghost(IStrategy strat) {
		super();
		this.color = Color.blue;
		Ghost.strategy = strat;
		Ghost.previusStrategy = 'd';
        idGhost = nbGhost;
        nbGhost++;
	}
	
	/**
	 * This ghost dies.
	 */
	public void die() {
		alive = false;
	}
	
	/**
	 * Get the ghost alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Set the ghost alive
	 */
	public void resurrect() {
		alive = true;
	}

	

	

}
