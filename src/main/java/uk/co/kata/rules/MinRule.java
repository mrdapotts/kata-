package uk.co.kata.rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinRule extends BaseRule {
	private static Logger LOGGER = Logger.getLogger(MinRule.class.getName());

	private BigDecimal unitPrice;

	protected final static int R_PRICE = 1;

	public MinRule(final ItemType itemName, final BigDecimal unitPrice) {
		super(itemName);
		setUnitPrice(unitPrice);
	}
	/**
	 * Basic parameter checking, could do with checking to see if price is a floating point number
	 * @param args
	 * @return
	 */
	public static Boolean checkArgs(final String args[]) {
		Boolean ret = Boolean.TRUE;
		if (!BaseRule.isValid(args) || args[R_PRICE].isEmpty()) {
			ret = Boolean.FALSE;
		}

		return ret;
	}
	/**
	 * Individual price processing set comments in BaseRule
	 */
	@Override
	public BigDecimal processData(List<ItemType> priceData) {
		int noItems = 0;

		BigDecimal ret = BigDecimal.ZERO;

		for (ItemType item : priceData) {
			if (item.equals(getItemName())) {
				noItems++;
			}
		}
		ret = getUnitPrice().multiply(new BigDecimal(noItems));
		LOGGER.log(Level.WARNING, "Item {0} noItems {1} Unit price {2}  return {3}",
				new Object[] { getItemName(), noItems, this.getUnitPrice(), ret

				});

		return ret;
	}

	/**
	 * @return the unitPrice
	 */
	public final BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public final void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MinRule [unitPrice=" + unitPrice + "]";
	}

}
