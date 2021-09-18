package com.shop.util;

import com.shop.assets.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtractorUtilTest {

  String[] orders;
  Item item1;
  Item item2;
  Item item3;

  @BeforeEach
  void setUp() {
    orders =
        new String[] {
          "2 books at 12.49", "2 imported music CD at 14.99"
        };
    item1 =
        new Item(
            "books",
            BigInteger.valueOf(2),
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                add(Constants.SalesTaxCategory.EXEMPTED);
              }
            },
            BigDecimal.valueOf(12.49));

    item2 =
        new Item(
            "imported music CD",
            BigInteger.valueOf(2),
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                // sequence of categories in the list matters for asserting equality of objects
                add(Constants.SalesTaxCategory.LOCAL);
                add(Constants.SalesTaxCategory.IMPORTED);
              }
            },
            BigDecimal.valueOf(14.99));

  }

  @AfterEach
  void tearDown() {}

  @Test
  public void testItemExtraction() {
    Item genItem1 = ExtractorUtil.extractItem(orders[0]);
    assertEquals(item1, genItem1);

    Item genItem2 = ExtractorUtil.extractItem(orders[1]);
    assertEquals(item2, genItem2);
  }
}
