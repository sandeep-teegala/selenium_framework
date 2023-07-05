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

	@FindBy(css = "div.cart")
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
		// Thread.sleep(Duration.ofSeconds(5));
		// js.executeScript("arguments[0].scrollIntoView(true);", checkoutButtonLoc);
		// js.executeScript("window.scrollBy(0,2500)");
		// waitForVisibleElement(checkoutButtonLoc, 3);
		// scrollToElement(checkoutButtonLoc);
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
//
//		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
//
//		Actions a = new Actions(driver);
//		a.moveToElement(checkoutButtonLoc).build().perform();
//
//		Thread.sleep(Duration.ofSeconds(5));

		checkoutButtonLoc.click();
	}

}
