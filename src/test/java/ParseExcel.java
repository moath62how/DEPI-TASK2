import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ParseExcel {
	public static Object[][] readData(String path) throws IOException {
		FileInputStream file = new FileInputStream(new File(path));
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getPhysicalNumberOfRows();
		//skip the res that's why -1
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells() -1 ;
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

}
