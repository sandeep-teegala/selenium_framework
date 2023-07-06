package selenium_framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.commonfunctions.CommonControls;

public class CheckOutPage extends CommonControls {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountryDD;

	@FindBy(xpath = "//section[contains(@class,'ta-results')]")
	WebElement countryDDList;

	@FindBy(xpath = "//*[contains(text(),'Place Order')]")
	WebElement placeOrderLoc;

	public void selectCountry(String value) {
		waitForVisibleElement(selectCountryDD, 5);
		actionSendKeys(selectCountryDD, value);
		waitForVisibleElement(countryDDList, 2);
		driver.findElement(
				By.xpath("//section[contains(@class,'ta-results')]//button/span[normalize-space()='" + value + "']"))
				.click();
	}

	public void placeOrder() {
		placeOrderLoc.click();
	}

}
