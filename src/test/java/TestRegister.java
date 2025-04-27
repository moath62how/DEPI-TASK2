import org.testng.annotations.*;
import java.io.IOException;

public class TestRegister
{
	@DataProvider(name = "excelData")
	public static Object[][] getExcelData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testData.xlsx"; // adjust your path
		return ParseExcel.readData(filePath);
	}

}
