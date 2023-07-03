package selenium_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.commonfunctions.CommonControls;
import selenium_framework.commonfunctions.CommonUiFunctions;

public class CartPage extends CommonControls {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.cart")
	List<WebElement> cartItems;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
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

	public void goToCheckout() {
		waitForVisibleElement(checkoutButtonLoc, 3);
		scrollToElement(checkoutButtonLoc);
		checkoutButtonLoc.click();
	}

}
