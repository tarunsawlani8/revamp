package com.amaropticals.filehandling;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONFileHandler {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(JSONFileHandler.class);

	public static Object readJsonFile(String typePath, String monthPath,
			String fileName, Class clazzz) {

		Object jsonFile = null;

		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(typePath);
		if (StringUtils.isNotBlank(monthPath))
			pathBuilder.append(File.separator);
		pathBuilder.append(monthPath);
		pathBuilder.append(File.separator);
		pathBuilder.append(fileName);
		LOGGER.info("Reading file from path={}", pathBuilder.toString());
		File f = new File(pathBuilder.toString());
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonFile = mapper.readValue(f, clazzz);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return jsonFile;
	}

	public static void writeJsonFile(String typePath, String monthPath,
			String fileName, Object obj) {

		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(typePath);
		if (StringUtils.isNotBlank(monthPath))
			pathBuilder.append(File.separator);
		pathBuilder.append(monthPath);
		pathBuilder.append(File.separator);
		
		File dir = new File(pathBuilder.toString());
		
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		pathBuilder.append(fileName);
		LOGGER.info("Writing file to path={}", pathBuilder.toString());

		File f = new File(pathBuilder.toString());

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(f, obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
