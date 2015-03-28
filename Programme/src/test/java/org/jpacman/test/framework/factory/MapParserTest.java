package org.jpacman.test.framework.factory;

import static org.junit.Assert.assertEquals;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by camro
 */
public class MapParserTest {

    private MapParser parser;
    private final String[] testMap = new String[] {
            "# .#",
            ".PG#",
            "## #"
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
     * Test if the map is empty
     * @throws FactoryException
     */
    @Test (expected = FactoryException.class)
    public void testEmptyMap() throws FactoryException {
        parser.parseMap(new String[] {});
    }

    /**
     * Test if there is a row of the map empty
     * @throws FactoryException
     */
    @Test (expected = FactoryException.class)
    public void testEmptyRowMap() throws FactoryException {
        parser.parseMap(new String[] {""});
    }

    /**
     * Test if there is a row of the map that does not have the right length
     * @throws FactoryException
     */
    @Test (expected = FactoryException.class)
    public void testIncorrectRowMap() throws FactoryException {
        parser.parseMap(new String[] {"####","#  #","###"});
    }

    /**
     * Test if there is a bad character
     * @throws FactoryException
     */
    @Test (expected = FactoryException.class)
    public void testIncorrectCharacters() throws FactoryException {
        parser.parseMap(new String[] {"#@##"});
    }

    /**
     * Test if the file is incorrect or nonexistent
     * @throws FactoryException
     */
    @Test (expected = FactoryException.class)
    public void testIncorrectMapFile() throws FactoryException {
        parser.parseFromFile("IncorrectFile.txt");
    }

    /**
     * Test if the map provided by the reader is correct
     * @throws FactoryException
     */
    @Test
    public void testGetMapBuffered() throws FactoryException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream result = cl.getResourceAsStream("acceptanceMap.txt");
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(result));
        String[] mapString = parser.getMap(br);
        assertEquals(testMap.length,mapString.length);
        for(int i=0; i<testMap.length;i++) {
            assertEquals(testMap[i],mapString[i]);
        }
    }
}
