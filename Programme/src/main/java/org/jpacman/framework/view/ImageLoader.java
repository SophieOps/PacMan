package org.jpacman.framework.view;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;

/**
 * The responsibilities of this class include obtaining images from file,
 * that can be used for animations of the player and the monsters in Pacman.
 *
 * @author Arie van Deursen, Delft University of Technology, May 2007
 *
 */

public class ImageLoader {

    /**
     * Animation sequence of images for monsters.
     */
    private Image[] monsterImageRed;
    private Image[] monsterImageOrange;
    private Image[] monsterImageCyan;
    private Image[] monsterImagePink;
    private Image[] monsterImageScared;

    /**
     * Animation sequence of images for the player.
     */
    private Image[][] playerImage;  
    
    /**
     * Width of the images.
     */
    private int width = -1;
    
    /**
     * Height of the images.
     */
    private int height = -1;
    
    /**
     * Create an empty (non intialized) image factory.
     */
    public ImageLoader() { /* Nothing needs to be done */ }
    
    /**
     * Create an empty (non initialized) image factory
     * requiring that all images are of the given (width, height).
     * @param w requested image width
     * @param h requested image height
     */
    public ImageLoader(int w, int h) { 
        width = w;
        height = h;
    }
     
    
    /**
     * Read images for player and monsters from file.
     * Different images exist for different phases of the animation.
     * @throws org.jpacman.framework.factory.FactoryException if the images can't be found.
     */
    public void loadImages() throws FactoryException {
    	try {
            monsterImageRed = new Image[]{
                    getImage("GhostBlinky1.gif"),
                    getImage("GhostBlinky2.gif")};
            monsterImageOrange= new Image[]{
                    getImage("GhostClyde1.gif"),
                    getImage("GhostClyde2.gif")};
            monsterImageCyan= new Image[]{
                    getImage("GhostInky1.gif"),
                    getImage("GhostInky2.gif")};
            monsterImagePink= new Image[]{
                    getImage("GhostPinky1.gif"),
                    getImage("GhostPinky2.gif")};
            monsterImageScared = new Image[]{
                    getImage("GhostScared1.gif"),
                    getImage("GhostScared2.gif")};

    		String[] sequence = new String[]{"2", "3", "4", "3", "2"};
    		playerImage = new Image[Direction.values().length][sequence.length + 1];
    		for (Direction d : Direction.values()) {
    			int dir = d.ordinal();
    			playerImage[dir][0] = getImage("PacMan1.gif");
    			for (int seq = 0; seq < sequence.length; seq++) {
    				String name = "PacMan" + sequence[seq] + d.toString().toLowerCase() + ".gif";
    				playerImage[dir][seq + 1] = getImage(name);
    			}
    		}
    	} catch (IOException io) {
    		throw new FactoryException("Can't load images", io);
    	}
    }

    /**
     * @return Number of different monster animation steps
     */
    public int monsterAnimationCount() {
        assert monsterImageRed != null : "Monster image should not be null.";
        int result = monsterImageRed.length;
        assert result >= 0;
        return result;
    }

    /**
     * @return Number of different player animation steps
     */
    public int playerAnimationCount() {
        assert playerImage != null;
        assert playerImage[0] != null;
        return playerImage[0].length;
    }


    /**
     * Get a player (pizza slice) in the appropriate direction at the
     * given animation sequence.
     * 
     * @param dir Direction pacman is moving to.
     * @param anim Animation step
     * @return Player image in appropriate direction.
     */
    public Image player(Direction dir, int anim) {
        assert anim >= 0;
        Image img = playerImage[dir.ordinal()][anim % playerAnimationCount()];
        assert img != null;
        return img;
    }

    /**
     * Obtain a picture of a monster.
     * @param animationIndex counter indicating which animation to use.
     * @return The monster image at the given animation index.
     */
    public Image monsterRed(int animationIndex) {
        assert animationIndex >= 0;
        return monsterImageRed[animationIndex % monsterAnimationCount()];
    }

    public Image monsterOrange(int animationIndex) {
        return monsterImageOrange[animationIndex % monsterAnimationCount()];
    }

    public Image monsterCyan(int animationIndex) {
        return monsterImageCyan[animationIndex % monsterAnimationCount()];
    }

    public Image monsterPink(int animationIndex) {
        return monsterImagePink[animationIndex % monsterAnimationCount()];
    }

    /**
     * Obtain a picture of a monster scared.
     * @param animationIndex counter indicating which animation to use.
     * @return The monster image scared at the given animation index.
     */
    public Image monsterScared(int animationIndex) {
        return monsterImageScared[animationIndex % monsterAnimationCount()];
    }

    /**
     * Obtain an image from a file / resource that can
     * be found on the classpath.
     * @param name The file containg, e.g., a .gif picture.
     * @return The corresponding Image.
     * @throws IOException If file can't be found.
     */
    private Image getImage(String name) throws IOException {
        assert name != null;
        
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL picfile = cl.getResource(name);
        if (picfile == null) {
            throw new IOException("Can't load image: "  + name);
        }
        Image result = new ImageIcon(picfile).getImage();
        assert result != null;
 
        return resize(result);
    }
    
     
    /**
     * Resize a given image to the required dimension.
     * @param im The image
     * @return The resized image.
     */
    Image resize(Image im) {
        assert im != null;
        Image result = im;
        if (width > 0 && height > 0) {
            int w = im.getWidth(null);        
            int h = im.getHeight(null);
            if (w != width || h != height) {
                result = im.getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
            }
        }
        assert result != null;
        return result;
    }
}
