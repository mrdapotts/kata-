package uk.co.kata.rules;

import java.math.BigDecimal;

/**
 * Gross overkill for this example, but if more complex rules are going to be
 * impelemented, it might have an example here.
 * 
 * @author Dave Potts
 *
 */
public class RuleFactory {

	private static RuleFactory instance;

	private RuleFactory() {

	}
	/**
	 * Single instance of this class
	 * @return
	 */
	public static RuleFactory getInstance() {
		if (instance == null)
			instance = new RuleFactory();
		return instance;
	}

	public FullRule makeFullRule(final ItemType name, final BigDecimal unitPrice, final Integer multiple,
			final BigDecimal specialPrice) {
		FullRule fullRule = new FullRule(name, unitPrice, multiple, specialPrice);
		return fullRule;
	}

	public MinRule makeMinRule(final ItemType name, final BigDecimal unitPrice) {
		MinRule minRule = new MinRule(name, unitPrice);
		return minRule;
	}
}
