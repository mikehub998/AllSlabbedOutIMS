package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Domain {

	CUSTOMER("Shows information about customers"), PRODUCT("View individual Products"), ORDER("View orders and purchases of items"),
	ORDERLINE("Selection to relate to the orderline of an order"),
	STOP("Exit the application");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private Domain(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	public static Domain getDomain(Utils utils) {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}

}
