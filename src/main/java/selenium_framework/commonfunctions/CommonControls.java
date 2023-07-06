package selenium_framework.commonfunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonControls {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor js;

	public CommonControls(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav//button[contains(text(),'Cart')]")
	WebElement cartButtonLoc;

	@FindBy(xpath = "//nav//button[contains(text(),'ORDERS')]")
	WebElement ordersButtonLoc;

	public void goToCart() {
		cartButtonLoc.click();
	}

	public void goToOrders() {
		ordersButtonLoc.click();
	}

	public void waitForVisibleElement(WebElement element, int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForInvisibleElement(WebElement element, int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForVisibleLocator(By locator, int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForInvisibleLocator(By locator, int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForAllVisibleElements(List<WebElement> elements, int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitForAllVisibleElements(WebElement element, int sec) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void actionSendKeys(WebElement element, String value) {
		action = new Actions(driver);
		action.sendKeys(element, value).build().perform();
	}

	public void actionScrollToElement(WebElement element) {
		action = new Actions(driver);
		action.scrollToElement(element).build().perform();
	}

	public void pageScrollToUp() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}

	public void pageScrollToDown() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public void pageScrollByVerticalIndex(int index) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + index + ")");
	}

	public void pageScrollByHorizontalIndex(int index) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + index + ",0)");
	}

	public void elementScrollToUp(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(" + element + ").scrollTo(document.body.scrollHeight, 0);");
	}

	public void elementScrollToDown(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(" + element + ").scrollTo(0, document.body.scrollHeight);");
	}

	public void elementScrollByVerticalIndex(WebElement element, int index) {
		js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(" + element + ").scrollBy(0," + index + ");");
	}

	public void elementScrollByHorizontalIndex(WebElement element, int index) {
		js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(" + element + ").scrollBy(" + index + ",0);");
	}

}
