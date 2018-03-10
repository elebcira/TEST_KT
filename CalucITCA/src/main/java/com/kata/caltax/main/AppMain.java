package com.kata.caltax.main;

import com.kata.caltax.model.Facture;
import com.kata.caltax.model.InputPanier;
import com.kata.caltax.model.Produit;
import com.kata.caltax.model.TypeTaxe;
import com.kata.caltax.service.CalculTaxeService;
import com.kata.caltax.service.Impl.CalculServiceImpl;

public class AppMain {

	public static void main(String[] args) {

		// #### Input 1
		//
		// * 2 livres à 12.49€
		// * 1 CD musical à 14.99€
		// * 3 barres de chocolat à 0.85€
		Produit livre =  new Produit.Builder().typeTaxe(TypeTaxe.LIVRE).description("Livre").importation(false).ht(12.49).quantite(2).build();
		Produit cd =  new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("CD musical").importation(false).ht(14.99).quantite(1).build();
		Produit barresChocolat =  new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("barres de chocolat").importation(false).ht(0.85).quantite(3).build();
		
		InputPanier input1 = new InputPanier(livre, cd, barresChocolat);
		
		
		
		// #### Input 2
		//
		// * 2 boîtes de chocolats importée à 10€
		// * 3 flacons de parfum importé à 47.50€
		Produit boitesChocolatsImportee =  new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("boîtes de chocolats").importation(true).ht(10).quantite(2).build();
		Produit flaconsParfumImporte =  new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("flacons de parfum").importation(true).ht(47.50).quantite(3).build();
		
		InputPanier input2 = new InputPanier(boitesChocolatsImportee, flaconsParfumImporte);

		
		
		// #### Input 3
		//
		// * 2 flacons de parfum importé à 27.99€
		// * 1 flacon de parfum à 18.99€
		// * 3 boîtes de pilules contre la migraine à 9.75€
		// * 2 boîtes de chocolats importés à 11.25€
		Produit flaconsParfumImporte3 =  new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("flacons de parfum").importation(true).ht(27.99).quantite(2).build();
		Produit flaconsParfum =  new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("flacon de parfum").importation(false).ht(18.99).quantite(1).build();
		Produit boitesPilules =  new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("boîtes de pilules contre la migraine").importation(false).ht(9.75).quantite(3).build();
		Produit chocolatsimporte =  new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("boîtes de chocolats").importation(true).ht(11.25).quantite(2).build();
		
		InputPanier input3 = new InputPanier(flaconsParfumImporte3, flaconsParfum, boitesPilules, chocolatsimporte);

		Facture facture = new Facture(input1, input2, input3);
		System.out.println(facture.toString());
		
		CalculTaxeService calculTaxeService = new CalculServiceImpl();
		calculTaxeService.calculerFacture(facture);

		System.out.println(facture.toString());

	}

}
