/**
 *
 */
package org.jpacman.test.framework.ui;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.jpacman.framework.controller.AbstractGhostMover;
import org.jpacman.framework.controller.IController;
import org.jpacman.framework.controller.NormalGhostMover;
import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Stefan Hugtenburg - Jesse Donkervliet
 *
 */
public class MainUITest {

    private MainUI mainUI;

    /**
     * Create a MainUI to test with.
     */
    @Before
    public void setUp() {
        mainUI = new MainUI();
    }

    /**
     * Test to check the initialize function.
     * This function should create a game, while not changing the GhostController.
     * @throws FactoryException when calling MainUI.initialize() fails.
     */
    @Test
    public void testInitialize() throws FactoryException {
        assertNull(mainUI.getGame());
        IController ghostMover = mainUI.getGhostController();

        mainUI.initialize();

        assertNotNull(mainUI.getGame());
        assertEquals(ghostMover, mainUI.getGhostController());
    }

    /**
     * Test for the withGhostController function.
     * This setter should always work if createUI has not yet been called.
     * @throws FactoryException if the initialize or createUI functions fail.
     */
    @Test
    public void testWithGhostController() throws FactoryException {
        mainUI.initialize();
        assertNull(mainUI.getGhostController());

        IController ghostMover1 = new NormalGhostMover(mainUI.getGame());
        IController ghostMover2 = new NormalGhostMover(mainUI.getGame());

        //Below we apply forced pointer comparison to check the setter.
        mainUI.withGhostController(ghostMover1);
        assertSame(ghostMover1, mainUI.getGhostController());

        //We do a second set call to confirm the first one is gone.
        mainUI.withGhostController(ghostMover2);
        assertNotSame(ghostMover1, mainUI.getGhostController());
        assertSame(ghostMover2, mainUI.getGhostController());

        //The createUI function should not affect our current GhostController.
        mainUI.createUI();

        assertSame(ghostMover2, mainUI.getGhostController());

    }

    /**
     * Test to see the createUI function fail,
     * because no GhostController has been set.
     * @throws FactoryException if the initialize or createUI functions fail.
     */
    @Test
    public void testCreateUIWithoutGhostController() throws FactoryException {
        assumeTrue(MainUI.class.desiredAssertionStatus());

        boolean gotException = false;

        mainUI.initialize();
        //Do not set a GhostController, this should cause an assertion error.
        try {
            mainUI.createUI();
        }
        catch (AssertionError ae) {
            gotException = true;
        }
        assertTrue(gotException);
    }

    /**
     * Test to see the withGhostController function fail,
     * because the withGhostController should not be allowed to
     * be called after the UI has been created by createUI.
     * @throws FactoryException if the initialize or createUI functions fail.
     */
    @Test
    public void testChangeGhostControllerAfterUI() throws FactoryException {
        assumeTrue(MainUI.class.desiredAssertionStatus());
        mainUI.initialize();
        mainUI.withGhostController(new NormalGhostMover(mainUI.getGame()));
        mainUI.createUI();

        boolean gotException = false;

        //After creating the UI, the GhostController should not be allowed to be changed.
        //This should cause an assertion error.
        try {
            mainUI.withGhostController(new NormalGhostMover(mainUI.getGame()));
        }
        catch (AssertionError ae) {
            gotException = true;
        }
        assertTrue(gotException);
    }

    /**
     * Test to see if the initializeNormalGame works properly
     * and sets all necessary fields, while providing the game with a GhostMover.
     * @throws FactoryException if the initialize or createUI functions fail.
     */
    @Test
    public void testInitializeNormalGame() throws FactoryException {
        mainUI.initializeNormalGame();

        assertNotNull(mainUI.getGame());
        assertTrue(mainUI.getGhostController() instanceof AbstractGhostMover);
        //Check for the existence of the UI.
        assertNotNull(mainUI.eventHandler());
    }

//    /**
//     * @throws FactoryException
//     */
//    @Test
//    public void testWithFactory() throws FactoryException {
//
//        mainUI.initialize();
//        IGameFactory fact = null;
//
//        boolean gotException = false;
//
//        try {
//            mainUI.withFactory(fact);
//        }
//        catch (AssertionError ae) {
//            gotException = true;
//        }
//
//        assertTrue(gotException);
//
//        fact = new DefaultGameFactory();
//
//        try {
//            mainUI.withFactory(fact);
//        }
//        catch (AssertionError ae) {
//            gotException = false;
//        }
//
//        assertTrue(gotException);
//    }
//
//    /**
//     * @throws FactoryException
//     */
//    @Test
//    public void testWithButtonPanel() throws FactoryException {
//        mainUI.initialize();
//        ButtonPanel bp = new ButtonPanel();
//
//        boolean gotException = false;
//
//        try {
//            mainUI.withButtonPanel(bp);
//        } catch (AssertionError ae) {
//            gotException = true;
//        }
//        assertFalse(gotException);
//    }
}
