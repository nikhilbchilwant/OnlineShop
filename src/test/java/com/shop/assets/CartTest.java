package com.shop.assets;

import com.shop.util.Constants.SalesTaxCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

  Cart cart;
  Item item1;
  Item item2;

  @BeforeEach
  void setUp() {
    cart = new Cart();
    item1 =
        new Item(
            "book",
            BigInteger.TEN,
            new ArrayList<SalesTaxCategory>() {
              {
                add(SalesTaxCategory.LOCAL);
              }
            },
            BigDecimal.valueOf(4));
    item1.setSalesTax(BigDecimal.valueOf(1));
    item2 =
        new Item(
            "perfume",
            BigInteger.TEN,
            new ArrayList<SalesTaxCategory>() {
              {
                add(SalesTaxCategory.LOCAL);
              }
            },
            BigDecimal.valueOf(2));
    item2.setSalesTax(BigDecimal.valueOf(1));
  }

  @AfterEach
  void tearDown() {}

  @Test
  public void testPushingItem() {
    cart.push(item1);
    assertEquals(BigDecimal.valueOf(10), cart.getTotalSalesTax());
    assertEquals(BigDecimal.valueOf(50), cart.getTotalAmount());
    cart.push(item2);
    assertEquals(BigDecimal.valueOf(20), cart.getTotalSalesTax());
    assertEquals(BigDecimal.valueOf(80), cart.getTotalAmount());
  }

  @Test
  public void testPoppingItem() {
    cart.push(item1);
    cart.push(item2);
    // Thing till this are checked by previous test

    cart.pop();
    assertEquals(BigDecimal.valueOf(10), cart.getTotalSalesTax());
    assertEquals(BigDecimal.valueOf(30), cart.getTotalAmount());
    cart.pop();
    assertEquals(BigDecimal.valueOf(0), cart.getTotalSalesTax());
    assertEquals(BigDecimal.valueOf(0), cart.getTotalAmount());
  }

  @Test
  public void testPeeking() {
    cart.push(item1);
    cart.push(item2);

    int itemCounter = 0;
    Iterator<Item> iterator = cart.peek();
    while (iterator.hasNext()) {
      iterator.next();
      itemCounter++;
    }
    assertEquals(2, itemCounter);
  }
}
