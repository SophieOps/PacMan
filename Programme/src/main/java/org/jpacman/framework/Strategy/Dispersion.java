package org.jpacman.framework.Strategy;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Tile;


public class Dispersion implements IStrategy {

	private int xg, xp, yg, yp;
	private float distMin = 999;
	private Direction dir;
	private int hauteur;
	private int largeur;
	private boolean home = false;
  
	@Override
	public Direction moveBlinky(Ghost g) {
		/* Blinky se rend dans le coin superieur droit.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de droite
		 */
		if(!home){
			largeur = Game.getInstanceOfGame().getBoardInspector().getWidth();
			calculateDir(largeur-1, 0, g);
		}else{
			dir = Direction.RIGHT;
		}
		return dir;
	}

	@Override
	public Direction movePinky(Ghost g) {
		/* Pinky se rend dans le coin superieur gauche.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de gauche
		 */
		if(!home){
			calculateDir(0, 0, g);
		}else{
			dir = Direction.LEFT;
		}
		return dir;
	}

	@Override
	public Direction moveInky(Ghost g, Ghost blinky) {
		/* Inky se rend dans le coin inferieur droit.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de droite
		 */
		if(!home){
			largeur = Game.getInstanceOfGame().getBoardInspector().getWidth();
			hauteur = Game.getInstanceOfGame().getBoardInspector().getHeight();
			calculateDir(largeur-1, hauteur-1, g);
		}else{
			dir = Direction.RIGHT;
		}
		return dir;
	}

	@Override
	public Direction moveClyde(Ghost g) {
		/* Clyde se rend dans le coin inferieur gauche.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de gauche
		 */
		if(!home){
			hauteur = Game.getInstanceOfGame().getBoardInspector().getHeight();
			calculateDir(0, hauteur-1, g);
		}else{
			dir = Direction.LEFT;
		}
		return dir;
	}

	private void calculateDir(int x, int y, Ghost g){
		xp = x;
		yp = y;
		for(Direction direction : Direction.values()){
			Tile target = Game.getInstanceOfGame().getBoard().tileAtDirection(g.getTile(), direction);
			if (target.tileCanBeOccupied()){
				xg = target.getX();
				yg = target.getY();
				int dist = (((xg-xp)^2) + ((yg-yp)^2));
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
		return ;
	}
	
	
	
	
	
	
}
