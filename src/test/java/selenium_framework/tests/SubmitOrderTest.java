package selenium_framework.tests;

import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import selenium_framework.pageobjects.LoginPage;
import selenium_framework.pageobjects.productPage;

public class SubmitOrderTest {

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

		LoginPage la = new LoginPage(driver);

		la.lauchApplication("https://rahulshettyacademy.com/client/");

		la.loginApplication("test557@gmail.com", "Test_557");

		productPage pp = new productPage(driver);

		String loginMsg = pp.getToastMessage("Login Successfully");

		Assert.assertEquals(loginMsg, "Login Successfully");

		String products[] = { "ZARA COAT 3", "ADIDAS ORIGINAL" };

		pp.addToCart(products);

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
