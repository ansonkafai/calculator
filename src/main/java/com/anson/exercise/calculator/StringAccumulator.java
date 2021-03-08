package com.anson.exercise.calculator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;

/**
 * @author Anson
 *
 */
public class StringAccumulator {
	
	public static final String DEFAULT_DELIMITER_1 = ",";
	public static final String DEFAULT_DELIMITER_2 = "\n";
	public static final String DELIMITERS_SEPARATOR = "|";
	public static final String REQUIRED_DELIMITER_START_INDICATOR = "//";
	public static final String REQUIRED_DELIMITER_END_INDICATOR = "\n";
	public static final int MAX_INT = 1000;

	/**
	 * String accumulator main entry point for caller.
	 * 
	 * @param numbers
	 * @return
	 * @throws Exception
	 */
	public static int add(String numbers) throws Exception {
		// define variables
		String[] delimiters = null;
		String[] delimitedNumbersInString = null;
		String numbersWithoutDelimitersPrefix = null;
		String regex = null;
		int[] delimitedNumbersInInt = null;
		int tmpInt = 0;
		int result = 0;

		// convert delimiters into regex
		delimiters = StringAccumulator.getDelimiters(numbers);
		regex = StringAccumulator.getDelimitersRegex(delimiters);

		// convert numbers string into String[]
		numbersWithoutDelimitersPrefix = StringAccumulator.getNumbersString(numbers);
		delimitedNumbersInString = numbersWithoutDelimitersPrefix.split(regex);

		// convert numbers String[] into int[]
		delimitedNumbersInInt = new int[delimitedNumbersInString.length];
		for (int i = 0; i < delimitedNumbersInString.length; i++) {
			if (StringUtils.isBlank(delimitedNumbersInString[i])) {
				// set to 0 if number is empty string
				delimitedNumbersInInt[i] = 0;
			} else {
				tmpInt = Integer.parseInt(delimitedNumbersInString[i]);
				if (tmpInt > StringAccumulator.MAX_INT) {
					// ignore the int (i.e. set to 0) if bigger than the predefined max
					delimitedNumbersInInt[i] = 0;	
				} else {
					delimitedNumbersInInt[i] = tmpInt;
				}
			}
		}
		
		// check if any negative numbers found then throw exception
		StringAccumulator.checkNegativeNumbers(delimitedNumbersInInt);
		
		// finally sum all the in-scoped int
		for (int num : delimitedNumbersInInt) {
			result += num;
		}
		
		return result;
	}

	/**
	 * Check if any negative numbers found, throw exception.
	 * 
	 * @param numbers
	 * @throws Exception
	 */
	public static void checkNegativeNumbers(int[] numbers) throws Exception {
		// define variables
		List<String> negativeNumbers = new ArrayList<String>();
		String errMsg = null;
		
		// consolidate all negative numbers
		for (int num : numbers) {
			if (num < 0) {
				negativeNumbers.add(String.valueOf(num));
			}
		}
		
		// if any negative numbers found, prepare error message and throw exception
		if (negativeNumbers.size() > 0) {
			Logger.debug(MessageFormat.format("Found [{0}] negative numbers.", negativeNumbers.size()));
			errMsg = negativeNumbers.stream().collect(Collectors.joining(", ", "negatives not allowed [", "]."));
			throw new Exception(errMsg);
		}
	}
	
	/**
	 * Convert delimiters array to regex string for further process.
	 * 
	 * @param delimiters
	 * @return
	 */
	public static String getDelimitersRegex(String[] delimiters) {
		// define variables
		StringBuilder sb = new StringBuilder();
		String regex = null;
		
		sb.append("[");
		for (String s : delimiters) {
			sb.append(Pattern.quote(s));
		}
		sb.append("]");
		
		regex = sb.toString();
		Logger.debug(regex);
		
		return regex;
	}
	
	/**
	 * Get the actual numbers string, i.e. removing the delimiter(s) prefix.
	 * 
	 * @param numbers
	 * @return
	 */
	public static String getNumbersString(String numbers) {
		// define variables
		String numbersString;
		
		if (StringUtils.startsWith(numbers, StringAccumulator.REQUIRED_DELIMITER_START_INDICATOR)) {
			// get the numbers string starting from the first occurrence of "\n"
			numbersString = StringUtils.substring(numbers, StringUtils.indexOf(numbers, StringAccumulator.REQUIRED_DELIMITER_END_INDICATOR)+1);
		} else {
			numbersString = numbers;
		}
		
		Logger.debug(numbersString);
		
		return numbersString;
	}
	
	/**
	 * Get required delimiter(s).
	 * 
	 * @param numbers
	 * @return
	 */
	public static String[] getDelimiters(String numbers) {
		// define variables
		String[] delimiters = null;
		String delimitersString = null;

		// analyze required delimiter(s)
		if (StringUtils.startsWith(numbers, StringAccumulator.REQUIRED_DELIMITER_START_INDICATOR)) {
			delimitersString = StringUtils.substring(numbers, 2, StringUtils.indexOf(numbers, StringAccumulator.REQUIRED_DELIMITER_END_INDICATOR));
			delimiters = StringUtils.split(delimitersString, StringAccumulator.DELIMITERS_SEPARATOR);
		}
		
		// if no required delimiter(s), set to default delimiters
		if (delimiters == null || delimiters.length <= 0) {
			delimiters = new String[] {StringAccumulator.DEFAULT_DELIMITER_1, StringAccumulator.DEFAULT_DELIMITER_2};
		}
		
		for (String delimiter : delimiters) {
			Logger.debug(delimiter);
		}
		
		return delimiters;
	}
}
