package selenium_framework.commonfunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonUiFunctions {

	WebDriver driver;

	public CommonUiFunctions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

}
