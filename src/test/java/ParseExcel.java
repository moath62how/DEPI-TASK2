package org.example;

import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParseExcel {
	public static Object[][] readData(String fileName) throws IOException {
		try (InputStream file = ParseExcel.class.getClassLoader().getResourceAsStream(fileName)) {
			if (file == null) {
				throw new IOException("Excel file not found: " + fileName);
			}

			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);

			// Dynamically determine rows and columns
			int startRow = 1; // Skip header row
			int endRow = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum(); // Header column count

			List<Object[]> dataList = new ArrayList<>();

			for (int i = startRow; i <= endRow; i++) {
				Row row = sheet.getRow(i);
				if (row == null) continue; // Skip empty rows

				Object[] rowData = new Object[colCount];
				boolean isEmptyRow = true;

				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					rowData[j] = getCellValue(cell);
					if (rowData[j] != null && !rowData[j].toString().isEmpty()) {
						isEmptyRow = false;
					}
				}

				if (!isEmptyRow) {
					dataList.add(rowData);
				}
			}

			workbook.close();
			return dataList.toArray(new Object[0][]);
		}
	}

	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue().trim();
			case NUMERIC:
				return cell.getNumericCellValue();
			case BOOLEAN:
				return cell.getBooleanCellValue();
			case BLANK:
				return "";
			default:
				return null;
		}
	}
}
