package com.amaropticals.daomapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.amaropticals.model.CreateInvoiceRequest;

public class InvoiceModelMapper implements RowMapper<CreateInvoiceRequest> {
	
	@Override
	public CreateInvoiceRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		CreateInvoiceRequest model = new CreateInvoiceRequest();
		model.setInvoiceId(rs.getLong("invoice_id"));
		model.setName(rs.getString("name"));
		model.setContact(rs.getString("contact"));
		model.setEmail(rs.getString("email"));
		model.setDeliveryDate(String.valueOf(rs.getDate("delivery_date")));
		model.setTotalAmount(String.valueOf(rs.getDouble("total_amount")));
		model.setInitialAmount(String.valueOf(rs.getDouble("initial_amount")));
		model.setUpdateDate(String.valueOf(rs.getTimestamp("update_timestamp")));
		model.setJsonFileName(rs.getString("json_file_name"));
		return model;
	}


}
