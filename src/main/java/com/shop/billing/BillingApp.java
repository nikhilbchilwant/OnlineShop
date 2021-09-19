package com.shop.billing;

import com.shop.assets.Cart;
import com.shop.assets.Item;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Calculates sales taxes and prints bill */
@Log4j2
public class BillingApp {

  TaxCalculator calculator;

  public BillingApp() {
    this.calculator = new TaxCalculator();
  }

  public boolean run(List<Item> items) {
    items = calculateTaxes(items);

    Cart cart = new Cart();
    for (Item item : items) {
      cart.push(item);
    }

    printBill(cart);
    return true;
  }

  private void printBill(Cart cart) {
    log.debug("Printing the bill.");
    Iterator<Item> itemIt = cart.iterator();
    while (itemIt.hasNext()) {
      Item item = itemIt.next();
      String billLine =
          item.getQuantity().toString()
              + " "
              + item.getType()
              + ": "
              + item.getNetPrice().toString();
      System.out.println(billLine);
    }
    System.out.println("Sales Taxes: " + cart.getTotalSalesTax().toString());
    System.out.println("Total: " + cart.getTotalAmount().toString());
    System.out.println();
  }

  private List<Item> calculateTaxes(List<Item> items) {
    log.trace("Calculating taxes.");
    List<Item> billedItems = new ArrayList<>();
    for (Item item : items) {
      item.setSalesTax(calculator.calculateSalesTax(item));
      billedItems.add(item);
    }
    log.trace("Calculated taxes.");
    return billedItems;
  }
}
