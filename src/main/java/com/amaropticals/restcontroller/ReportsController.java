package com.amaropticals.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.common.CommonUtils;
import com.amaropticals.common.MailUtils;
import com.amaropticals.dao.GenericDAO;
import com.amaropticals.model.AOError;
import com.amaropticals.model.InvoiceListResponse;
import com.amaropticals.model.ReportModel;

@RequestMapping("/reports")
@RestController
public class ReportsController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GenericDAO stocksDAO;

	@Value("${amaropticals.reports.email}")
	private String emailAddresses;

	@RequestMapping(value = "/sendReport", method = RequestMethod.POST)
	public ResponseEntity<ReportModel> sendReport(HttpServletRequest request, @RequestBody ReportModel reportModel) {

		if (!CommonUtils.checkAuthentication(request)) {
			reportModel.setError(new AOError(2, "Un-Authorized access.Please login again"));
			return new ResponseEntity<ReportModel>(reportModel, HttpStatus.OK);

		}

		String monthYear = reportModel.getYear() + reportModel.getMonth();
		String startInvoice = monthYear + "0100";
		String endInvoice = monthYear + "3200";

		String sql = "SELECT SUM(total_amount) as total_amount, COUNT(*) as total_customer FROM opticals_invoices WHERE invoice_id > "
				+ Long.valueOf(startInvoice) + " AND invoice_id < " + Long.valueOf(endInvoice) + ";";

		SqlRowSet sqlRowSet = stocksDAO.genericQuery(sql);
		LOGGER.info("Row exists:" + sqlRowSet.first());
		double totalAmount = sqlRowSet.getDouble("total_amount");

		long totalCustomer = sqlRowSet.getInt("total_customer");
		LOGGER.info(" customers={} ", totalCustomer);

		double salesPerCustomer = 0;
		if (totalCustomer > 0) {
			salesPerCustomer = totalAmount / totalCustomer;
		}
		MailUtils.sendMail(emailAddresses, "", reportModel, String.valueOf(totalAmount), String.valueOf(totalCustomer),
				String.valueOf(salesPerCustomer));

		reportModel.setResponseMessage("Mail sent successfully to the concerned user.");
		return new ResponseEntity<ReportModel>(reportModel, HttpStatus.OK);
	}

}
