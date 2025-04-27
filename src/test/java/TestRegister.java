import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.IOException;  // THIS IS THE MISSING IMPORT
import static org.testng.Assert.*;
import org.example.ParseExcel; // Add this import at the top
public class TestRegister {
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@DataProvider(name = "excelData")
	public Object[][] getExcelData() throws IOException {
		return ParseExcel.readData("testdata.xlsx");
	}

	@Test(dataProvider = "excelData")
	public void testRegistration(String username, String email, String password,
								 String confirmPassword, String expectedResult) {
		RegisterPage registerPage = new RegisterPage(driver);
		driver.get("https://url-shortening-production-6c83.up.railway.app/signup");

		registerPage.register(username, email, password, confirmPassword);

		if (expectedResult.contains("Success")) {
			assertTrue(registerPage.isAlertVisible());
		} else {
			assertTrue(registerPage.getAlertText().contains("Error"));
		}
	}
}
