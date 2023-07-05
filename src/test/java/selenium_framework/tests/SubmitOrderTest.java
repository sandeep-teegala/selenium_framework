package selenium_framework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium_framework.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test
	public void placeOrder() throws IOException, InterruptedException {
		la.loginApplication("test557@gmail.com", "Test_557");
		String loginMsg = pp.getToastMessage("Login Successfully");
		Assert.assertEquals(loginMsg, "Login Successfully");
		String products[] = { "ZARA COAT 3" };
		pp.addToCart(products);
		pp.goToCart();
		Assert.assertTrue(cp.isCartItemsPresent(products));
		cp.goToCheckout();
		co.selectCountry("India");
		co.placeOrder();
		String thankmsg = cf.confirmMessage();
		Assert.assertEquals(thankmsg, "THANKYOU FOR THE ORDER.");
		String OrderToastMsg = pp.getToastMessage("Order Placed Successfully");
		Assert.assertEquals(OrderToastMsg, "Order Placed Successfully");
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "test557@gmail.com", "Test_557", "ZARA COAT 3" },
				{ "test558@gmail.com", "Test_558", "ADIDAS ORIGINAL" } };
	}

	// This test using test data with direct muti dimensiosns array in getData we
	// need to pass teh parameterts for the test see below
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(String email, String password, String product) throws IOException, InterruptedException {
		la.loginApplication(email, password);
		String loginMsg = pp.getToastMessage("Login Successfully");
		Assert.assertEquals(loginMsg, "Login Successfully");
		String products[] = { product };
		pp.addToCart(products);
		pp.goToCart();
		Assert.assertTrue(cp.isCartItemsPresent(products));
		cp.goToCheckout();
		co.selectCountry("India");
		co.placeOrder();
		String thankmsg = cf.confirmMessage();
		Assert.assertEquals(thankmsg, "THANKYOU FOR THE ORDER.");
		String OrderToastMsg = pp.getToastMessage("Order Placed Successfully");
		Assert.assertEquals(OrderToastMsg, "Order Placed Successfully");
	}

	@Test(dependsOnMethods = "submitOrder")
	public void verifyOrder() throws IOException, InterruptedException {
		la.loginApplication("test558@gmail.com", "Test_558");
		pp.goToOrders();
		String products[] = { "ADIDAS ORIGINAL" };
		Assert.assertTrue(op.isOrderPresent(products));
	}

	@DataProvider
	public Object[][] getData1() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "test557@gmail.com");
		map.put("password", "Test_557");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "test558@gmail.com");
		map1.put("password", "Test_558");
		map1.put("product", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map1 } };
	}

	// This test using test data with hashmap in getData1 we need to provide the
	// hashmap as parameter see below
	@Test(dataProvider = "getData1")
	public void submitOrder1(HashMap<String, String> input) throws IOException, InterruptedException {
		la.loginApplication(input.get("email"), input.get("password"));
		String loginMsg = pp.getToastMessage("Login Successfully");
		Assert.assertEquals(loginMsg, "Login Successfully");
		String products[] = { input.get("product") };
		pp.addToCart(products);
		pp.goToCart();
		Assert.assertTrue(cp.isCartItemsPresent(products));
		cp.goToCheckout();
		co.selectCountry("India");
		co.placeOrder();
		String thankmsg = cf.confirmMessage();
		Assert.assertEquals(thankmsg, "THANKYOU FOR THE ORDER.");
		String OrderToastMsg = pp.getToastMessage("Order Placed Successfully");
		Assert.assertEquals(OrderToastMsg, "Order Placed Successfully");
	}

	// This is a data Provider method which gets test data from .json file
	@DataProvider
	public Object[][] getData2() throws IOException {
		List<HashMap<String, String>> data = fu.getJsonDataToMap(
				"//selenium_framework//src//main//java//selenium_framework//resources//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// This test using test data with .json in getData2 we need to provide the
	// hashmap as parameter see below
	@Test(dataProvider = "getData1")
	public void submitOrder2(HashMap<String, String> input) throws IOException, InterruptedException {
		la.loginApplication(input.get("email"), input.get("password"));
		String loginMsg = pp.getToastMessage("Login Successfully");
		Assert.assertEquals(loginMsg, "Login Successfully");
		String products[] = { input.get("product") };
		pp.addToCart(products);
		pp.goToCart();
		Assert.assertTrue(cp.isCartItemsPresent(products));
		cp.goToCheckout();
		co.selectCountry("India");
		co.placeOrder();
		String thankmsg = cf.confirmMessage();
		Assert.assertEquals(thankmsg, "THANKYOU FOR THE ORDER.");
		String OrderToastMsg = pp.getToastMessage("Order Placed Successfully");
		Assert.assertEquals(OrderToastMsg, "Order Placed Successfully");
	}

}
