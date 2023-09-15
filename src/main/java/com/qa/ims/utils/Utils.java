package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	
	private static final Logger LOGGER = LogManager.getLogger();

	private final Scanner scanner;

	public Utils(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Utils() {
		scanner = new Scanner(System.in);
	}

	public Long getLong() {
		String input = null;
		Long longInput = null;
		do {
			try {
				input = getString();
				longInput = Long.parseLong(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		return scanner.nextLine();
	}

	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}
	public float getFloat() {
		String input = null;
		float floatInput = 0.0f;
		do {
			try {
				input = getString();
				floatInput = Float.parseFloat(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a float");
			}
		} while (floatInput == 0.0f);
		return floatInput;
	}
	public int getInt() {
		String input = null;
		int intInput = 0;
		do {
			try {
				input = getString();
				intInput = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter an integer");
			}
		} while (intInput == 0);
		return intInput;
	}

}
