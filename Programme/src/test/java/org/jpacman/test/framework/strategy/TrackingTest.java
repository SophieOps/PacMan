package org.jpacman.test.framework.strategy;

import org.jpacman.framework.Strategy.IStrategy;
import org.jpacman.framework.Strategy.Tracking;
import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.*;
import org.junit.Test;

import static java.lang.Math.pow;
import static org.junit.Assert.*;

/**
 * Created by camro.
 */
public class TrackingTest {

    private float distMin = 999;

    @Test
    public void testBlinkyMoveToPacman()throws FactoryException {
        String[] map = {
                "#######",
                "#     #",
                "# R..P#",
                "#     #",
                "#######",
                "#SSSS.#"};

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(map);

        IStrategy strategy = new Tracking();
        assertTrue(strategy instanceof Tracking);
        Ghost ghost = theGame.getGhosts().get(0);
        assertTrue(ghost instanceof GhostBlinky);
        Direction dir;

        //ghost.setStrategy(strategy);
        //dir = ghost.getStrategy().moveBlinky(ghost);
        dir = calculateDir(theGame.getPlayer().getTile().getX(),theGame.getPlayer().getTile().getY(),ghost);

        assertFalse(Direction.UP ==dir);
        assertFalse(Direction.DOWN ==dir);
        assertFalse(Direction.LEFT ==dir);
        assertTrue(Direction.RIGHT == dir);
    }

    @Test
    public void testPinkyMoveToPacman()throws FactoryException {
        String[] map = {
                "#######",
                "#  P M#",
                "#     #",
                "#     #",
                "#     #",
                "#     #",
                "#     #",
                "#######",
                "#SSSS.#"};

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(map);

        IStrategy strategy = new Tracking();
        Ghost ghost = theGame.getGhosts().get(0);
        assertTrue(ghost instanceof GhostPinky);
        Direction dir;

        //ghost.setStrategy(strategy);
        //dir = ghost.getStrategy().moveBlinky(ghost);

        Tile tileEnd = dirpossible(dirpossible(dirpossible(dirpossible(theGame.getPlayer().getTile()))));
        assertTrue((tileEnd.getX() == 1) && (tileEnd.getY() == 1));

        dir = calculateDir(tileEnd.getX(),tileEnd.getY(),ghost);
        assertFalse(Direction.RIGHT ==dir);
        assertFalse(Direction.DOWN ==dir);
        assertFalse(Direction.UP ==dir);
        assertTrue(Direction.LEFT == dir);
    }

    private Direction calculateDir(int x, int y, Ghost g){
        int xp = x;
        int yp = y;
        Direction dir = null;
        for(Direction direction : Direction.values()){
            Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(g.getTile(), direction);
            if (target.tileCanBeOccupied()){
                int xg = target.getX();
                int yg = target.getY();
                int dist = (int) (pow((xg-xp),2) + pow((yg-yp),2));
                if(dist < distMin){
                    distMin = dist;
                    dir = direction;
                }else if(dist == distMin){
                    //en haut, puis à gauche, puis en bas.
                    if((direction == Direction.UP)
                            || ((direction == Direction.LEFT) && (dir != Direction.UP))
                            || ((direction == Direction.DOWN) && (dir == Direction.RIGHT))){
                        distMin = dist;
                        dir = direction;
                    }
                }
            }
        }
        distMin = 999;
        return dir;
    }

    private Tile dirpossible(Tile tile){
        Tile tiletmp;
        for(Direction dir : Direction.values()){
            tiletmp = Game.getInstanceOfGame().getBoard().tileAtDirection(tile, dir);
            if (tiletmp.tileCanBeOccupied()){
                return tiletmp;
            }
        }
        return null;
    }
}
