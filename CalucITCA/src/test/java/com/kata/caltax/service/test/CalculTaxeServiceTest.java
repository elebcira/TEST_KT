package com.kata.caltax.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kata.caltax.model.Facture;
import com.kata.caltax.model.InputPanier;
import com.kata.caltax.model.Produit;
import com.kata.caltax.model.TypeTaxe;
import com.kata.caltax.service.CalculTaxeService;
import com.kata.caltax.service.Impl.CalculServiceImpl;

public class CalculTaxeServiceTest {

	CalculTaxeService calculTaxeService = new CalculServiceImpl();
	static Facture facture;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// #### Input 1
		//
		// * 2 livres à 12.49€
		// * 1 CD musical à 14.99€
		// * 3 barres de chocolat à 0.85€
		Produit livre = new Produit.Builder().typeTaxe(TypeTaxe.LIVRE).description("Livre").importation(false).ht(12.49).quantite(2).build();
		Produit cd = new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("CD musical").importation(false).ht(14.99).quantite(1).build();
		Produit barresChocolat = new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("barres de chocolat").importation(false).ht(0.85).quantite(3).build();

		InputPanier input1 = new InputPanier(livre, cd, barresChocolat);

		// #### Input 2
		//
		// * 2 boîtes de chocolats importée à 10€
		// * 3 flacons de parfum importé à 47.50€
		Produit boitesChocolatsImportee = new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("boîtes de chocolats").importation(true).ht(10).quantite(2).build();
		Produit flaconsParfumImporte = new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("flacons de parfum").importation(true).ht(47.50).quantite(3).build();

		InputPanier input2 = new InputPanier(boitesChocolatsImportee, flaconsParfumImporte);

		// #### Input 3
		//
		// * 2 flacons de parfum importé à 27.99€
		// * 1 flacon de parfum à 18.99€
		// * 3 boîtes de pilules contre la migraine à 9.75€
		// * 2 boîtes de chocolats importés à 11.25€
		Produit flaconsParfumImporte3 = new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("flacons de parfum").importation(true).ht(27.99).quantite(2).build();
		Produit flaconsParfum = new Produit.Builder().typeTaxe(TypeTaxe.AUTRE).description("flacon de parfum").importation(false).ht(18.99).quantite(1).build();
		Produit boitesPilules = new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("boîtes de pilules contre la migraine").importation(false).ht(9.75).quantite(3).build();
		Produit chocolatsimporte = new Produit.Builder().typeTaxe(TypeTaxe.PREM_NECESSITE).description("boîtes de chocolats").importation(true).ht(11.25).quantite(2).build();

		InputPanier input3 = new InputPanier(flaconsParfumImporte3, flaconsParfum, boitesPilules, chocolatsimporte);

		facture = new Facture(input1, input2, input3);
		System.out.println(facture.toString());
		CalculTaxeService calculTaxeService = new CalculServiceImpl();
		calculTaxeService.calculerFacture(facture);
		System.out.println(facture.toString());
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testInut1() {

		// INPUT 1
		assertEquals(facture.getInputPaniers().get(0).getInput().get(0).getHt().doubleValue(), 12.49, 0);

		assertEquals(facture.getInputPaniers().get(0).getInput().get(0).getHt().doubleValue(), 12.49, 0);

		assertEquals(facture.getInputPaniers().get(0).getInput().get(1).getHt().doubleValue(), 14.99, 0);
		assertEquals(facture.getInputPaniers().get(0).getInput().get(2).getHt().doubleValue(), 0.85, 0);

		assertEquals(facture.getInputPaniers().get(0).getMontantTaxes().doubleValue(), 5.53, 0);
		assertEquals(facture.getInputPaniers().get(0).getTotal().doubleValue(), 48.05, 0);
	}

	@Test
	public void testInut2() {
		// INPUT 2
		assertEquals(facture.getInputPaniers().get(1).getInput().get(0).getHt().doubleValue(), 10, 0);
		assertEquals(facture.getInputPaniers().get(1).getInput().get(1).getHt().doubleValue(), 47.50, 0);

		assertEquals(facture.getInputPaniers().get(1).getMontantTaxes().doubleValue(), 36.65, 0);
		assertEquals(facture.getInputPaniers().get(1).getTotal().doubleValue(), 199.15, 0);

	}

	@Test
	public void testInut3() {
		// INPUT 3
		assertEquals(facture.getInputPaniers().get(2).getInput().get(0).getHt().doubleValue(), 27.99, 0);
		assertEquals(facture.getInputPaniers().get(2).getInput().get(1).getHt().doubleValue(), 18.99, 0);
		assertEquals(facture.getInputPaniers().get(2).getInput().get(2).getHt().doubleValue(), 9.75, 0);
		assertEquals(facture.getInputPaniers().get(2).getInput().get(3).getHt().doubleValue(), 11.25, 0);

		assertEquals(facture.getInputPaniers().get(2).getMontantTaxes().doubleValue(), 18.98, 0);
		assertEquals(facture.getInputPaniers().get(2).getTotal().doubleValue(), 145.7, 0);
	}

}
