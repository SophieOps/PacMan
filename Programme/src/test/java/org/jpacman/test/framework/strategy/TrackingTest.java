package org.jpacman.test.framework.strategy;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Game;
import org.junit.Test;

/**
 * Created by camro on 2/04/2015.
 */
public class TrackingTest {

    @Test
    public void testSuperGumEat()throws FactoryException {
        String[] map = {
                "######",
                "#P..R#",
                "######",
                "#SSSS#"};

        MapParser p = new MapParser(new DefaultGameFactory());
        Game theGame = p.parseMap(map);
    }
}
