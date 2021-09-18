package com.shop;

import com.shop.assets.Item;
import com.shop.assets.Order;
import com.shop.billing.BillingApp;
import com.shop.util.ExtractorUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
public class OnlineShop {

  private static BillingApp billingApp;

  public static void main(String[] args) {
    log.debug("Received input " + args[0]);
    String filePath = args[0];
    List<Order> orders = getOrders(filePath);

    billingApp = new BillingApp();
    for (Order order : orders) {
      List<Item> items = fetchItems(order);
      billingApp.run(items);
    }
  }

  private static List<Item> fetchItems(Order order) {
    Iterator<String> itemDescIt = order.getItemDescIterator();
    List<Item> items = new ArrayList<>();
    while (itemDescIt.hasNext()) {
      String itemDesc = itemDescIt.next();
      Item item = ExtractorUtil.extractItem(itemDesc);
      items.add(item);
    }
    return items;
  }

  private static List<Order> getOrders(String filePath) {
    File orderFile = new File(filePath);
    List<Order> orders = new ArrayList<>();
    try (LineIterator iterator = FileUtils.lineIterator(orderFile, "UTF-8")) {
      Order order = new Order();
      String line;
      while (iterator.hasNext()) {
        line = iterator.nextLine();
        if (line.equals("")) {
          orders.add(order);
          order = new Order();
        } else {
          order.addItem(line);
        }
      }
      orders.add(order); // the last order will not have the corresponding empty string
    } catch (IOException failedFile) {
      String errorMsg =
          String.format("Failed to create iterator for file %s. Check path.", orderFile);
    }
    return orders;
  }
}
