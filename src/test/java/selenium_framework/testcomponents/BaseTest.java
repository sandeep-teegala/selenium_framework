package selenium_framework.testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import selenium_framework.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		// Properties class
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//selenium_framework//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		String browserMode = prop.getProperty("mode");

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
					.setPageLoadStrategy(PageLoadStrategy.NORMAL).setAcceptInsecureCerts(true)
					.addArguments("--" + browserMode + "");
			if (browserMode.equalsIgnoreCase("headless")) {
				Options.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(Options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if (browserMode.equalsIgnoreCase("headed")) {
			driver.manage().window().maximize();
		}

		return driver;

	}

	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		LoginPage la = new LoginPage(driver);
		String url = prop.getProperty("url");
		la.lauchApplication(url);
		return la;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
