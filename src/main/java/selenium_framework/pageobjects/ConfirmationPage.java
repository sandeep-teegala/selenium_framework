package selenium_framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.commonfunctions.CommonControls;

public class ConfirmationPage extends CommonControls {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1.hero-primary")
	WebElement confirmMessageLoc;

	public String confirmMessage() {
		return confirmMessageLoc.getText().trim();
	}

}
