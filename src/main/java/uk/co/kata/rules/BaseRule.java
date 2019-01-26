package uk.co.kata.rules;

import java.math.BigDecimal;
import java.util.List;

/**
 * Base rule 
 * @author Dave Potts
 *
 */
public abstract class BaseRule {

	protected final static int R_NAME = 0;

	private ItemType itemName;

	BaseRule(ItemType itemName) {
		this.itemName = itemName;
	}

	public static Boolean isValid(final String[] args) {
		Boolean ret = Boolean.TRUE;

		if (args[R_NAME].isEmpty())
			ret = Boolean.FALSE;
		return ret;
	}

	/**
	 * @return the itemName
	 */
	public final ItemType getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public final void setItemName(ItemType itemName) {
		this.itemName = itemName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseRule [itemName=" + itemName + "]";
	}

	/**
	 * Method to be invoke to process a price 
	 * 
	 * @param priceData priceData to process
	 * @return summary value for this item type
	 * 
	 * Note this method gets the entire list of data just in case there are some
	 * rules that are defined to say you get a discount for buying another item type
	 */
	public abstract BigDecimal processData(List<ItemType> priceData);

}
