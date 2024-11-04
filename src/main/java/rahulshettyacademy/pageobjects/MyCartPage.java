package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent  {
WebDriver driver;
	
	public MyCartPage(WebDriver driver) { //gets driver from the test file
		
		super(driver);
		this.driver=driver; //assigns local driver to the driver from test file
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean verifyCartItemsAndCheckout(String productName) {
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); 
		return match;
	}
	
	public ShippingPage goToCheckout() {
		checkoutEle.click();
		return new ShippingPage(driver); //we can return ShippingPage obj like this directly also
	}
}
