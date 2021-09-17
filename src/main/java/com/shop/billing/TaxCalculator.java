package com.shop.billing;

import com.shop.assets.Item;
import com.shop.util.Constants;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {

  public static final BigDecimal ROUND_FACTOR = BigDecimal.valueOf(0.05);
  public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
  public static final BigDecimal ZERO = BigDecimal.valueOf(0, 2); // for remainder
  public static final int SCALE = 2;

  public BigDecimal calculateSalesTax(@NotNull Item item) {
    BigDecimal taxPerItem = BigDecimal.ZERO;
    for (Constants.SalesTaxCategory category : item.getSalesTaxCategories()) {
      // base price * tax rate / 100
      BigDecimal exactTax =
          (Constants.TAX_RATES.get(category).multiply(item.getBasePrice()))
              .divide(HUNDRED, SCALE, RoundingMode.CEILING);
      BigDecimal roundedTax = roundOff(exactTax);
      taxPerItem = taxPerItem.add(roundedTax);
    }

    return taxPerItem.setScale(SCALE, RoundingMode.CEILING);
  }

  /** Rounds off to the nearest multiple of 0.05 */
  private @NotNull BigDecimal roundOff(@NotNull BigDecimal amount) {
    BigDecimal remainder = amount.remainder(ROUND_FACTOR, Constants.MATH_CONTEXT);
    BigDecimal extra = BigDecimal.ZERO;
    if (!remainder.equals(ZERO)) {
      extra = ROUND_FACTOR.subtract(remainder);
    }
    return amount.add(extra);
  }
}
