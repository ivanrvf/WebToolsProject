package com.spring.project;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.spring.project.model.Item;
import com.spring.project.model.Order;
import com.spring.project.model.product.Product;

public class ExcelView extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
		Map<String,List<Order>> salesOrderList = (Map<String,List<Order>>) model.get("salesOrderMap");
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Sales Order List");
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("OrderID");
		header.createCell(1).setCellValue("CustomerName");
		header.createCell(2).setCellValue("ProductName");
		header.createCell(3).setCellValue("Quantity");
		int rowNum = 1;
		for (Map.Entry<String, List<Order>> entry : salesOrderList.entrySet()) {
			//create the row data
			for(Order salesOrder: entry.getValue()) {
				HSSFRow row = sheet.createRow(rowNum++);
				Iterator iterator = salesOrder.getItems().iterator();
				while(iterator.hasNext()) {
					row.createCell(0).setCellValue(salesOrder.getId());
					row.createCell(1).setCellValue(salesOrder.getUser().getUsername());
					Item product = (Item)iterator.next(); 
					row.createCell(2).setCellValue(product.getProduct().getName());
					row.createCell(3).setCellValue(product.getQty());
				}
			}
		}
	}
}