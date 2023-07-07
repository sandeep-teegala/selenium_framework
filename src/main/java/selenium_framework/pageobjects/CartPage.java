package selenium_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.commonfunctions.CommonControls;

public class CartPage extends CommonControls {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li[class*='items']")
	List<WebElement> cartItems;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButtonLoc;

	public boolean isCartItemsPresent(String[] products) {
		waitForAllVisibleElements(cartItems, 5);
		boolean isItem = false;
		for (String str : products) {
			isItem = cartItems.stream()
					.anyMatch(item -> item.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(str));
		}
		return isItem;
	}

	public void goToCheckout() throws InterruptedException {
		Thread.sleep(2000);
		pageScrollToDown();
		waitForVisibleElement(checkoutButtonLoc, 5);
		checkoutButtonLoc.click();
		Thread.sleep(2000);
		pageScrollToUp();
		Thread.sleep(2000);
	}

}
