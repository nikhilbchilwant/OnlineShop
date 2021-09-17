package com.shop.billing;

import com.shop.assets.Item;
import com.shop.util.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorTest {

  Item item1;
  Item item2;
  Item item3;
  Item item4;
  TaxCalculator taxCalculator;

  @BeforeEach
  void setUp() {
    item1 =
        new Item(
            "book",
            BigInteger.TEN,
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                add(Constants.SalesTaxCategory.IMPORTED);
              }
            },
            BigDecimal.valueOf(11.25));

    item2 =
        new Item(
            "book",
            BigInteger.TEN,
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                add(Constants.SalesTaxCategory.LOCAL);
                add(Constants.SalesTaxCategory.IMPORTED);
              }
            },
            BigDecimal.valueOf(27.99));

    item3 =
        new Item(
            "book",
            BigInteger.TEN,
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                add(Constants.SalesTaxCategory.EXEMPTED);
              }
            },
            BigDecimal.valueOf(18.99));

    item4 =
        new Item(
            "book",
            BigInteger.TEN,
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                add(Constants.SalesTaxCategory.LOCAL);
                add(Constants.SalesTaxCategory.IMPORTED);
              }
            },
            BigDecimal.valueOf(47.5));

    taxCalculator = new TaxCalculator();
  }

  @AfterEach
  void tearDown() {}

  @Test
  public void testTaxCalculation() {
    assertEquals(
        BigDecimal.valueOf(60, 2), // sensitive to decimals
        taxCalculator.calculateSalesTax(item1));
    assertEquals(BigDecimal.valueOf(420, 2), taxCalculator.calculateSalesTax(item2));
    assertEquals(BigDecimal.valueOf(0, 2), taxCalculator.calculateSalesTax(item3));
    assertEquals(BigDecimal.valueOf(715, 2), taxCalculator.calculateSalesTax(item4));
  }
}
