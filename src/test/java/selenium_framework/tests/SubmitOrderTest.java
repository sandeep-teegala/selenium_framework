package selenium_framework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium_framework.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {
		la.loginApplication("test557@gmail.com", "Test_557");
		String loginMsg = pp.getToastMessage("Login Successfully");
		Assert.assertEquals(loginMsg, "Login Successfully");
		String products[] = { "ZARA COAT 3" };
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
