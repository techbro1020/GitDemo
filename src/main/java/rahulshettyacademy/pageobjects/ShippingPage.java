package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ShippingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ShippingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement placeholder;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement countryname;
	
	@FindBy(css=".action__submit")
	WebElement PlaceOrder;
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(placeholder, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));//waits till the country dropdown gets loaded
		countryname.click();	

	}
	 
	public ConfirmationPage submitOrder() {
		PlaceOrder.click();//clicks on place order
		return new ConfirmationPage(driver);
	}
}
