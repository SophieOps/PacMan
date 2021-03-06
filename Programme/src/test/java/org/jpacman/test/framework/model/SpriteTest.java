package org.jpacman.test.framework.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.Tile;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class addressing how sprites occupy or deoccupy tiles.
 *
 * @author Arie van Deursen, TU Delft, Dec 24, 2011
 */
public class SpriteTest {

    private final Sprite john = new Sprite() { };
    private final Tile center = new Tile(0, 0);

    /**
     * Initial situation: Sprite John is in the middle.
     */
    @Before
    public void setUp() {
        john.occupy(center);
    }



    /**
     * Test what happens if we relocate a sprite.
     */
    @Test
    public void relocateSprite() {

        //   TODO: make it working

        john.deoccupy();
        Tile north = new Tile(0, 1);
        john.occupy(north);

        // john now lives at north
        assertThat(north.topSprite(), equalTo(john));

        // but john doesn't live in the center anymore.
        assertFalse(center.containsSprite(john));
    }

    /**
     * Test what happens if there are multiple sprites on one tile.
     */
    @Test
    public void multipleSprites() {
        Sprite david = new Sprite() {
        };
        david.occupy(center);

        // now David is the top most sprite.
        assertThat(center.topSprite(), equalTo(david));

        // but john is still there too.
        assertTrue(center.containsSprite(john));
        assertThat(john.getTile(), equalTo(center));
    }

    /**
     * Testing moves, now with custom made matcher.
     */
    @Test
    public void testMoveWithMatchers() {
        Tile north = new Tile(0, 1);
        john.deoccupy();
        john.occupy(north);
        assertThat(john, occupies(north));
        // assertThat(john, not(occupies(center)));
    }



    /**
     * @param expected
     *            The tile the sprite should be on.
     * @return A hamcrest matcher telling whether a sprite is on
     *         a given tile.
     */
    public static Matcher<Sprite> occupies(final Tile expected) {
        return new FeatureMatcher<Sprite, Tile>(equalTo(expected), "occupying", "tile") {
            @Override
            protected Tile featureValueOf(Sprite actual) {
                return actual.getTile();
            }
        };
    }
}
