package swagLabs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OverViewPage extends BaseTestSwagLabs{

	WebDriver driver;
	public OverViewPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	By productNameElement=By.xpath("//div[@class='inventory_item_name']");
	By finishButtonElement=By.xpath("//a[contains(text(),'FINISH')]");
	By thankYouElement=By.xpath("//h2[@class='complete-header']");
	
	public void placeOrder(String productName) {
		String expectedProductName=driver.findElement(productNameElement).getText();
		Assert.assertTrue(expectedProductName.toLowerCase().contains(productName));
		driver.findElement(finishButtonElement).click();
		String expectedThankYouMessage=driver.findElement(thankYouElement).getText();
		Assert.assertEquals(expectedThankYouMessage, thankYouMessage);
	}
	
	public void multipleProductOrder(List<String> allProductNames) {
		for(int i=1;i<=allProductNames.size();i++) {
			String singleProductName=driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText();
			Assert.assertTrue(singleProductName.toLowerCase().contains(allProductNames.get(i-1)));
			
		}
		
		driver.findElement(finishButtonElement).click();
		String expectedThankYouMessage=driver.findElement(thankYouElement).getText();
		Assert.assertEquals(expectedThankYouMessage, thankYouMessage);
	}
}
