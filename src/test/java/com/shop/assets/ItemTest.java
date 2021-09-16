package com.shop.assets;

import com.shop.util.Constants.salesTaxCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestItem {

  Item item;

  @BeforeEach
  void setUp() {
    item = new Item("book", BigInteger.ONE, salesTaxCategory.LOCAL, BigDecimal.TEN);
  }

  @AfterEach
  void tearDown() {}

  @Test
  public void testSalesTaxFormatting() throws IllegalArgumentException {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          item.setSalesTax(BigDecimal.valueOf(10.235));
        });
  }

  @Test
  public void testSalesTaxRounding() throws IllegalArgumentException {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          item.setSalesTax(BigDecimal.valueOf(10.23));
        });
  }

  /** If the input is correct, sales tax should be set correctly. */
  @Test
  public void testSalesTax() {
    item.setSalesTax(BigDecimal.valueOf(10.2));
    assertEquals(BigDecimal.valueOf(10.2), item.getSalesTax());
  }

  // Skipping tests for constructors, fields as those are not meaningful tests
}
