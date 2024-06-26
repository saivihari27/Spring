package com.springboot.service;


import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Employee;
import com.springboot.repo.EmployeeRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeReportService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	

	public void generateExcelSheet(HttpServletResponse response) throws Exception {
		List<Employee> listEmp = employeeRepo.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Employee_Record");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Salary");
		
		int dataRowIndex = 1;
		for(Employee emp : listEmp) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(emp.getEmpId());
			dataRow.createCell(1).setCellValue(emp.getEmpName());
			dataRow.createCell(2).setCellValue(emp.getEmpSalary());
			dataRowIndex ++;
		}
		
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops); // to write the data in a file.
		workbook.close();
		ops.close();	
	}

}
