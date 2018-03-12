/**
 * @author Elhadi
 *Le montant TTC est calculé comme suit :
 *Pttc = Pht + somme(arrondi(Pht*t/100))
 *Pttc: Prix TTC
 *Pht : Prix hors taxes
 *t : taxe applicable
 */

package com.kata.caltax.service.Impl;

import static com.kata.caltax.util.CalculTaxeUtil.getTTC;
import static com.kata.caltax.util.CalculTaxeUtil.getTaxe;
import static com.kata.caltax.util.CalculTaxeUtil.getTotalHT;
import static com.kata.caltax.util.CalculTaxeUtil.getTotalTTC;

import java.math.BigDecimal;
import java.util.function.Consumer;

import com.kata.caltax.model.Facture;
import com.kata.caltax.model.InputPanier;
import com.kata.caltax.model.Produit;
import com.kata.caltax.service.CalculTaxeService;


public class CalculServiceImpl implements CalculTaxeService {

	@Override
	public void calculerFacture(Facture facture) {
		//CALCULER_TAX
		facture.getInputPaniers().stream().flatMap(input -> input.getInput().stream()).forEach(CALCULER_TAX);
		//CALCULER_TOTAL
		facture.getInputPaniers().stream().forEach(CALCULER_TOTAL);
	}

	private Consumer<InputPanier> CALCULER_TOTAL = input -> {
		input.setTotal(getTotal(input));
		input.setMontantTaxes(getMontantTaxes(input));
	};

	private Consumer<Produit> CALCULER_TAX = produit -> {
		BigDecimal totalHT = getTotalHT(produit.getHt(), produit.getQualtite());
		produit.setTotalHt(totalHT);

		BigDecimal taxe = getTaxe(produit.getTypeTaxe().getTaxe(), produit.isImportation());
		BigDecimal ttc = getTTC(taxe, produit.getHt(), produit.getQualtite());
		produit.setTtc(ttc);

		BigDecimal totalTTC = getTotalTTC(produit.getTtc(), produit.getQualtite());
		produit.setTotalTtc(totalTTC);

	};
	
	
	private BigDecimal getMontantTaxes(InputPanier input) {
		return input.getTotal().subtract(input.getInput().stream().map(p -> p.getTotalHt()).reduce((BigDecimal.valueOf(0)), (p1, sum) -> p1 = sum.add(p1)));
	}

	private BigDecimal getTotal(InputPanier input) {
		return input.getInput().stream().map(p -> p.getTotalTtc()).reduce((BigDecimal.valueOf(0)), (p1, sum) -> p1 = sum.add(p1));
	}
	




}
