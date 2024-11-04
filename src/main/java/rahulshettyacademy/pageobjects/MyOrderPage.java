package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class MyOrderPage extends AbstractComponent  {
WebDriver driver;

	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;

	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public MyOrderPage(WebDriver driver) { //gets driver from the test file
		
		super(driver);
		this.driver=driver; //assigns local driver to the driver from test file
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyOrderDisplay(String productName) {
		boolean match = productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName)); 
		return match;
	}
}
