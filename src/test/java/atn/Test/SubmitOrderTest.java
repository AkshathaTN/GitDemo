package atn.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import atn.TestComponents.BaseTest;
import atn.pageobjects.CartPage;
import atn.pageobjects.CheckoutPage;
import atn.pageobjects.ConfirmationPage;
import atn.pageobjects.OrderPage;
import atn.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	
	String productName ="ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match =  cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods={"submitOrder"})

	public void OrdersHistoryTest()
	{
		//"ZARA COAT
		ProductCatalog productCatalog = landingPage.loginApplication("puvi@gmail.com", "Akshu@123");
		OrderPage orderpage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<Object,Object> map= new <Object,Object>HashMap();
//		map.put("email","puvi@gmail.com" );
//		map.put("password", "Akshu@123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<Object,Object> map1= new <Object,Object>HashMap();
//		map1.put("email","anshika@gmail.com" );
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/atn/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {"puvi@gmail.com","Akshu@123","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}

}
