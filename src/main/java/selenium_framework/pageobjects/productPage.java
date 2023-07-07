package selenium_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.commonfunctions.CommonControls;

public class productPage extends CommonControls {

	WebDriver driver;

	public productPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> getProductsListLoc;

	@FindBy(xpath = "//div[contains(@class,'ngx-spinner')]")
	WebElement spinnerLoc;

	@FindBy(xpath = "//div[@id='toast-container']/div/div[normalize-space()='Product Added To Cart']")
	WebElement productAddedToastLoc;

	By addToCartLoc = By.xpath("//button[normalize-space()='Add To Cart']");

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	public String getToastMessage(String text) {
		String loginToastLoc = "//div[@id='toast-container']/div[normalize-space()='" + text + "']";
		waitForVisibleElement(driver.findElement(By.xpath(loginToastLoc)), 5);
		return driver.findElement(By.xpath(loginToastLoc)).getText().trim();
	}

	public void waitForSpinnerInvisible() {
		waitForInvisibleElement(spinnerLoc, 5);
	}

	public WebElement getProduct(String products) {
		waitForAllVisibleElements(getProductsListLoc, 5);
		WebElement getProduct = getProductsListLoc.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(products))
				.findFirst().orElse(null);
		return getProduct;
	}

	public void addToCart(String[] products) throws InterruptedException {
		for (String pro : products) {
			WebElement product = getProduct(pro);
			product.findElement(addToCart).click();
			// waitForSpinnerInvisible();
			Thread.sleep(2000);
			waitForVisibleElement(productAddedToastLoc, 5);
		}
	}

}
