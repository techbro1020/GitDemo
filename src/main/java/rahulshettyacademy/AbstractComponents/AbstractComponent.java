package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.MyOrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(css="[routerlink*='cart']")
		WebElement cartHeader;
		
		
		@FindBy(css="[routerlink*='myorders']")
		WebElement orderHeader;
		
	public void waitForElementToAppear(By findBy) { //here the arg is a By locator
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) { //here the arg is a By locator
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		w.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException { //here the arg is a By locator
		
		Thread.sleep(1000);
//		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
//		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
	public MyCartPage goToCartPage() {
		cartHeader.click();
		MyCartPage mycartpage = new MyCartPage(driver);
		return mycartpage;
	}
	
	public MyOrderPage goToOrdersPage() {
		orderHeader.click();
		MyOrderPage myorderpage = new MyOrderPage(driver);
		return myorderpage;
	}
	
}
