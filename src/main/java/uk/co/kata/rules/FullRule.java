package uk.co.kata.rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Dave Potts
 * 
 * Process a full rule
 *
 */
public class FullRule extends MinRule {

	private static Logger LOGGER = Logger.getLogger(FullRule.class.getName());

	protected final static int R_MULTIPLE = 2;
	protected final static int R_SPECIAL_PRICE = 3;

	private Integer multiple;
	private BigDecimal specialPrice;

	FullRule(final ItemType itemName, final BigDecimal unitPrice, final Integer multiple,
			final BigDecimal specialPrice) {
		super(itemName, unitPrice);
		setMultiple(multiple);
		setSpecialPrice(specialPrice);
	}
	/**
	 * Basic argument checking.  
	 * Could be improved by checking if the arguments a decimal values
	 * @param args
	 * @return
	 */
	public static Boolean checkArgs(final String args[]) {
		Boolean ret = Boolean.TRUE;
		if (!BaseRule.isValid(args) || args[R_MULTIPLE].isEmpty() || args[R_SPECIAL_PRICE].isEmpty()) {
			ret = Boolean.FALSE;
		}

		return ret;
	}

	/**
	 * Process a full rule as per spec
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
		int noSpecial = noItems / getMultiple();
		int noRump = noItems % getMultiple();

		ret = getUnitPrice().multiply(new BigDecimal(noRump));

		ret = ret.add(this.getSpecialPrice().multiply(new BigDecimal(noSpecial)));
		LOGGER.log(Level.WARNING,
				"Item {0} Items {1} Unit price {2} Special price {3} multiple {4} no Specials {5}  left over {6} return {7}",
				new Object[] { getItemName(), noItems, this.getUnitPrice(), this.getSpecialPrice(), this.getMultiple(),
						noSpecial, noRump, ret

				});

		return ret;

	}

	/**
	 * @return the multiple
	 */
	public final Integer getMultiple() {
		return multiple;
	}

	/**
	 * @param multiple
	 *            the multiple to set
	 */
	public final void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	/**
	 * @return the specialPrice
	 */
	public final BigDecimal getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * @param specialPrice
	 *            the specialPrice to set
	 */
	public final void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

}
