package org.jpacman.framework.Strategy;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;


public class Dispersion implements IStrategy {

  
	@Override
	public Direction moveBlinky(Ghost g) {
		/* Blinky se rend dans le coin superieur droit.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de droite
		 */
		
		
		return null;

	}

	@Override
	public Direction movePinky() {
		/* Pinky se rend dans le coin superieur gauche.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de gauche
		 */
		
		return null;
	}

	@Override
	public Direction moveInky() {
		/* Inky se rend dans le coin inferieur droit.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de droite
		 */
		return null;
		
	}

	@Override
	public Direction moveClyde() {
		/* Clyde se rend dans le coin inferieur gauche.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de gauche
		 */
		return null;

	}

}
