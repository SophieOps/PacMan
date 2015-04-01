/**
 * 
 */
package org.jpacman.framework.Strategy;

import org.jpacman.framework.controller.AbstractGhostMover;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IGameInteractor;

/**
 * @author Sophie
 *
 */
public class Tracking implements IStrategy {
	
	@Override
	public void moveBlinky() {
		/* Blinky a une philosophie tres simple :  
		 * Droit au but ! . En toute circonstance, 
		 * il tente de se rendre sur la case ou se trouve 
		 * Pac-Man par le chemin le plus court. Il a une 
		 * vitesse de deplacement de 100%.
		 * */

	}

	@Override
	public void movePinky() {
		/* Pinky aime tendre des embuscades. 
		 * Il devine ou sera Pac-Man 4 mouvements plus 
		 * tard et se rend à cette position. Il a une 
		 * vitesse de deplacement de 80%.
		 */
		

	}

	@Override
	public void moveInky() {
		/* Inky est dicilement previsible pour un humain,
		 *  car son comportement depend des positions et des
		 *  directions de Pac-Man et de Blinky. Imaginez un 
		 *  vecteur joignant la position de Blinky a celle 
		 *  où se trouvera Pac-Man dans 2 mouvements. Doublez 
		 *  la longueur de ce vecteur. La position que Inky 
		 *  cherche a atteindre est a l'extremite de ce 
		 *  vecteur. Il a une vitesse de deplacement de 100%.
		 */

	}

	@Override
	public void moveClyde() {
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

	}

}
