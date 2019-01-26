package uk.co.kata.driver;
/**
 * Driving class file for Checkout Kata program
 * @author dp42
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.kata.rules.ItemType;
import uk.co.kata.rules.Rules;

public class Kata {
	final static String CONFIG_FILE = "rules.properties";
	final static String LOG_CONFIG_FILE = "logging.properties";

	private static Logger LOGGER;

	static {
		URL url = Kata.class.getClassLoader().getResource(LOG_CONFIG_FILE);
		if (url == null) {
			System.err.println("Can not find logfile on class path \"" + LOG_CONFIG_FILE + "\"");
			System.exit(-127);
		}
		String path = url.getFile();
		if (path == null) {
			System.err.println("Can not read logfile on class path \"" + LOG_CONFIG_FILE + "\"");
			System.exit(-127);

		}
		System.setProperty("java.util.logging.config.file", path);
		
		LOGGER = Logger.getLogger(Kata.class.getName());
		LOGGER.log(Level.SEVERE, "Processing input file {0}", path);
	}

	/**
	 * Entry forprogram
	 * @param args
	 */
	public static void main(String args[]) {

		if (args.length == 0) {
			System.err.println("Usage  data_file");
			System.exit(0);
		} else if (args.length == 1) {

			new Kata(CONFIG_FILE, args[0]);
		} else {

			new Kata(args[0], args[1]);
		}
	}
	/**
	 * Only used by test  cases
	 */
	public Kata(){
		
	}

	/**
	 * Command line interface
	 * @param configFileName  name of file that defines items and their values
	 * @param priceFile   list of data to process
	 */
	public Kata(final String configFileName, final String priceFile) {
		LOGGER.log(Level.INFO, "Processing rule file {0}", configFileName);
		LOGGER.log(Level.INFO, "Processing input file {0}", priceFile);

		Rules theRules = new Rules();

		try {
			theRules.readRules(configFileName);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to read configfile ", e);
			e.printStackTrace();
			return;
		}

		try {
			BigDecimal total = parseData(theRules, priceFile);
			System.out.println("Total " +total);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to process  pricefile ", e);
			
			return;
		}
	
		
	}
	/**
	 * Process a data file
	 * @param theRules list of rules to use
	 * @param priceFile list of data to process
	 * @return total price of data
	 * @throws IOException
	 */
	public BigDecimal parseData(final Rules theRules, final String priceFile) throws IOException {
		
		if(priceFile== null ){
			throw new IllegalArgumentException("null priceFile argument");
		}
	
		List<ItemType> priceData = new ArrayList<>();
		BufferedReader br=null;
		// default value, return if there is no data ro process
		BigDecimal ret = BigDecimal.ZERO;
		try {
			
			br = new BufferedReader(new FileReader(priceFile));
		} catch (Exception e ){
			e.printStackTrace();
		
		}
		Integer lineNo = 0;
		String dataLine;
		while ((dataLine = br.readLine()) != null) {
			LOGGER.log(Level.FINE, "Reading line No {0} \"{1}\"", new Object[] { lineNo, dataLine });
			lineNo++;
			// discovered a null or empty line, ignore
			if (dataLine.isEmpty() || dataLine.length() != 1) {
				LOGGER.log(Level.SEVERE, "Empty or syntax error at line {0}", lineNo);
				continue;
			}
			priceData.add(new ItemType(dataLine));

		}
		br.close();
		ret = theRules.processData(priceData);

		return ret;
	}

}
