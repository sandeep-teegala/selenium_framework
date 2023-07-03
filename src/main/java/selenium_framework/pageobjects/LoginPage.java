package selenium_framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// By using the direct webelement we do page objects like below
	// WebElement email =
	// driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"));

	// But there is a optimised way by using the pagefactory like below using
	// @FindBy
	// that means driver.findElement will be constacted in run time by @FindBy
	// To say @FindBy find driver we need to do intiElelments in constructor

	@FindBy(xpath = "//label[text()='Email']/following-sibling::input")
	WebElement email;

	@FindBy(xpath = "//label[text()='Password']/following-sibling::input")
	WebElement password;

	@FindBy(css = "input[value='Login']")
	WebElement loginButton;

	public void lauchApplication(String url) {
		driver.get(url);
	}

	// Once we done with loctors we do actions
	public void loginApplication(String emailValue, String passwordValue) {
		email.sendKeys(emailValue);
		password.sendKeys(passwordValue);
		loginButton.click();
	}

}
