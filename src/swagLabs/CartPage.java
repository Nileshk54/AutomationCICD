package swagLabs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	By checkOutElement=By.xpath("//a[contains(text(),'CHECKOUT')]");
	By productNameElement=By.xpath("//div[@class='inventory_item_name']");
	
	
	public InformationPage goToCheckOut(String productName) {
		String actualProductName=driver.findElement(productNameElement).getText();
		Assert.assertTrue(actualProductName.toLowerCase().contains(productName));
		driver.findElement(checkOutElement).click();
		return new InformationPage(driver);
	}
	
	public InformationPage multipleGoToCheckOut(List<String> productNames) {
		for(int i=1;i<=productNames.size();i++) {
			String singleProductName=driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText();
			Assert.assertTrue(singleProductName.toLowerCase().contains(productNames.get(i-1)));
			
		}
		driver.findElement(checkOutElement).click();
		return new InformationPage(driver);
	}

}
