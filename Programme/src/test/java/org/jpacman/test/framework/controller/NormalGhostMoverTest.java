package org.jpacman.test.framework.controller;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by camro on 2/04/2015.
 */
public class NormalGhostMoverTest {

    private String[] mapNoNode = {
            "#####",
            "#G#P#",
            "###S#",
            "#SSS#"};

    private String[] mapOneNode = {
            "#####",
            "#G#P#",
            "# #S#",
            "#SSS#"};

    private String[] mapTwoNode = {
            "# ###",
            "#G#P#",
            "# #S#",
            "#SSS#"};

    private String[] mapThreeNode = {
            "# ###",
            " G#P#",
            "# #S#",
            "#SSS#"};

    private String[] mapFourNode = {
            "# ###",
            " G P#",
            "# #S#",
            "#SSS#"};

    private int possibleDirection = 0;

    @Test
    public void testNoAtNode() throws FactoryException{

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(mapNoNode);

        Ghost g = theGame.getGhosts().get(0);

        Tile tile = g.getTile();
        for(Direction dir : Direction.values()){
            Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
            if (target.tileCanBeOccupied()){
                possibleDirection ++;
            }
        }
        assertEquals(possibleDirection, 0);
    }

    @Test
    public void testOneAtNode() throws FactoryException{

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(mapOneNode);

        Ghost g = theGame.getGhosts().get(0);

        Tile tile = g.getTile();
        for(Direction dir : Direction.values()){
            Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
            if (target.tileCanBeOccupied()){
                possibleDirection ++;
            }
        }
        assertEquals(possibleDirection, 1);
    }

    @Test
    public void testTwoAtNode() throws FactoryException{

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(mapTwoNode);

        Ghost g = theGame.getGhosts().get(0);

        Tile tile = g.getTile();
        for(Direction dir : Direction.values()){
            Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
            if (target.tileCanBeOccupied()){
                possibleDirection ++;
            }
        }
        assertEquals(possibleDirection, 2);
    }

    @Test
    public void testThreeAtNode() throws FactoryException{

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(mapThreeNode);

        Ghost g = theGame.getGhosts().get(0);

        Tile tile = g.getTile();
        for(Direction dir : Direction.values()){
            Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
            if (target.tileCanBeOccupied()){
                possibleDirection ++;
            }
        }
        assertEquals(possibleDirection, 3);
    }

    @Test
    public void testFourAtNode() throws FactoryException{

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(mapFourNode);

        Ghost g = theGame.getGhosts().get(0);

        Tile tile = g.getTile();
        for(Direction dir : Direction.values()){
            Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
            if (target.tileCanBeOccupied()){
                possibleDirection ++;
            }
        }
        assertEquals(possibleDirection, 4);
    }

}
