import org.openqa.selenium.*;

public class BasePage {
	protected final WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement find(By locator){
		return driver.findElement(locator);
	}
}
