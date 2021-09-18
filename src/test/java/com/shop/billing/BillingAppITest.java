package com.shop.billing;

import com.shop.assets.Item;
import com.shop.util.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BillingAppITest {

  BillingApp billingApp;
  Item item1;
  Item item2;
  Item item3;
  List<Item> items;

  @BeforeEach
  void setUp() {
    billingApp = new BillingApp();
    items = new ArrayList<>();
    item1 =
        new Item(
            "book",
            BigInteger.ONE,
            new ArrayList<Constants.SalesTaxCategory>(),
            BigDecimal.valueOf(12.49));

    item2 =
        new Item(
            "music CD",
            BigInteger.ONE,
            new ArrayList<Constants.SalesTaxCategory>() {
              {
                add(Constants.SalesTaxCategory.LOCAL);
              }
            },
            BigDecimal.valueOf(14.99));
    item3 =
        new Item(
            "chocolate bar",
            BigInteger.ONE,
            new ArrayList<Constants.SalesTaxCategory>(),
            BigDecimal.valueOf(0.85));
    items.add(item1);
    items.add(item2);
    items.add(item3);
  }

  @AfterEach
  void tearDown() {}

  @Test
  public void testBillingApp() {
    assertTrue(billingApp.run(items));
  }
}
