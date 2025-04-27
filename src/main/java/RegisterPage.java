import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By confirmPasswordLocator = By.id("confirmPassword");
	By emailLocator = By.id("email");
	By submitBtnLocator = By.className("btn-primary");
	By alertLocator = By.xpath("//div[contains(@class,'toastify-center toastify-top')]");

	WebElement usernameField = find(usernameLocator);
	WebElement passwordField = find(passwordLocator);
	WebElement confirmPasswordField = find(confirmPasswordLocator);
	WebElement emailField = find(emailLocator);
	WebElement submitBtn = find(submitBtnLocator);
	WebElement alert = find(alertLocator);

	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	public String getAlertText(){
		return alert.getText();
	}
	public boolean isAlertVisible(){
		return alert.isDisplayed();
	}
	public void register(String username, String password, String confirmPassword, String email) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		emailField.sendKeys(email);
		submitBtn.click();
	}
}
