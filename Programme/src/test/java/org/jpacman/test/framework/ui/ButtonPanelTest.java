package org.jpacman.test.framework.ui;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanKeyListener;
import org.junit.Test;
import org.mockito.Spy;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by camro.
 */

public class ButtonPanelTest {

    /**
     * Test the player can not move when the game is on pause
     * @throws FactoryException
     */
    @Test
    public void testButtonsStartPause() throws FactoryException {
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();

        ui.main();

        Tile pos = ui.getGame().getPlayer().getTile();
        Tile posRight = ui.getGame().getBoardInspector().tileAt(pos.getX()+1,pos.getY());

        robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_RIGHT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posRight);

        robot.click(robot.finder().findByName(ButtonPanel.STOP_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_LEFT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posRight);

        robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_LEFT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),pos);

        robot.click(robot.finder().findByName(ButtonPanel.EXIT_BUTTON_NAME));

        robot.cleanUp();
    }

    /**
     * Test that the window is closed when click on the exit button
     * @throws FactoryException
     */
    @Test
    public void testButtonExit() throws FactoryException{
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();

        ui.main();
        assertTrue(ui.isVisible());

        robot.click(robot.finder().findByName(ButtonPanel.EXIT_BUTTON_NAME));
        robot.waitForIdle();
        assertFalse(ui.isVisible());

        robot.cleanUp();
    }
}
