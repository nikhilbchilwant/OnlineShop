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
    log.debug("Received input file path " + args[0]);
    String filePath = args[0];
    List<Order> orders = getOrders(filePath);

    billingApp = new BillingApp();
    int orderNo = 1;
    for (Order order : orders) {
      List<Item> items = fetchItems(order);
      log.info("Order No. {} has {} items.",orderNo, items.size());
      log.debug("Running the Billing app");
      billingApp.run(items);
      orderNo++;
    }
  }

  /**
   * Creates item objects from the raw text of orders
   */
  private static List<Item> fetchItems(Order order) {
    log.trace("Creating items from order.");
    Iterator<String> itemDescIt = order.getItemDescIterator();
    List<Item> items = new ArrayList<>();
    while (itemDescIt.hasNext()) {
      String itemDesc = itemDescIt.next();
      Item item = ExtractorUtil.extractItem(itemDesc);
      items.add(item);
    }
    log.trace("Created {} items.", items.size());
    return items;
  }

  /**
   * Reads input text file and creates orders containing raw text item description.
   */
  private static List<Order> getOrders(String filePath) {
    File orderFile = new File(filePath);
    List<Order> orders = new ArrayList<>();
    try (LineIterator iterator = FileUtils.lineIterator(orderFile, "UTF-8")) {
      log.info("Reading orders from the file {}", orderFile.getAbsolutePath());
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
      log.error(errorMsg);
    }
    log.info("Got {} orders", orders.size());
    return orders;
  }
}
