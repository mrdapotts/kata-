package uk.co.kata;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;


import org.junit.Test;

import uk.co.kata.driver.Kata;
import uk.co.kata.rules.Rules;
/**
 *       Run some basic tests on the kata program
 *       Assumes that junit isonthe class path
 *       @author Dave Potts
 *
 */
public class BasicTest {

	
	/**
	 * Simple test  show throw an exception if no rules files supplied
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Test (expected = IllegalArgumentException.class) 
	public void nullRuleFile() throws IOException,IllegalArgumentException {

		Rules theRules = new Rules();
		theRules.readRules(null);
	
	}
	
	/**
	 * Simple test  show throw an exception if no rules files supplied
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Test 
	public void readRuleFile() throws IOException,IllegalArgumentException {

		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
	
	}
	
	/**
	 * Simple test  show throw an exception if no prices files supplied
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Test (expected = IllegalArgumentException.class) 
	public void readNullPriceFile() throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		theKata.parseData(theRules, null);
	
	}
	/**
	 *
	 * Should return the cost of four A items ie 180
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void readEmpyCases () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("empty.text").getFile());
	
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(0));
	}
	
	/**
	 *
	 * Should return the cost of four A items ie 180
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void readTwoACases () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("2A.text").getFile());
		
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(100));
	}
	
	/**
	 *
	 * Should return the cost of three A items ie 130
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void readThreeACases () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("3A.text").getFile());
		
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(130));
	}
	
	
	/**
	 *
	 * Should return the cost of four A items ie 180
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void readFourACases () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("4A.text").getFile());
		
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(180));
	}
	
	/**
	 *
	 * Should return the cost of four D items ie 180
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void readFour4Cases () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("4D.text").getFile());
		
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(60));
	}
	
	
	/**
	 *
	 * Should attempt to process a file with an F in  ie an unknown value
	 * return will be zero
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void processUnknownItem () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("unknown.text").getFile());
		
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(0));
	}
	/**
	 *
	 * Process several different values
	 * return will be zero
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * 
	 */
	@Test 
	public void processComboItem () throws IOException,IllegalArgumentException {

		Kata  theKata = new Kata();
		Rules theRules = new Rules();
		theRules.readRules("rules.properties");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("combo.text").getFile());
		
		
		BigDecimal total = theKata.parseData(theRules,file.getAbsolutePath());
		
		assertEquals(total,new BigDecimal(195));
	}
}
