package com.kata.caltax.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.kata.caltax.model.TypeTaxe;

public class CalculTaxeUtil {

	public static BigDecimal getByPourcent(BigDecimal base, BigDecimal purcentage) {
		return base.multiply(purcentage).divide(new BigDecimal(100));
	}

	//Le montant de chacune des taxes est arrondi aux 5 centimes supérieurs
	public static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
		return increment.signum() == 0 ? value : value.divide(increment, 0, roundingMode).multiply(increment);
	}
	

	public static BigDecimal getTTC(BigDecimal taxFinal, BigDecimal ht, double quantite) {
		return ht.add(getByPourcent(ht, taxFinal));
	}

	public static BigDecimal getTotalTTC(BigDecimal ttc, double quantite) {
		return round(ttc.multiply(BigDecimal.valueOf(quantite)), BigDecimal.valueOf(0.05), RoundingMode.UP);
	}

	
	public static BigDecimal getTotalHT(BigDecimal ht, double quantite) {
		return ht.multiply(BigDecimal.valueOf(quantite));
	}

	// Une taxe additionnelle de 5% est appliquée sur les produits importés, sans
	public static BigDecimal getTaxe(BigDecimal tax, boolean isImportation) {
		return isImportation ? tax.add(TypeTaxe.IMPORTATION.getTaxe()) : tax;
	}


}
