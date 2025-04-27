import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException; // <-- ADD THIS IMPORT
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends BasePage {
	private final By usernameLocator = By.id("username");
	private final By passwordLocator = By.id("password");
	private final By confirmPasswordLocator = By.id("confirmPassword");
	private final By emailLocator = By.id("email");
	private final By submitBtnLocator = By.className("btn-primary");
	private final By alertLocator = By.xpath("//div[contains(@class,'toastify-center toastify-top')]");

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	public void register(String username, String email, String password, String confirmPassword) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator)).sendKeys(username);
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailLocator)).sendKeys(email);
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator)).sendKeys(password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordLocator)).sendKeys(confirmPassword);
		wait.until(ExpectedConditions.elementToBeClickable(submitBtnLocator)).click();
	}
	public String getAlertText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(alertLocator));
			return alert.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public boolean isAlertVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(alertLocator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
