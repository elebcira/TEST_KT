/**
 * @author Elhadi
 *Aucun taxe n'est appliquée sur les produits de premières nécessités, à savoir la nourriture et les médicaments.
 *Une taxe sur la valeur ajoutée de 10% est appliquée sur les livres.
 *Une taxe sur la valeur ajoutée de 20% est appliquée sur tous les autres produits.

 *Une taxe additionnelle de 5% est appliquée sur les produits importés, sans exception.
*/
package com.kata.caltax.model;

import java.math.BigDecimal;

public enum TypeTaxe {

	PREM_NECESSITE(0), LIVRE(10), AUTRE(20), IMPORTATION(5);

	private int taxe;

	TypeTaxe(int taxe) {
		this.taxe = taxe;
	}

	public BigDecimal getTaxe() {
		return BigDecimal.valueOf(Double.valueOf(taxe));
	}

}
