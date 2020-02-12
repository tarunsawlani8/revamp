package com.amaropticals.daomapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.amaropticals.model.UserModel;

public class UserModelMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserModel model = new UserModel();
		model.setUserId(rs.getLong("user_id"));
		model.setUsername(rs.getString("user_name"));
		model.setName(rs.getString("name"));
		model.setPassword(rs.getString("password"));
		model.setRole(rs.getString("role"));
		return model;
	}

}