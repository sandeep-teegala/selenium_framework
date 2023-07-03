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
import org.testng.annotations.Test;

import selenium_framework.pageobjects.CartPage;
import selenium_framework.pageobjects.CheckOutPage;
import selenium_framework.pageobjects.ConfirmationPage;
import selenium_framework.pageobjects.LoginPage;
import selenium_framework.pageobjects.productPage;
import selenium_framework.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test
	public void submitOrder() throws InterruptedException {
		LoginPage la = new LoginPage(driver);
		productPage pp = new productPage(driver);
		CartPage cp = new CartPage(driver);
		CheckOutPage co = new CheckOutPage(driver);
		ConfirmationPage cf = new ConfirmationPage(driver);

		la.lauchApplication("https://rahulshettyacademy.com/client/");

		la.loginApplication("test557@gmail.com", "Test_557");

		String loginMsg = pp.getToastMessage("Login Successfully");

		Assert.assertEquals(loginMsg, "Login Successfully");

		String products[] = { "ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO" };

		pp.addToCart(products);

		pp.goToCart();

		cp.isCartItemsPresent(products);

		Assert.assertTrue(true);

		cp.goToCheckout();

		co.selectCountry("India");

		co.placeOrder();

		String thankmsg = cf.confirmMessage();

		Assert.assertEquals(thankmsg, "THANKYOU FOR THE ORDER.");

		String OrderToastMsg = pp.getToastMessage("Order Placed Successfully");

		Assert.assertEquals(OrderToastMsg, "Order Placed Successfully");

	}

}
