package com.amaropticals.daomapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.amaropticals.model.CustomerModel;

public class CustomerModelMapper implements RowMapper<CustomerModel> {
	
	@Override
	public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerModel model = new CustomerModel();
		model.setName(rs.getString("name"));
		model.setContact(rs.getString("contact"));
		model.setEmail(rs.getString("email"));
		return model;
	}

}
