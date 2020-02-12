package com.amaropticals.restcontroller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.common.AOConstants;
import com.amaropticals.common.CommonUtils;
import com.amaropticals.dao.StocksDAO;
import com.amaropticals.model.CommonResponse;
import com.amaropticals.model.TaskModel;

@RequestMapping("/tasks")
@RestController
public class TaskController {

	@Autowired
	private StocksDAO stocksDAO;

	@Autowired
	private InvoiceController invoiceController;

	private final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@RequestMapping(value = "/createTasks", method = RequestMethod.POST)
	public CommonResponse createTasks(@RequestBody TaskModel model) {
		LOGGER.info("Adding Tasking taskId={}, taskStatus={}", model.getTaskId(), model.getTaskStatus());

		String sql = "INSERT INTO opticals_tasks (task_id, task_status, name, delivery_date, update_timestamp, user)"
				+ " VALUES(?,?,?,?,?,?);";
		stocksDAO.addOrUpdateInvoice(sql, model.getTaskId(), model.getTaskStatus(), model.getName(), model.getDeliveryDate(),
				model.getUpdateTime(), model.getUser());
		CommonResponse response = new CommonResponse();
		response.setStatus(AOConstants.SUCCESS_TEXT);
		return response;

	}

	@RequestMapping(value = "/searchTasks", method = RequestMethod.POST)
	public List<TaskModel> searchTasks(@RequestBody TaskModel model) {
		LOGGER.info("Search Tasking taskId={}, taskStatus={}", model.getTaskId(), model.getTaskStatus());

		String sql = "SELECT * from opticals_tasks;";

		if (StringUtils.isNotBlank(model.getTaskId())) {
			sql = "SELECT * from opticals_tasks WHERE task_id LIKE  '" + model.getTaskId() + "%';";
		} else if (StringUtils.isNotBlank(model.getTaskStatus())) {
			sql = "SELECT * from opticals_tasks WHERE task_status LIKE '" + model.getTaskStatus() + "';";
		} else if (StringUtils.isNotBlank(model.getDeliveryDate())) {

			sql = "SELECT * from opticals_tasks WHERE delivery_date > '" + Date.valueOf(model.getDeliveryDate()) + "';";

		} else {

			sql = "SELECT * from opticals_tasks ;";
		}

		return stocksDAO.findTasks(sql);
	}

	@RequestMapping(value = "/updateTasks", method = RequestMethod.POST)
	public CommonResponse updateTasks(@RequestBody TaskModel model) {
		LOGGER.info("Updating Tasking taskId={}, taskStatus={}", model.getTaskId(), model.getTaskStatus());
		TaskModel oldModel = searchTasks(model).get(0);

		model.setTaskId(oldModel.getTaskId());
		model.setDeliveryDate(model.getDeliveryDate().substring(0,10));
		String sql = "UPDATE  opticals_tasks SET  task_status=? , delivery_date=?, update_timestamp=?, user=? WHERE task_id=?;";
		stocksDAO.addOrUpdateInvoice(sql, model.getTaskStatus(), model.getDeliveryDate(),
				Timestamp.valueOf(LocalDateTime.now()), model.getUser(), model.getTaskId());
		if ("READY FOR PICKUP".equalsIgnoreCase(model.getTaskStatus())) {
			CommonUtils.sendMessages(invoiceController.getInvoice(Long.valueOf(model.getTaskId().split("-")[0])),
					model.getTaskStatus());
		}
		CommonResponse response = new CommonResponse();
		response.setStatus(AOConstants.SUCCESS_TEXT);
		return response;
	}
}
