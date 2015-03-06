package test.java.org.jpacman.test.framework.ui;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertTrue;
import java.awt.event.KeyEvent;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import main.java.org.jpacman.framework.factory.FactoryException;
import main.java.org.jpacman.framework.ui.ButtonPanel;
import main.java.org.jpacman.framework.ui.IPacmanInteraction;
import main.java.org.jpacman.framework.ui.MainUI;
import main.java.org.jpacman.framework.ui.PacmanKeyListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test if the MainUI keeps responding to keystrokes when a focus traversal key
 * has been pressed and thus didn't lose focus.
 * 
 * @author Rik Nijessen, TU Delft, Mar 2, 2013
 */
@RunWith(MockitoJUnitRunner.class)
public class MainUIFocusTest {

	@Spy
	private PacmanKeyListener pi;

	/**
	 * Perform a tab key press to check if the MainUI is handling focus
	 * traversal properly.
	 * 
	 * @throws FactoryException
	 *             If reading board fails.
	 */
	@Test
	public void testFocusHandling() throws FactoryException {

		MainUI ui = new MainUI();
		ui.withModelInteractor(pi);
		Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();

		ui.main();
		
		//hit start
	    robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));

		
		// perform tab key press and wait until it took place
		robot.pressAndReleaseKey(KeyEvent.VK_TAB);
		robot.waitForIdle();

		// perform left key press and wait until it took place
		robot.pressAndReleaseKey(KeyEvent.VK_LEFT);
		robot.waitForIdle();

		// verify that the game registered the left key press and that it didn't
		// lose focus
		verify(pi).left();
		assertTrue(ui.isFocusOwner());

	}

}