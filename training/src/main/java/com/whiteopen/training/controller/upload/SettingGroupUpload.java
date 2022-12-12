package com.whiteopen.training.controller.upload;
import com.whiteopen.training.module.settingGroup.entity.SettingGroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SettingGroupUpload {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static Boolean hasExcelFormat(MultipartFile file) {
     	if (!TYPE.equals(file.getContentType())) {
     		return false;
     	}
     	return true;
   }

	public List<SettingGroup> uploadSettingGroup(InputStream is) throws ParseException {
		try {
	         Workbook workbook = new XSSFWorkbook(is);

	         Sheet sheet = workbook.getSheetAt(0);
	         if (sheet == null) {
	            workbook.close();
	         }

	         List<SettingGroup> settingGroup = new ArrayList<>(10000);

	         int rowNumber = 0;
	         for (Row rows : sheet) {
	            if (rowNumber == 0) {
	               rowNumber++;
	               continue;
	            }

	            SettingGroup vo = new SettingGroup();
	            for (int celli = 1; celli < rows.getLastCellNum(); celli++) {

	               Cell currentCell = rows.getCell(celli, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	               if (celli == 1) {
	                  vo.setSettingGroupCode(StringUtils.isNotEmpty(currentCell.getStringCellValue())
	                              ? currentCell.getStringCellValue().trim() : "");
	               } else if (celli == 2) {
	                  vo.setSettingGroupName(StringUtils.isNotEmpty(currentCell.getStringCellValue())
	                        || currentCell.getCellType() != CellType.BLANK ? currentCell.getStringCellValue().trim() : "");
	               } else if (celli == 3) {
	            	   vo.setSettingGroupDesc(StringUtils.isNotEmpty(currentCell.getStringCellValue())
		                        || currentCell.getCellType() != CellType.BLANK ? currentCell.getStringCellValue().trim() : "");
	               }
	            }
	            settingGroup.add(vo);
	         }
	         workbook.close();
	         return settingGroup;
	      } catch (IOException e) {
	         throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	      }
	}
}
