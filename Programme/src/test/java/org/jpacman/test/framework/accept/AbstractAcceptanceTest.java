package test.java.org.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import main.java.org.jpacman.framework.model.Controller;
import main.java.org.jpacman.framework.factory.FactoryException;
import main.java.org.jpacman.framework.model.Ghost;
import main.java.org.jpacman.framework.model.Player;
import main.java.org.jpacman.framework.model.Tile;
import main.java.org.jpacman.framework.ui.IPacmanInteraction;
import main.java.org.jpacman.framework.ui.MainUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Class containing common fixture for acceptance tests.
 * 
 * @author Arie van Deursen, TU Delft, Feb 26, 2012
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractAcceptanceTest {
	
	private Player thePlayer;

	/**
	 * Simple map used for acceptance testing purposes.
	 */
	public static final String ACCEPTANCE_MAP = "acceptanceMap.txt";
	
	
	/**
	 * To ensure control over ghost moves, we must replace
	 * the random monster moves with a more controlled one.
	 */
	@Mock private Controller mockedGhostMover;


	/**
	 * Main engine to conduct user interaction with the model.
	 * Normally invoked via the gui -- in this test suite
	 * invoked programmatically.
	 * Deprecated: will be made private.
	 */
	private IPacmanInteraction engine;
	
	/**
	 * The main (Swing) user interface.
	 */
	private MainUI theUI;

	/**
	 * Setup the game, given a simpler map to
	 * improve controllability.
	 * 
	 * @throws FactoryException When map loading fails.
	 * @throws InterruptedException When controllers fail.
	 */
	@Before
	public void setUp() throws FactoryException, InterruptedException {
		theUI = makeUI()
			.withBoard(ACCEPTANCE_MAP)
		    .withGhostController(mockedGhostMover)
		    .initialize()
		    .createUI();	
		theUI.start();
		engine = theUI.eventHandler();
		thePlayer = (Player) tileAt(1, 1).topSprite();
	}
	
	
	/**
	 * Factory method to make the UI to be tested.
	 * @return A new instance of Pacman's top level user interface.
	 */
	public MainUI makeUI() {
		return new MainUI();
	}

	/**
	 * Ensure the UI gets closed after the tests are done.
	 * (Otherwise awt/eclipse will complain when multiple
	 * test runs are conducted).
	 */
	@After
	public void closeUI() {
		getEngine().exit();
	}

	/**
	 * @return The player of the game.
	 */
	protected Player getPlayer() {
		assert thePlayer != null : "Player must be initialized.";
		return thePlayer;
	}
	
	/**
	 * @return The ghost mover for this game.
	 */
	protected Controller getMockedGhostMover() {
		return mockedGhostMover;
	}

	/**
	 * @return The interactor with the underlying model.
	 */
	protected IPacmanInteraction getEngine() {
		return engine;
	}
	
	/**
	 * @return The top level user interface.
	 */
	protected MainUI getUI() {
		return theUI;
	}
	
	/**
	 * Convenience method to retun a tile at a given location.
	 * Helpful for testing effect of moves.
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @return Tile at (x,y)
	 */
	protected Tile tileAt(int x, int y) {
		return getUI().getGame().getBoardInspector().tileAt(x, y);
	}
	
	/**
	 * Basic sanity checks on initial setting.
	 */
	@Test
	public void testInitialSetup() {
		Tile playerTile = tileAt(1, 1);
		assertEquals(playerTile, getPlayer().getTile());
		assertTrue(getPlayer().isAlive());
        assertEquals(0, getPlayer().points);
	}


	/**
	 * @return The single ghost living in this game.
	 */
	public Ghost theGhost() {
		// we know there's just one ghost on the map.
		List<Ghost> allGhosts = getUI().getGame().getGhosts();
		assert allGhosts.size() == 1;
		return allGhosts.get(0);
	}
}