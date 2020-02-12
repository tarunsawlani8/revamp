package com.amaropticals.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.dao.StocksDAO;
import com.amaropticals.model.CustomerModel;

@RequestMapping("/customers")
@RestController
public class CustomerController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StocksDAO stocksDAO;

	@RequestMapping(value = "/findCustomer", method = RequestMethod.POST)
	public List<CustomerModel> findCustomer(@RequestBody CustomerModel customerModel) {

		List<CustomerModel> customerList = new ArrayList<>();
		if (StringUtils.isNotBlank(customerModel.getContact())) {

			String sql = "SELECT name, contact, email FROM opticals_invoices WHERE contact="
					+ customerModel.getContact();
			customerList = stocksDAO.findCustomer(sql);

			if (!customerList.isEmpty()) {
				customerList.stream()
						.forEach(customer -> LOGGER.info("Existing customer Details for contact=?, name=?, email=?",
								customer.getContact(), customer.getName(), customer.getEmail()));
			} else {
				LOGGER.info("No existing details found.");
			}
		}
		return customerList;

	}
}
