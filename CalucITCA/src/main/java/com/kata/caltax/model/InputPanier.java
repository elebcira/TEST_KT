package com.kata.caltax.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputPanier {

	private BigDecimal montantTaxes = BigDecimal.valueOf(0);
	private BigDecimal total = BigDecimal.valueOf(0);

	public InputPanier(Produit... produits) {
		this.input.addAll(Arrays.asList(produits));
	}

	private List<Produit> input = new ArrayList<>();

	public List<Produit> getInput() {
		return input;
	}

	public BigDecimal getMontantTaxes() {
		return montantTaxes;
	}

	public void setMontantTaxes(BigDecimal montantTaxes) {
		this.montantTaxes = montantTaxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		input.stream().forEach(produit -> builder.append(produit.toString()).append("\n"));
		builder.append("\n");

		if (total.compareTo(BigDecimal.valueOf(0)) > 0) {
			
			builder.append("Montant des taxes : ").append(montantTaxes.stripTrailingZeros()).append("€").append("\n");

			builder.append("Total : ").append(total.stripTrailingZeros()).append("€").append("\n\n");
		}
		return builder.toString();

	}

}
