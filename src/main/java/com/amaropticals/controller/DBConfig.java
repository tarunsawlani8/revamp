package com.amaropticals.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

@Configuration
public class DBConfig  {

@Bean
public MBeanExporter exporter()
{
final MBeanExporter exporter = new MBeanExporter();
exporter.setAutodetect(true);
exporter.setExcludedBeans("dataSource");
return exporter;
}


}