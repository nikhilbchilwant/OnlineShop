package com.shop.assets;

import com.shop.util.Constants.SalesTaxCategory;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class Item {

  private static final int SCALE = 2;

  @Getter private String type; // e.g. book, computer etc.

  @Getter
  private BigInteger
      quantity; // https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html

  @Getter @Setter private List<SalesTaxCategory> salesTaxCategories;

  @Getter private BigDecimal basePrice;

  @Getter private BigDecimal salesTax;

  public Item(
      @NonNull String type,
      @NonNull BigInteger quantity,
      @NonNull List<SalesTaxCategory> salesTaxCategory,
      @NonNull BigDecimal basePrice) {
    this.type = type;
    this.quantity = quantity;
    this.salesTaxCategories = salesTaxCategory;
    this.basePrice = basePrice;
    this.salesTax = BigDecimal.ZERO;
  }

  /**
   * Sets sales tax amount after checking for formatting and rounding off rule.
   *
   * @param salesTax sales tax per item
   */
  public void setSalesTax(@NonNull BigDecimal salesTax) {
    boolean isFormatted = salesTax.scale() <= SCALE;
    BigDecimal fraction = salesTax.remainder(BigDecimal.ONE);
    boolean isRounded =
        fraction.remainder(BigDecimal.valueOf(0.05)).compareTo(BigDecimal.valueOf(0.0)) == 0;

    String error = "";

    if (!isFormatted) {
      error = error + "Incorrect number of decimal places in " + salesTax + ". Requires to be 2. ";
    }
    if (!isRounded) {
      error = error + "Incorrectly rounded off sales tax " + salesTax + ".";
    }

    if (!isFormatted || !isRounded) {
      throw new IllegalArgumentException(error);
    } else {
      this.salesTax = salesTax;
    }
  }

  public BigDecimal getNetPrice() {
    return this.basePrice.add(this.salesTax);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return getType().equals(item.getType())
        && getQuantity().equals(item.getQuantity())
        && getSalesTaxCategories().equals(item.getSalesTaxCategories())
        && getBasePrice().equals(item.getBasePrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getType(), getQuantity(), getSalesTaxCategories(), getBasePrice());
  }
}
