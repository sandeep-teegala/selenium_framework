package selenium_framework.tests;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubmitOrder__StandAlone_Test {

	static WebDriver driver;

	public static void main(String[] args) {

		String mode = "headed";
		driver = new ChromeDriver(browerOptions(mode));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		if (mode.equalsIgnoreCase("headed")) {
			driver.manage().window().maximize();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Actions a = new Actions(driver);

		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input")).sendKeys("test557@gmail.com");
		driver.findElement(By.xpath("//label[text()='Password']/following-sibling::input")).sendKeys("Test_557");
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		String loginToastLoc = "//div[@id='toast-container']/div[normalize-space()='Login Successfully']";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(loginToastLoc))));
		String loginNotificationMessage = driver.findElement(By.xpath(loginToastLoc)).getText().trim();
		System.out.println(loginNotificationMessage);

		List<WebElement> getProductsList = driver.findElements(By.cssSelector(".mb-3"));
		WebElement getProduct = getProductsList.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		getProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ngx-spinner')]"))));

		String productAddToastLoc = "//div[@id='toast-container']/div[normalize-space()='Product Added To Cart']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productAddToastLoc)));
		String productAddMessage = driver.findElement(By.xpath(productAddToastLoc)).getText().trim();
		System.out.println(productAddMessage);

		driver.findElement(By.xpath("//nav//button[contains(text(),'Cart')]")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector("div.cart"));
		boolean isItem = cartItems.stream()
				.anyMatch(item -> item.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		System.out.println(isItem);

		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();

		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build()
				.perform();
		String countryDDList = " //section[contains(@class,'ta-results')]";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(countryDDList))));
		driver.findElement(By.xpath(countryDDList + "//button/span[normalize-space()='India']")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Place Order')]")).click();

		String thankMessage = driver.findElement(By.cssSelector("h1.hero-primary")).getText().trim();
		System.out.println(thankMessage);

		String orderPlacedToastLoc = "//div[@id='toast-container']/div[normalize-space()='Order Placed Successfully']";
		String orderPlacedMessage = driver.findElement(By.xpath(orderPlacedToastLoc)).getText().trim();
		System.out.println(orderPlacedMessage);
		driver.quit();
	}

	public static ChromeOptions browerOptions(String Mode) {
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito").addArguments("--remote-allow-origins=*").addArguments("--log-level=3")
				.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" })
				.setPageLoadStrategy(PageLoadStrategy.NORMAL).addArguments("--" + Mode + "");
		if (Mode.equalsIgnoreCase("headless")) {
			options.addArguments("--window-size=1920,1080");
		}
		return options;
	}

}
