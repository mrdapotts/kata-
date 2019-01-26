package uk.co.kata.rules;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Rule support functions 
 * @author Dave Potts
 *
 */
public class RuleUtils {
	private static Logger LOGGER = Logger.getLogger(RuleUtils.class.getName());

	public static BigDecimal String2BigDecimal(final String price) {

		BigDecimal ret;
		LOGGER.log(Level.FINEST, "Parsing number \"{0}\"", price);
		ret = new BigDecimal(price);
		ret.setScale(2);
		return ret;

	}
}
