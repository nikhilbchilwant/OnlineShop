package com.shop.assets;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Stores collection of Items. Maintains sales tax and amount totals. */
public class Cart {

  private List<Item> items;
  @Getter private BigDecimal totalSalesTax; // sum of sales taxes on items
  @Getter private BigDecimal totalAmount; // final payable amount

  public Cart() {
    this.items = new ArrayList<>();
    this.totalSalesTax = BigDecimal.ZERO;
    this.totalAmount = BigDecimal.ZERO;
  }

  public void push(Item item) {
    this.items.add(item);
    BigDecimal salesTax = item.getSalesTax().multiply(new BigDecimal(item.getQuantity()));
    this.totalSalesTax = this.totalSalesTax.add(salesTax);
    BigDecimal totalAmount = item.getNetPrice().multiply(new BigDecimal(item.getQuantity()));
    this.totalAmount = this.totalAmount.add(totalAmount);
  }

  /** Return the item as per the FIFO (First In First Out) policy. */
  public Item pop() {
    Item poppedItem = this.items.remove(0);
    this.totalSalesTax =
        totalSalesTax.subtract(
            poppedItem.getSalesTax().multiply(new BigDecimal(poppedItem.getQuantity())));
    this.totalAmount =
        totalAmount.subtract(
            poppedItem.getNetPrice().multiply(new BigDecimal(poppedItem.getQuantity())));
    return poppedItem;
  }

  /** Gives iterator over stored items. */
  public Iterator<Item> peek() {
    return this.items.iterator();
  }
}
