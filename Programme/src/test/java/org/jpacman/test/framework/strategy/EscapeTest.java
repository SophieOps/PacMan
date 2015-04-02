package org.jpacman.test.framework.strategy;


import org.jpacman.framework.Strategy.Escape;
import org.jpacman.framework.Strategy.IStrategy;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by camro.
 */
public class EscapeTest {

    private static Random randomizer = new Random();
    private IStrategy escape = new Escape();

    @Test
    public void testValueOfRandom() throws FactoryException{

        Direction dir = Direction.values()[0];
        int dirIndex = randomizer.nextInt(Direction.values().length);
        assertTrue((0 == dirIndex) || (1 == dirIndex) || (2 == dirIndex) || (3 == dirIndex));
        assertFalse(dirIndex>=4);
        dir = Direction.values()[dirIndex];
        assertNotNull(dir);

    }

    /**
     * Test if the ghost move after get a direction random
     * @throws FactoryException
     */
    @Test
    public void TestGhostMoveAfterGetDir() throws FactoryException {
        MainUI ui = new MainUI();

        ui.withBoard("testMap.txt");
        ui.initializeNormalGame();

        Ghost ghost = ui.getGame().getGhosts().get(0);
        assertNotNull(ghost);
        Tile pos = ghost.getTile();
        Direction dir;
        assertNotNull(escape);

        ghost.setStrategy(escape);
        dir = ghost.getStrategy().moveBlinky(ghost);

        assertEquals(pos, ghost.getTile());
        ui.getGame().moveGhost(ghost, dir);
        assertNotEquals(pos, ghost.getTile());
    }

    /**
     * test with simulates the random direction and checks if the ghost is the right direction
     * @throws FactoryException
     */
    @Test
    public void testDirectionOfGhost() throws FactoryException {
        MainUI ui = new MainUI();

        ui.withBoard("pathMap.txt");
        ui.initializeNormalGame();

        Ghost ghost = ui.getGame().getGhosts().get(0);
        ghost.setStrategy(escape);
        Direction dir;
        int dirIndex = 3;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"RIGHT");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 2) && (ghost.getTile().getY() == 1));

        dirIndex = 2;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"DOWN");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 2) && (ghost.getTile().getY() == 2));

        dirIndex = 2;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"DOWN");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 2) && (ghost.getTile().getY() == 3));

        dirIndex = 3;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"RIGHT");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 3) && (ghost.getTile().getY() == 3));

        dirIndex = 3;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"RIGHT");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 4) && (ghost.getTile().getY() == 3));

        dirIndex = 3;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"RIGHT");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 5) && (ghost.getTile().getY() == 3));

        dirIndex = 0;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"UP");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 5) && (ghost.getTile().getY() == 2));

        dirIndex = 0;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"UP");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 5) && (ghost.getTile().getY() == 1));

        dirIndex = 1;
        dir = Direction.values()[dirIndex];
        assertEquals(dir.name(),"LEFT");
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 4) && (ghost.getTile().getY() == 1));

    }


}
