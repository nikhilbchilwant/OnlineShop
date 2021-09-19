package com.shop.assets;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Stores collection of Items. Maintains sales tax and amount totals. */
@Log4j2
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
    log.trace("Pushed item {} to cart. Cart state is {}", item.toString(), this.toString());
  }

  /** Return the item as per the FIFO (First In First Out) policy. */
  public Item pop() {
    if (this.items.isEmpty()) return null;

    Item poppedItem = this.items.remove(0);
    this.totalSalesTax =
        totalSalesTax.subtract(
            poppedItem.getSalesTax().multiply(new BigDecimal(poppedItem.getQuantity())));
    this.totalAmount =
        totalAmount.subtract(
            poppedItem.getNetPrice().multiply(new BigDecimal(poppedItem.getQuantity())));
    log.trace("Popped item {} from cart. Cart state is {}", poppedItem.toString(), this.toString());
    return poppedItem;
  }

  /** Gives iterator over stored items. */
  public Iterator<Item> iterator() {
    return this.items.iterator();
  }

  @Override
  public String toString() {
    return "Cart{"
        + "No. of items="
        + items.size()
        + ", totalSalesTax="
        + totalSalesTax
        + ", totalAmount="
        + totalAmount
        + '}';
  }
}
