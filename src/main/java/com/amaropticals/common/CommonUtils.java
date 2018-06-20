package com.amaropticals.common;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
	private static long invoiceId;

	public static long getNextInvoiceId() {
		long nextInvoiceId;

		Calendar cal = Calendar.getInstance();

		StringBuilder date = new StringBuilder();
		date.append(cal.get(1));
		if (cal.get(2)+1 < 10) {
			date.append(0);
		}
		date.append(cal.get(2)+1);
		if (cal.get(5) < 10) {
			date.append(0);
		}
		date.append(cal.get(5));

		if (Long.parseLong(date.toString() + "000") > invoiceId) {
			nextInvoiceId = Long.parseLong(date.toString() + "001");
		} else {

			nextInvoiceId = invoiceId + 1;
		}
		
		invoiceId = nextInvoiceId;

		LOGGER.info("Next Invoice Id={}", nextInvoiceId);
		return nextInvoiceId;
	}

	public static long getInvoiceId() {
		return invoiceId;
	}

	public static synchronized void setInvoiceId(long invoiceId) {
		CommonUtils.invoiceId = invoiceId;
	}
}
