import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ParseExcel {
	private static Object[][] readAllData(String path) throws IOException {
		FileInputStream file = new FileInputStream(new File(path));
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getPhysicalNumberOfRows();
		//skip the res that's why -1
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells() ;
		System.out.println(colCount);

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = cell == null ?  "" : cell.getStringCellValue();
			}
		}
		workbook.close();
		return data;
	}
	public static Object[][] readValidData(String path) throws IOException {
		Object[][] allData = readAllData(path);
		List<Object[]> validList = new ArrayList<>();

		for (Object[] row : allData) {
			if (row == null || row.length == 0) {
				continue; // Skip null or empty rows because of an wired error that i have no clue how to fix
			}
			String result = row[row.length - 1].toString();
			if (result.equalsIgnoreCase("success")) {
				Object[] trimmedRow = new Object[row.length - 1];
				System.arraycopy(row, 0, trimmedRow, 0, row.length - 1);
				validList.add(trimmedRow);
			}
		}
		return validList.toArray(new Object[0][]);
	}


	public static Object[][] readInvalidData(String path) throws IOException {
		Object[][] allData = readAllData(path);
		List<Object[]> invalidList = new ArrayList<>();

		for (Object[] row : allData) {
			String result = row[row.length - 1].toString();
			if (!result.equalsIgnoreCase("success") && result.contains("Error")) {
				Object[] trimmedRow = new Object[row.length - 1];
				System.arraycopy(row, 0, trimmedRow, 0, row.length - 1);
				invalidList.add(trimmedRow);
			}
		}
		return invalidList.toArray(new Object[0][]);
	}

}