package selenium_framework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import selenium_framework.pageobjects.CartPage;
import selenium_framework.pageobjects.CheckOutPage;
import selenium_framework.pageobjects.ConfirmationPage;
import selenium_framework.pageobjects.LoginPage;
import selenium_framework.pageobjects.OrdersPage;
import selenium_framework.pageobjects.productPage;
import selenium_framework.utilities.FileUtilities;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public LoginPage la;
	public productPage pp;
	public CartPage cp;
	public CheckOutPage co;
	public ConfirmationPage cf;
	public OrdersPage op;
	public FileUtilities fu;

	public WebDriver initializeDriver() throws IOException {

		// Properties class
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//selenium_framework//resources//GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		String browserMode = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("mode");

		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		System.setProperty("webdriver.chrome.silentOutput", "true");

		if (browserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito").addArguments("--remote-allow-origins=*").addArguments("--log-level=3")
					.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" })
					.setPageLoadStrategy(PageLoadStrategy.NORMAL).setAcceptInsecureCerts(true)
					.addArguments("--" + browserMode + "");
			if (browserMode.equalsIgnoreCase("headless")) {
				options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);
		}

		else if (browserName.equalsIgnoreCase("Firefox")) {
			FirefoxOptions Options = new FirefoxOptions();
			Options.addArguments("-private").addArguments("--log-level=3").setPageLoadStrategy(PageLoadStrategy.NORMAL)
					.setAcceptInsecureCerts(true).addArguments("--" + browserMode + "");
			if (browserMode.equalsIgnoreCase("headless")) {
				Options.addArguments("--window-size=1920,1080");
			}
			driver = new FirefoxDriver(Options);
		}

		else if (browserName.equalsIgnoreCase("Edge")) {
			EdgeOptions Options = new EdgeOptions();
			Options.addArguments("InPrivate").addArguments("--remote-allow-origins=*").addArguments("--log-level=3")
					.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" })
					.setPageLoadStrategy(PageLoadStrategy.NORMAL).setAcceptInsecureCerts(true)
					.addArguments("--" + browserMode + "");
			if (browserMode.equalsIgnoreCase("headless")) {
				Options.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(Options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().setSize(new Dimension(1920, 1080));
		// driver.manage().window().maximize();

		return driver;

	}

	public String getScreenShot(String testName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\output\\screenshots" + testName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\output\\screenshots" + testName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		la = new LoginPage(driver);
		pp = new productPage(driver);
		cp = new CartPage(driver);
		co = new CheckOutPage(driver);
		cf = new ConfirmationPage(driver);
		op = new OrdersPage(driver);
		fu = new FileUtilities();
		String url = prop.getProperty("url");
		la.goTo(url);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
