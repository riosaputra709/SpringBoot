package com.whiteopen.training.controller.download;

import com.whiteopen.training.module.settingGroup.entity.SettingGroup;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class SettingGroupDownload {
	
	public Workbook settingGroupDownload(Workbook wb, List<SettingGroup> settingGroup) {

		Sheet sheet = wb.getSheet("Setting Group");
		
		sheet.setDisplayGridlines(false);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 32 * 256);
		sheet.setColumnWidth(3, 64 * 256);

		Integer rowNumber = 0;
		Cell cell = null;
		Row row = null;
		
		// HEADER 
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.ORANGE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setBorderBottom(BorderStyle.MEDIUM);
		headerStyle.setBorderTop(BorderStyle.MEDIUM);
		headerStyle.setBorderLeft(BorderStyle.MEDIUM);
		headerStyle.setBorderRight(BorderStyle.MEDIUM);
		headerStyle.setWrapText(true);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font fontHeader = wb.createFont();
		fontHeader.setBold(true);
		fontHeader.setFontHeightInPoints((short) 14);
		fontHeader.setFontName("Calibri");
		headerStyle.setFont(fontHeader);
		
		// SET aligment Left
		CellStyle alignLeft = wb.createCellStyle();
		alignLeft.setAlignment(HorizontalAlignment.LEFT);
		alignLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		alignLeft.setBorderBottom(BorderStyle.THIN);
		alignLeft.setBorderTop(BorderStyle.THIN);
		alignLeft.setBorderLeft(BorderStyle.THIN);
		alignLeft.setBorderRight(BorderStyle.THIN);
		alignLeft.setWrapText(true);
		
		// ADD HEADER
		rowNumber += 2;
		row = sheet.createRow(rowNumber);
		row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
		cell = row.createCell(1);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Setting Group Code");
		
		cell = row.createCell(2);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Setting Group Name");
		
		cell = row.createCell(3);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Setting Group Desc");

		for(int i=0; i<settingGroup.size(); i++) {
			rowNumber += 1;
			row= sheet.createRow(rowNumber);
			cell = row.createCell(1);
			cell.setCellStyle(alignLeft);
			cell.setCellValue(settingGroup.get(i).getSettingGroupCode());
			
			cell = row.createCell(2);
			cell.setCellStyle(alignLeft);
			cell.setCellValue(settingGroup.get(i).getSettingGroupName());
			
			cell = row.createCell(3);
			cell.setCellStyle(alignLeft);
			cell.setCellValue(settingGroup.get(i).getSettingGroupDesc());
		}
		
		return wb;
	}

	public String settingGroupDownloadCSV(List<SettingGroup> settingGroup) {
		String download = "";
		String s = " | ";
		String header = "SETTING GROUP CODE "+s+" SETTING GROUP NAME " +s+ " SETTING GROUP DESCRIPTION \n";
		
		String field = "";
		for(int i=0; i<settingGroup.size(); i++) {
			SettingGroup sg = settingGroup.get(i);
//			field += '"'+sg.getSettingGroupCode()+'"' + s + '"'+sg.getSettingGroupName()+'"' + s + '"'+sg.getSettingGroupName()+'"' + " \n";
			field += sg.getSettingGroupCode() + s + sg.getSettingGroupName() + s + sg.getSettingGroupName() + " \n";
		}
		
		download += header;
		download += field;
		
		return download;
	}

}
