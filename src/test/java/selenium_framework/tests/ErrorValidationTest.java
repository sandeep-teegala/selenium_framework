package selenium_framework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium_framework.testcomponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = "ErrorHandling")
	public void loginError() {
		la.loginApplication("test557@gmail.com", "Test_777");
		String loginMsg = pp.getToastMessage("Incorrect email or password.");
		Assert.assertEquals(loginMsg, "Incorrect email or password!");
	}

	@Test
	public void productError() throws IOException, InterruptedException {
		la.loginApplication("test557@gmail.com", "Test_557");
		String loginMsg = pp.getToastMessage("Login Successfully");
		Assert.assertEquals(loginMsg, "Login Successfully");
		String products[] = { "ZARA COAT 3" };
		pp.addToCart(products);
		pp.goToCart();
		String errorProducts[] = { "ZARA COAT 33" };
		Assert.assertFalse(cp.isCartItemsPresent(errorProducts));
		cp.goToCheckout();
		co.selectCountry("India");
		co.placeOrder();
		String thankmsg = cf.confirmMessage();
		Assert.assertEquals(thankmsg, "THANKYOU FOR THE ORDER.");
		String OrderToastMsg = pp.getToastMessage("Order Placed Successfully");
		Assert.assertEquals(OrderToastMsg, "Order Placed Successfully");
	}

}
