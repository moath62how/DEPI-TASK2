import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRegister {
	private final String BASE_URL = "https://url-shortening-production-6c83.up.railway.app/signup";
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(BASE_URL);

	}


	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

	@DataProvider(name = "validRegisterData")
	public Object[][] getExcelData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testData.xlsx";
		return ParseExcel.readValidData(filePath);
	}

	@Test(dataProvider = "validRegisterData")
	public void testValidRegistration(String username, String email, String password,
	                                  String confirmPassword) {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.register(username, email, password, confirmPassword);
		Assert.assertTrue(registerPage.isAlertVisible(), "The alert is not displayed");
		Assert.assertTrue(registerPage.getAlertText().contains("Success"), "Unexpected message in the alert :" + registerPage.getAlertText());

	}
}
