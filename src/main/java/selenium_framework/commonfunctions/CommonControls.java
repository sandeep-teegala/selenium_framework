package selenium_framework.commonfunctions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonControls {

	WebDriver driver;
	WebDriverWait wait;

	public CommonControls(WebDriver driver) {
		this.driver = driver;
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

}
