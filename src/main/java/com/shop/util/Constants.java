package com.shop.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public final class Constants {

  public static final Map<SalesTaxCategory, BigDecimal> TAX_RATES =
      new HashMap<SalesTaxCategory, BigDecimal>() {
        {
          put(SalesTaxCategory.LOCAL, BigDecimal.TEN);
          put(SalesTaxCategory.EXEMPTED, BigDecimal.ZERO);
          put(SalesTaxCategory.IMPORTED, BigDecimal.valueOf(5));
        }
      };
  public static final MathContext MATH_CONTEXT = new MathContext(2, RoundingMode.CEILING);
  public static final String[] EXEMPTED_ITEMS = {
    "book", "chocolate", "pill"
  };
  public static final CharSequence IMPORTED = "imported";

  public enum SalesTaxCategory {
    LOCAL,
    EXEMPTED,
    IMPORTED
  }
}
