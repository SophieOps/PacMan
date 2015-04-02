package org.jpacman.test.framework.strategy;


import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.ui.MainUI;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by camro.
 */
public class EscapeTest {

    /**
     * test that simulates the random direction and checks if the ghost is the right direction
     * @throws FactoryException
     */
    @Test
    public void testMove() throws FactoryException {
        MainUI ui = new MainUI();

        ui.withBoard("pathMap.txt");
        ui.initializeNormalGame();

        Ghost ghost = ui.getGame().getGhosts().get(0);

        Direction dir = Direction.values()[0];
        int dirIndex = 3;
        dir = Direction.values()[dirIndex];

        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 2) && (ghost.getTile().getY() == 1));

        dirIndex = 2;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 2) && (ghost.getTile().getY() == 2));

        dirIndex = 2;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 2) && (ghost.getTile().getY() == 3));

        dirIndex = 3;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 3) && (ghost.getTile().getY() == 3));

        dirIndex = 3;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 4) && (ghost.getTile().getY() == 3));

        dirIndex = 3;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 5) && (ghost.getTile().getY() == 3));

        dirIndex = 0;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 5) && (ghost.getTile().getY() == 2));

        dirIndex = 0;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 5) && (ghost.getTile().getY() == 1));

        dirIndex = 1;
        dir = Direction.values()[dirIndex];
        ui.getGame().moveGhost(ghost, dir);
        assertTrue((ghost.getTile().getX() == 4) && (ghost.getTile().getY() == 1));

    }
}
