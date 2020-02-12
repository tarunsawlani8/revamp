package com.amaropticals.restcontroller;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.common.CacheMap;
import com.amaropticals.dao.GenericDAO;
import com.amaropticals.model.AOError;
import com.amaropticals.model.UserModel;

@RequestMapping("/users")
@RestController
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GenericDAO genericDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserModel> login(@RequestBody UserModel userModel) {
		LOGGER.info("Logging in={}", userModel.getUsername());

		String sql = "SELECT * from opticals_users WHERE user_name = '" + userModel.getUsername() + "'";
		List<UserModel> userModelList = genericDAO.findUsers(sql);
		if (userModelList == null || userModelList.size() != 1) {
			userModel.setError(new AOError(2, "User mis-match.Please try again."));
		} else {
			if (!userModel.getPassword().equals(userModelList.get(0).getPassword())) {
				userModel.setError(new AOError(1, "Invalid login details.Please try again."));

			} else {
				userModel.setPassword(null);
				userModel.setRole(userModelList.get(0).getRole());
				userModel.setName(userModelList.get(0).getName());
				try

				{
					userModel.setToken(CacheMap.getToken(userModel));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				userModel.setLoginTime(Calendar.getInstance().getTime());
				LOGGER.info("new Token generated={} for user={} time={}", userModel.getToken(), userModel.getUsername(),
						userModel.getLoginTime());

				CacheMap.addEntry(userModel.getToken(), userModel);

			}
		}
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/logoff", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserModel> logoff(HttpServletRequest request) {
		UserModel userModel = (UserModel) CacheMap.readEntry(request.getHeader("token"));
		CacheMap.deleteEntry(userModel.getToken());
		LOGGER.info("Logged off={}", userModel.getUsername());
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);

	}

}
