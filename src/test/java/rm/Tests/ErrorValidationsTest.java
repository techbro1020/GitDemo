package rm.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.ShippingPage;
import rm.TestComponents.BaseTest;
import rm.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("qwertasd@gmail.com", "Zxc@45"); //to login and create obj for new page invoked and return that obj
		
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());//we can remove "or" from the msg to fail this test
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("qwertasd@gmail.com", "Zxc@12345"); //to login and create obj for new page invoked and return that obj
		
		List<WebElement> products = productCatalogue.getProductList();//returns product list as webelement after applying waits
		
		productCatalogue.addProductToCart(productName); //find the product, clicks add to cart button, waits for the toast container to disappear and 
														//waits for loading icon to disappear
		
		MyCartPage mycartpage = productCatalogue.goToCartPage();//clicks on cart button.... goToCartPage() is a method in AbstractComponent class but as ProductCatalogue class is child of it
										//so its object can call it directly .. also it returns the obj for the class file of next page invoked
		
		boolean match = mycartpage.verifyCartItemsAndCheckout("ZARA COAT 333"); //checks if the item is added to cart and returns boolean ... 
																				//here we have given wrong value so match will be false 
		Assert.assertFalse(match); //as match is false, this should pass
	}

}
