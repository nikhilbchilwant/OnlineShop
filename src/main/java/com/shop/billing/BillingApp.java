package com.shop.billing;

import com.shop.assets.Cart;
import com.shop.assets.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BillingApp {

  TaxCalculator calculator;

  public BillingApp(){
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
    Iterator<Item> itemIt = cart.iterator();
    while (itemIt.hasNext()){
      Item item = itemIt.next();
      String billLine = item.getQuantity().toString() + " " + item.getType() + ": " + item.getNetPrice().toString();
      System.out.println(billLine);
    }
    System.out.println("Sales Taxes: " + cart.getTotalSalesTax().toString());
    System.out.println("Total: " + cart.getTotalAmount().toString());
  }

  private List<Item> calculateTaxes(List<Item> items) {
    List<Item> billedItems = new ArrayList<>();
    for (Item item : items) {
      item.setSalesTax(calculator.calculateSalesTax(item));
      billedItems.add(item);
    }
    return billedItems;
  }

//  private void printBill(Cart cart) {
//  }

}
