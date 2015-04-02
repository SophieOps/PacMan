package org.jpacman.test.framework.factory;

import static org.junit.Assert.assertEquals;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.SuperGum;
import org.junit.Before;
import org.junit.Test;

/**
 * Test suite addressing collaboration between parser
 * and actual factory.
 * 
 * @author Arie van Deursen, TU Delft, Feb 27, 2012
 */
public class FactoryIntegrationTest {

    private MapParser parser;

    private final String[] map = new String[] {
            "######",
            "#S.. #",
            "#GP  #",
            "#SSS #",
            "#  . #",
            "#ROCM#",
            "######"
    };


    /**
     * Create the standard factory and parser.
     */
    @Before
    public void setUp() {
        IGameFactory factory = new DefaultGameFactory();
        parser = new MapParser(factory);
    }

    /**
     * Test parsing of a larger map.
     * Individual unit tests address specific cases; here we focus
     * on the overall results.
     *
     * @throws FactoryException If parsing fails. Should not happen.
     */
    @Test
    public void testFullMap() throws FactoryException {
        Game g = parser.parseMap(map);
        Board b = g.getBoard();

        // did we recognize the right sprites?
        assertEquals(SpriteType.EMPTY, b.spriteTypeAt(1, 4));
        assertEquals(SpriteType.PLAYER, b.spriteTypeAt(2, 2));
        assertEquals(SpriteType.GHOST, b.spriteTypeAt(1, 2));
        assertEquals(SpriteType.WALL, b.spriteTypeAt(0, 0));
        assertEquals(SpriteType.FOOD, b.spriteTypeAt(2, 1));
        assertEquals(SpriteType.SUPERGUM, b.spriteTypeAt(1,1));
        assertEquals(SpriteType.GHOSTBLINKY, b.spriteTypeAt(1,5));
        assertEquals(SpriteType.GHOSTCLYDE, b.spriteTypeAt(2,5));
        assertEquals(SpriteType.GHOSTINKY, b.spriteTypeAt(3,5));
        assertEquals(SpriteType.GHOSTPINKY, b.spriteTypeAt(4,5));

        // did we properly set the player?
        assertEquals(g.getPlayer(), b.spriteAt(2, 2));

        // were all ghosts added?
        assertEquals(5, g.getGhosts().size());

        // was the food actually added?
        final int cellsWithFoodCount = 3;
        final int cellsWithSuperGumCount = 4;
        assertEquals(cellsWithFoodCount * Food.DEFAULT_POINTS + cellsWithSuperGumCount* SuperGum.DEFAULT_POINTS,
                g.getPointManager().totalFoodInGame());
    }
}
