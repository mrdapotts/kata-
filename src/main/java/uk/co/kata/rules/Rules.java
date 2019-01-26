package uk.co.kata.rules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Read in and store rules
 * @author dp42
 *
 */
public class Rules {
	private final static Integer SHORT_LINE = 2;
	private final static Integer LONG_LINE = 4;

	private static Logger LOGGER = Logger.getLogger(Rules.class.getName());
	private List<BaseRule> theRules;

	public Rules() {
		theRules = new ArrayList<>();
	}

	/**
	 * Read the rule file, generate one rule per item
	 * 
	 * @param rulesFile rules files to process
	 * @throws IOException
	 */
	public void readRules(String rulesFile) throws IOException {
		if (rulesFile == null) {
			throw new IllegalArgumentException("Empty rules file supplied");
		}
		LOGGER.log(Level.INFO, "Reading rules file \"{0}\"", rulesFile);


		InputStream in = getClass().getResourceAsStream("/"+rulesFile);

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		Integer lineNo = 0;
		String ruleLine;
		while ((ruleLine = br.readLine()) != null) {
			LOGGER.log(Level.FINE, "Reading line No {0} \"{1}\"", new Object[] { lineNo, ruleLine });
			lineNo++;
			BaseRule theRule = parseRule(lineNo, ruleLine);
			if (theRule == null) {
				// empty line, or comment etc
				continue;
			}
			theRules.add(theRule);
		}
		br.close();
	}

	/**
	 * Parse a line of the rules file 
	 * @param lineNo  line number of file
	 * @param ruleLine text of rule
	 * @return parsed rule
	 */
	private BaseRule parseRule(final Integer lineNo, final String ruleLine) {

		BaseRule theRule = null;
		LOGGER.log(Level.INFO, "parsing line \"{0}\"", ruleLine);
		if (ruleLine == null || ruleLine.isEmpty() || ruleLine.startsWith("#")) {
			return theRule;
		}
		String tokens[] = ruleLine.split("[,]");
		if (tokens == null) {
			LOGGER.log(Level.INFO, "Could not parse line {0} correctly discovered {1} ",
					new Object[] { lineNo, ruleLine });
			return theRule;
		}
		// remove any trailing spaces
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim();

		}
		LOGGER.log(Level.INFO, "Discovered  {0} strings", tokens.length);
		if (tokens.length == SHORT_LINE) {
			if (!BaseRule.isValid(tokens)) {
				LOGGER.log(Level.INFO, "Could not parse line {0} correctly discovered {1} ",
						new Object[] { lineNo, ruleLine });
			} else {
				theRule = mkMinRule(tokens);
			}

		} else if (tokens.length == LONG_LINE) {
			// do some basic checking that the read tokens are the type that we
			// expect
			if (!BaseRule.isValid(tokens)) {
				LOGGER.log(Level.INFO, "Could not parse line {0} correctly discovered {1} ",
						new Object[] { lineNo, ruleLine });

			} else {
				theRule = mkFullRule(tokens);
			}

		} else {
			LOGGER.log(Level.INFO, "Could not parse line correctly discovered {0} when expecting 2 or 4 tokens",
					tokens.length);
		}
		return theRule;
	}
	/**
	 * Populate a minium rule
	 * @param tokens list oftokens to process
	 * @return parsed rules
	 */
	private MinRule mkMinRule(String[] tokens) {

		RuleFactory ruleFactory = RuleFactory.getInstance();

		BigDecimal unitPrice = RuleUtils.String2BigDecimal(tokens[MinRule.R_PRICE]);
		MinRule theRule = ruleFactory.makeMinRule(new ItemType(tokens[BaseRule.R_NAME]), unitPrice);

		return theRule;

	}
	/**
	 * Populate a full rule
	 * @param tokens list of tokens to process
	 * @return parsed rules
	 */
	private FullRule mkFullRule(String[] tokens) {

		RuleFactory ruleFactory = RuleFactory.getInstance();

		BigDecimal unitPrice = RuleUtils.String2BigDecimal(tokens[MinRule.R_PRICE]);
		Integer multiple;

		try {

			multiple = Integer.parseInt(tokens[FullRule.R_MULTIPLE]);

		} catch (NumberFormatException e) {
			return null;

		}

		BigDecimal specialPrice = RuleUtils.String2BigDecimal(tokens[FullRule.R_SPECIAL_PRICE]);
		if (unitPrice == null || specialPrice == null) {
			return null;
		}
		FullRule theRule = ruleFactory.makeFullRule(new ItemType(tokens[BaseRule.R_NAME]), unitPrice, multiple,
				specialPrice);

		return theRule;

	}
	/**
	 * Given a list of price data parse it according to the know rules
	 * @param priceData
	 * @return
	 */
	public BigDecimal processData(List<ItemType> priceData) {
		// Get a list of items types

		BigDecimal price = BigDecimal.ZERO;

		LOGGER.log(Level.FINEST, "Processing {0}", theRules.size());

		for (BaseRule rule : theRules) {
			LOGGER.log(Level.INFO, "Processing rule {0}", rule.getItemName());
			price = price.add(rule.processData(priceData));
		}

		return price;

	}
}
