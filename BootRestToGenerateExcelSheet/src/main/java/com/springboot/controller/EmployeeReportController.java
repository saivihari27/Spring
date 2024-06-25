package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.service.EmployeeReportService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class EmployeeReportController {

	@Autowired
	private EmployeeReportService reportService;
	
	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {
		/*Whatever the report is generating at response we have to be stored/downloaded */
		
		response.setContentType("application/octet-stream"); // How the response is send we have to be defined.
		// setContentType is responsible to download excel file.
		
		// And we have to send response headers in the key-value pair..
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=employee.xls";
		
		response.setHeader(headerKey, headerValue);
		
		reportService.generateExcelSheet(response);
		
	}

}
