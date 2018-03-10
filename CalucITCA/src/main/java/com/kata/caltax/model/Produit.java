package com.kata.caltax.model;

import java.math.BigDecimal;

public class Produit {
	// required
	private TypeTaxe typeTaxe;
	private String description;
	private double quantite;
	private boolean importation;
	private BigDecimal ht = BigDecimal.valueOf(0);

	// optional
	private BigDecimal ttc = BigDecimal.valueOf(0);
	private BigDecimal totalHt = BigDecimal.valueOf(0);
	private BigDecimal totalTtc = BigDecimal.valueOf(0);

	private Produit(Builder builder) {
		this.description = builder.description;
		this.importation = builder.importation;
		this.ht = builder.ht;
		this.quantite = builder.quantite;
		this.typeTaxe = builder.typeTaxe;
	}

	public boolean isImportation() {
		return importation;
	}

	public double getQualtite() {
		return quantite;
	}

	public BigDecimal getHt() {
		return ht;
	}

	public BigDecimal getTtc() {
		return ttc;
	}

	public void setTtc(BigDecimal ttc) {
		this.ttc = ttc;
	}

	public BigDecimal getTotalHt() {
		return totalHt;
	}

	public void setTotalHt(BigDecimal totalHt) {
		this.totalHt = totalHt;
	}

	public BigDecimal getTotalTtc() {
		return totalTtc;
	}

	public void setTotalTtc(BigDecimal totalTtc) {
		this.totalTtc = totalTtc;
	}

	public TypeTaxe getTypeTaxe() {
		return typeTaxe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("* ").append((int) quantite).append(" ").append(description).append(importation ? quantite > 1 ? " importées" : " importée" : "")
				.append(" à ").append(ht.stripTrailingZeros().toPlainString()).append("€");
		if (totalTtc.compareTo(BigDecimal.valueOf(0)) > 0) {
			builder.append(" : ").append(totalTtc.stripTrailingZeros().toPlainString()).append("€");
		}

		return builder.toString();
	}

	public static class Builder {

		private TypeTaxe typeTaxe;
		private String description;
		private double quantite;
		private boolean importation;
		private BigDecimal ht = BigDecimal.valueOf(0);

		public Builder typeTaxe(TypeTaxe typeTaxe) {
			this.typeTaxe = typeTaxe;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder quantite(double quantite) {
			this.quantite = quantite;
			return this;
		}

		public Builder importation(boolean importation) {
			this.importation = importation;
			return this;
		}

		public Builder ht(double ht) {
			this.ht = BigDecimal.valueOf(ht);
			return this;
		}

		public Produit build() {
			return new Produit(this);
		}

	}
}
