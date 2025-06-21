package swagLabs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabsSinglePage {

	String productName="jacket";
	String confirmationMessage="THANK YOU FOR YOUR ORDER";
	
	@Test
	public void swagLabsSinglePage() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("---incognito");
		WebDriver driver=new ChromeDriver(opt);
		driver.get("https://www.saucedemo.com/v1/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
		List<WebElement> allProductNames=driver.findElements(By.xpath("//div[@class='inventory_item']"));
		
		for(int i=1;i<=allProductNames.size();i++) {
			String allProductName=driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//div[@class='inventory_item_name']")).getText().toLowerCase();
			
			if(allProductName.contains(productName)) {
				driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//button")).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
		
		String addedProductName=driver.findElement(By.xpath("//div[@class='cart_item_label']/child::a/child::div")).getText();
		
		System.out.println(" addedProductName " + addedProductName);
		boolean flag=addedProductName.toLowerCase().contains(productName);
		System.out.println(flag);
		Assert.assertTrue(addedProductName.toLowerCase().contains(productName));
		
		driver.findElement(By.xpath("//a[contains(text(),'CHECKOUT')]")).click();
		
		driver.findElement(By.id("first-name")).sendKeys("Nilesh");
		driver.findElement(By.id("last-name")).sendKeys("Khalate");
		driver.findElement(By.id("postal-code")).sendKeys("411028");
		
		driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'FINISH')]")).click();
		
		String expectedConfirmationMessage=driver.findElement(By.xpath("//div[@class='checkout_complete_container']/h2")).getText();
		
		Assert.assertEquals(confirmationMessage, expectedConfirmationMessage);
		
		driver.quit();
	}
	
}
