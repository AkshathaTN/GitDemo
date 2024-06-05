package atn.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import atn.TestComponents.BaseTest;
import atn.pageobjects.CartPage;
import atn.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException
	{
		
		String productName ="ZARA COAT 3";
		landingPage.loginApplication("puvi@gmail.com", "Akshu@13");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	public void ProductErrorValidation() throws IOException
	{
		
		String productName ="ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("puvi@gmail.com", "Akshu@123");
		
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match =  cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertTrue(match);		

	}

}
