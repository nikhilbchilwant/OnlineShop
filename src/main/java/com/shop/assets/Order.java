package com.shop.assets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Stores item descriptions as raw text.
 */
public class Order {
  private List<String> itemsDescription;

  public Order() {
    itemsDescription = new ArrayList<>();
  }

  public Iterator<String> getItemDescIterator() {
    return itemsDescription.iterator();
  }

  public void addItem(String description) {
    itemsDescription.add(description);
  }
}
