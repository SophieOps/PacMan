package org.jpacman.framework.Strategy;


public class Dispersion implements IStrategy {

  
	@Override
	public void moveBlinky() {
		/* Blinky se rend dans le coin superieur droit.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de droite
		 */

	}

	@Override
	public void movePinky() {
		/* Pinky se rend dans le coin superieur gauche.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de gauche
		 */
		

	}

	@Override
	public void moveInky() {
		/* Inky se rend dans le coin inferieur droit.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de droite
		 */
		
	}

	@Override
	public void moveClyde() {
		/* Clyde se rend dans le coin inferieur gauche.
		 * Une fois sa position "maison" atteinte, le fant^ome 
		 * va se deplacer dans un circuit qui consiste a 
		 * toujours emprunter le chemin de gauche
		 */

	}

}
