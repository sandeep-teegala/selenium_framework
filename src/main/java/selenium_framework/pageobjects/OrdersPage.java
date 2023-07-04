package selenium_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.commonfunctions.CommonControls;

public class OrdersPage extends CommonControls {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ordersLoc;

	public boolean isOrderPresent(String[] products) {
		waitForAllVisibleElements(ordersLoc, 5);
		boolean isItem = false;
		for (String str : products) {
			isItem = ordersLoc.stream().anyMatch(item -> item.getText().equalsIgnoreCase(str));
		}
		return isItem;
	}

}
