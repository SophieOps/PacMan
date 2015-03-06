package test.java.org.jpacman.test.framework.ui;

import main.java.org.jpacman.framework.model.GhostMover;
import main.java.org.jpacman.framework.factory.FactoryException;
import main.java.org.jpacman.framework.ui.IPacmanInteraction;
import main.java.org.jpacman.framework.ui.MainUI;
import org.junit.Test;

/**
 * Smoke test for the main UI: Just start it
 * and interact with it to see if no exceptions are thrown.
 * The bare minimum for any test suite.
 * 
 * @author Arie van Deursen, TU Delft, Feb 4, 2012
 */
public class MainUISmokeTest {
	
	/**
	 * Invoke series of actions on the real game, including the 
	 * real ghost mover.
	 * 
	 * @throws FactoryException If reading board fails.
	 * @throws InterruptedException Since we're timing.
	 */
	@Test
	public void testUIActions() throws FactoryException, InterruptedException {
		MainUI ui = new MainUI();
		ui.initializeNormalGame();
		ui.start();
		IPacmanInteraction eventHandler = ui.eventHandler();

		// now trigger interesting events.
		eventHandler.start();
		eventHandler.up();
		eventHandler.left();

		// give the ghosts some time to move.
		final int nrOfGhostMovesToWait = 10;
		Thread.sleep(nrOfGhostMovesToWait * GhostMover.DELAY);

		// and attempt some moves again.
		eventHandler.down();
		eventHandler.stop();
		eventHandler.start();
		eventHandler.right();
		
		// and we're done.
		eventHandler.exit();
	}
}