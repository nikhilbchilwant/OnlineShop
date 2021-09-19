package com.shop.util;

import com.shop.assets.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates Item objects by extracting data from raw order texts.
 */
public class ExtractorUtil {

  public static Item extractItem(String orderLine) {
    String type = extractType(orderLine);
    BigInteger quantity = extractQuantity(orderLine);
    boolean isExempted = checkExemption(orderLine);
    boolean isImported = checkImported(orderLine);
    BigDecimal basePrice = extractBasePrice(orderLine);
    return generateItem(type, quantity, basePrice, isExempted, isImported);
  }

  private static Item generateItem(
      String type,
      BigInteger quantity,
      BigDecimal basePrice,
      boolean isExempted,
      boolean isImported) {
    List<Constants.SalesTaxCategory> taxCategories = new ArrayList<>();

    if (isExempted) taxCategories.add(Constants.SalesTaxCategory.EXEMPTED);
    else taxCategories.add(Constants.SalesTaxCategory.LOCAL);

    if (isImported) taxCategories.add(Constants.SalesTaxCategory.IMPORTED);
    return new Item(type, quantity, taxCategories, basePrice);
  }

  private static BigDecimal extractBasePrice(String orderLine) {
    String[] tokens = orderLine.split(" ");
    return new BigDecimal(
        tokens[
            tokens.length
                - 1]); // assuming that input is properly formatted. So, no scale required.
  }

  private static boolean checkImported(String orderLine) {
    return orderLine.contains(Constants.IMPORTED);
  }

  private static String extractType(String orderLine) {
    String[] tokens = orderLine.split(" ");
    String[] typeTokens = Arrays.copyOfRange(tokens, 1, tokens.length - 2);
    return String.join(" ", typeTokens);
  }

  private static BigInteger extractQuantity(String orderLine) {
    return new BigInteger(orderLine.split(" ")[0]);
  }

  private static boolean checkExemption(String orderLine) {
    for (String category : Constants.EXEMPTED_ITEMS) {
      if (orderLine.contains(category)) {
        return true;
      }
    }
    return false;
  }
}
