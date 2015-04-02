/**
 * 
 */
package org.jpacman.framework.Strategy;

import org.jpacman.framework.controller.AbstractGhostMover;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IGameInteractor;
import org.jpacman.framework.model.Tile;

/**
 * @author Sophie
 *
 */
public class Tracking implements IStrategy {
	
	private int xg, xp, yg, yp;
	private float distMin = 999;
	private Direction dir;
	
	@Override
	public Direction moveBlinky(Ghost g) {
		/* Blinky a une philosophie tres simple :  
		 * Droit au but ! . En toute circonstance, 
		 * il tente de se rendre sur la case ou se trouve 
		 * Pac-Man par le chemin le plus court. Il a une 
		 * vitesse de deplacement de 100%.
		 */
		Tile tilePacman = Game.getInstanceOfGame().getPlayer().getTile();
		calculateDir(tilePacman.getX(), tilePacman.getY(), g);
		return dir;

	}

	@Override
	public Direction movePinky(Ghost g) {
		/* Pinky aime tendre des embuscades. 
		 * Il devine ou sera Pac-Man 4 mouvements plus 
		 * tard et se rend à cette position. Il a une 
		 * vitesse de deplacement de 80%.
		 */
		Tile tileEnd;
		Tile tilePacman = Game.getInstanceOfGame().getPlayer().getTile();
		tileEnd = dirpossible(dirpossible(dirpossible(dirpossible(tilePacman))));
		calculateDir(tileEnd.getX(), tileEnd.getY(), g);
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

	@Override
	public Direction moveInky(Ghost g, Ghost blinky) {
		/* Inky est dicilement previsible pour un humain,
		 *  car son comportement depend des positions et des
		 *  directions de Pac-Man et de Blinky. Imaginez un 
		 *  vecteur joignant la position de Blinky a celle 
		 *  où se trouvera Pac-Man dans 2 mouvements. Doublez 
		 *  la longueur de ce vecteur. La position que Inky 
		 *  cherche a atteindre est a l'extremite de ce 
		 *  vecteur. Il a une vitesse de deplacement de 100%.
		 */
		boolean xPos = false, yPos = false;
		Tile tileEnd;
		Tile tilePacman = Game.getInstanceOfGame().getPlayer().getTile();
		tileEnd = dirpossible(dirpossible(tilePacman));
		xp = tilePacman.getX();
		yp = tilePacman.getY();
		xg = blinky.getTile().getX();
		yg = blinky.getTile().getY();
		int distX = ((xg-xp)^2)*2;
		int distY = ((yg-yp)^2)*2;
		if((xg-xp)>0){xPos = true;}
		if((yg-yp)>0){yPos = true;}
		if(xPos && yPos){
			calculateDir(g.getTile().getX()-distX, g.getTile().getY()-distY, g);
		}else if(xPos && !yPos){
			calculateDir(g.getTile().getX()-distX, g.getTile().getY()+distY, g);
		}else if(!xPos && !yPos){
			calculateDir(g.getTile().getX()+distX, g.getTile().getY()+distY, g);
		}else if(!xPos && yPos){
			calculateDir(g.getTile().getX()+distX, g.getTile().getY()-distY, g);
		}		
		return dir;

	}

	@Override
	public Direction moveClyde(Ghost g) {
		/*Clyde ne semble pas se preoccuper des autres. Il a 
		 * en fait deux modes de fonctionnement, et il passe 
		 * de l'un a l'autre en fonction de la distance 
		 * existant entre Pac-Man et lui. S'il se trouve à 
		 * plus de 8 cases de Pac-Man, Clyde a le m^eme 
		 * comportement que Blinky. Dans le cas contraire, il 
		 * se rend sur la case situee dans le coin inferieur 
		 * gauche du labyrinthe. Il a une vitesse de deplacement
		 *  de 100%.
		 */
		Tile tilePacman = Game.getInstanceOfGame().getPlayer().getTile();
		
		xp = tilePacman.getX();
		yp = tilePacman.getY();
		xg = g.getTile().getX();
		yg = g.getTile().getY();
		int dist = (((xg-xp)^2) + ((yg-yp)^2));
		if(dist > 8){
			calculateDir(tilePacman.getX(), tilePacman.getY(), g);
		}else{
			int hauteur = Game.getInstanceOfGame().getBoardInspector().getHeight();
			calculateDir(0, hauteur-1, g); //en supposant que le coin inférieur gauche a les coordonnées (0, hauteur-1)
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
