package org.jpacman.test.framework.ui;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by camro.
 */
public class PacmanKeyListenerTest {

    @Test
    public void testMovingKeyBindingUp() throws FactoryException{
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
        ui.withBoard("testMap.txt");
        ui.main();

        Tile pos = ui.getGame().getPlayer().getTile();
        Tile posTwo = ui.getGame().getBoardInspector().tileAt(pos.getX(),pos.getY()-1);


        robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_UP);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posTwo);

        ui.eventHandler().exit();
        robot.cleanUp();
    }

    @Test
    public void testMovingKeyBindingDown() throws FactoryException{
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
        ui.withBoard("testMap.txt");
        ui.main();

        Tile pos = ui.getGame().getPlayer().getTile();
        Tile posTwo = ui.getGame().getBoardInspector().tileAt(pos.getX(),pos.getY()+1);

        robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_DOWN);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posTwo);

        ui.eventHandler().exit();
        robot.cleanUp();
    }

    @Test
    public void testMovingKeyBindingLeft() throws FactoryException{
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
        ui.withBoard("testMap.txt");
        ui.main();

        Tile pos = ui.getGame().getPlayer().getTile();
        Tile posTwo = ui.getGame().getBoardInspector().tileAt(pos.getX()-1,pos.getY());

        robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_LEFT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posTwo);

        ui.eventHandler().exit();
        robot.cleanUp();
    }

    @Test
    public void testMovingKeyBindingRight() throws FactoryException{
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
        ui.withBoard("testMap.txt");
        ui.main();

        Tile pos = ui.getGame().getPlayer().getTile();
        Tile posTwo = ui.getGame().getBoardInspector().tileAt(pos.getX()+1,pos.getY());

        robot.click(robot.finder().findByName(ButtonPanel.START_BUTTON_NAME));
        robot.pressAndReleaseKey(KeyEvent.VK_RIGHT);
        robot.waitForIdle();
        assertEquals(ui.getGame().getPlayer().getTile(), posTwo);

        ui.eventHandler().exit();
        robot.cleanUp();
    }

    @Test
    public void testStateKeyBinding() throws FactoryException {
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();

        ui.main();

        Tile pos = ui.getGame().getPlayer().getTile();
        Tile posTwo = ui.getGame().getBoardInspector().tileAt(pos.getX()+1,pos.getY());

        robot.pressAndReleaseKey(KeyEvent.VK_S);
        robot.pressAndReleaseKey(KeyEvent.VK_RIGHT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posTwo);

        robot.pressAndReleaseKey(KeyEvent.VK_Q);
        robot.pressAndReleaseKey(KeyEvent.VK_LEFT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),posTwo);

        robot.pressAndReleaseKey(KeyEvent.VK_S);
        robot.pressAndReleaseKey(KeyEvent.VK_LEFT);
        robot.waitForIdle();

        assertEquals(ui.getGame().getPlayer().getTile(),pos);

        robot.click(robot.finder().findByName(ButtonPanel.EXIT_BUTTON_NAME));

        ui.eventHandler().exit();
        robot.cleanUp();
    }

    @Test
    public void testExitKeyBinding() throws FactoryException {
        MainUI ui = new MainUI();

        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();

        ui.main();
        assertTrue(ui.isVisible());

        robot.pressAndReleaseKey(KeyEvent.VK_X);
        robot.waitForIdle();
        assertFalse(ui.isVisible());

        ui.eventHandler().exit();
        robot.cleanUp();
    }
}
