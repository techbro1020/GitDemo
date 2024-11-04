package rm.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.MyOrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.ShippingPage;
import rm.TestComponents.BaseTest;

public class SubmitOrderTestWithJSON extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"PurchaseViaJSON"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		
		
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password")); //to login and create obj for new page invoked and return that obj
		
		List<WebElement> products = productCatalogue.getProductList();//returns product list as webelement after applying waits
		
		productCatalogue.addProductToCart(input.get("product")); //find the product, clicks add to cart button, waits for the toast container to disappear and 
														//waits for loading icon to disappear
		
		MyCartPage mycartpage = productCatalogue.goToCartPage();//clicks on cart button.... goToCartPage() is a method in AbstractComponent class but as ProductCatalogue class is child of it
										//so its object can call it directly .. also it returns the obj for the class file of next page invoked
		
		boolean match = mycartpage.verifyCartItemsAndCheckout(input.get("product")); //checks if the item is added to cart and returns boolean
		Assert.assertTrue(match);
		ShippingPage shipPage = mycartpage.goToCheckout(); //clicks on checkout page and creates obj for next page class file and returns it	
		shipPage.selectCountry("ind");//selects country
		ConfirmationPage confPg = shipPage.submitOrder(); //clicks on place order and return obj for next page class
	
		//to confirm if order is placed
		String confirmMsg = confPg.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));	
	}

	@Test(dependsOnMethods= {"submitOrder"}) //submitOrder() method will run first and add product to cart then this method will verify if order is placed
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingpage.loginApplication("qwertasd@gmail.com", "Zxc@12345");
		MyOrderPage ordersPage = landingpage.goToOrdersPage(); //or  productCatalogue.goToOrdersPage() also works as both extend the abstractcomponent class
		
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));;
		
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rm\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
