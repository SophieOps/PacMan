package org.jpacman.framework.factory;

import org.jpacman.framework.Strategy.Dispersion;
import org.jpacman.framework.model.*;

/**
 * A factory for the classes related to the 
 * game, the board, and its content.
 * 
 * @author Arie van Deursen, TU Delft, Jan 22, 2012
 */
public class DefaultGameFactory implements IGameFactory {

	private Game theGame;	//transient		//retiré lors du refactoring parce que jugé sans intérêt
	
	@Override
	public Game makeGame() {
		theGame = new Game();
		return theGame;
	}
	
	@Override
	public Player makePlayer() {
		assert getGame() != null;
		Player p = new Player();
		getGame().addPlayer(p);
		return p;
	}

	@Override
	public Ghost makeGhost() {
		assert getGame() != null;
		Ghost g = new Ghost(new Dispersion());
		getGame().addGhost(g);
		return g;
	}

    @Override
    public GhostBlinky makeGhostRed() {
        assert getGame() != null;
        GhostBlinky g = new GhostBlinky(new Dispersion());
        getGame().addGhost(g);
        return g;
    }

    @Override
    public GhostClyde makeGhostOrange() {
        assert getGame() != null;
        GhostClyde g = new GhostClyde(new Dispersion());
        getGame().addGhost(g);
        return g;
    }

    @Override
    public GhostInky makeGhostCyan() {
        assert getGame() != null;
        GhostInky g = new GhostInky(new Dispersion());
        getGame().addGhost(g);
        return g;
    }

    @Override
    public GhostPinky makeGhostPink() {
        assert getGame() != null;
        GhostPinky g = new GhostPinky(new Dispersion());
        getGame().addGhost(g);
        return g;
    }

	@Override
	public Food makeFood() {
		assert getGame() != null;
		Food f = new Food();
		getGame().getPointManager().addPointsToBoard(f.getPoints());
		return f;
	}

	@Override
	public Wall makeWall() {
		return new Wall();
	}

	@Override
	public SuperGum makeSuperGum() {
		assert getGame() != null;
		SuperGum sg = new SuperGum();
		getGame().getPointManager().addPointsToBoard(sg.getPoints());
		return sg;
	}
	
	@Override
	public Board makeBoard(int w, int h) {
		assert getGame() != null;
		Board b = new Board(w, h);
		getGame().setBoard(b);
		return b;
	}
		
	/**
	 * @return The game created by this factory.
	 */
	protected Game getGame() {
		assert theGame != null;
		return theGame;
	}
}
