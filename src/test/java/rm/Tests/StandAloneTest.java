package rm.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage landingpage = new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		
		driver.findElement(By.id("userEmail")).sendKeys("qwertasd@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Zxc@12345");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));//waits for the elements to get loaded
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //clicks the add to cart button only within the prod scope
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));//waits for toast container to disappear
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //waits for the loading icon to disappear
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); //clicks on cart button
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//to check if we added the correct product
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); 
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();//clicks on checkout
		
		//selecting country
//		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
//		
//		List<WebElement> countries =driver.findElements(By.cssSelector(".ta-results button"));
//		
//		WebElement count= countries.stream().filter(country->country.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
//		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
//		count.click();//Note: if we dont maximise the screen then the toast container may get the click...so we have used wait for it to disappear
	
		//the above can also be done as below
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "ind").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));//waits till the country dropdown gets loaded
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();//clicks on place order
	
		//to confirm if order is placed
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
	}

}
