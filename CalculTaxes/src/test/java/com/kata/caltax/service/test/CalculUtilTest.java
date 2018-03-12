package com.kata.caltax.service.test;

import static com.kata.caltax.util.CalculTaxeUtil.getByPourcent;
import static com.kata.caltax.util.CalculTaxeUtil.getTTC;
import static com.kata.caltax.util.CalculTaxeUtil.getTaxe;
import static com.kata.caltax.util.CalculTaxeUtil.getTotalHT;
import static com.kata.caltax.util.CalculTaxeUtil.getTotalTTC;
import static com.kata.caltax.util.CalculTaxeUtil.round;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class CalculUtilTest {

	@Test
	public void testGetByPourcent() {
		BigDecimal result = getByPourcent(BigDecimal.valueOf(100), BigDecimal.valueOf(5));
		assertEquals(result.doubleValue(), 5, 0);
	}

	@Test
	public void testGetTTC() {
		BigDecimal result = getTTC(BigDecimal.valueOf(10), BigDecimal.valueOf(100), 2);
		assertEquals(result.doubleValue(), 110, 0);

	}

	@Test
	public void testGetTotalTTC() {
		BigDecimal result = getTotalTTC(BigDecimal.valueOf(10), 1);
		assertEquals(result.doubleValue(), 10, 0);

	}

	@Test
	public void testGetTotalHT() {
		BigDecimal result = getTotalHT(BigDecimal.valueOf(10), 3);
		assertEquals(result.doubleValue(), 30, 0);

	}

	@Test
	public void testGetTaxe() {
		BigDecimal result = getTaxe(BigDecimal.valueOf(10), false);
		getTaxe(BigDecimal.valueOf(10), true);
		assertEquals(result.doubleValue(), 10, 0);
	}

	@Test
	public void testRound() {
		BigDecimal param = BigDecimal.valueOf(0.99);
		BigDecimal result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 1.00, 0);

		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 1.00, 0);

		param = BigDecimal.valueOf(1.00);
		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 1.00, 0);

		param = BigDecimal.valueOf(1.01);
		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 1.05, 0);

		param = BigDecimal.valueOf(1.02);
		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 1.05, 0);

		param = BigDecimal.valueOf(1.84);
		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 1.85, 0);

		param = BigDecimal.valueOf(2.55);
		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 2.55, 0);

		param = BigDecimal.valueOf(2.550);
		result = round(param, BigDecimal.valueOf(0.05), RoundingMode.UP);
		assertEquals(result.doubleValue(), 2.55, 0);

		param = BigDecimal.valueOf(2);
		result = round(param, BigDecimal.valueOf(0), RoundingMode.UP);
		assertEquals(result.doubleValue(), 2, 0);
	}

}
