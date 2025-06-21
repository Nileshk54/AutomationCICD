package swagLabs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BaseTestSwagLabs{

	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	
	By allProducts=By.xpath("//div[@class='inventory_item']");
	By addToCartButton=By.xpath("//div[@id='shopping_cart_container']");

	
	public CartPage addProduct(String productName) {
		List<WebElement> allProductsElement=driver.findElements(allProducts);
		for(int i=1;i<=allProductsElement.size();i++) {
			String singleProductName=driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//div[@class='inventory_item_name']")).getText();
			if(singleProductName.toLowerCase().contains(productName)) {
		
				String actualPrice=driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//div[@class='inventory_item_price']")).getText();
				driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//button")).click();
		
				break;
			}
			
		}
		
		driver.findElement(addToCartButton).click();
		
		return new CartPage(driver);
	}
	
	public CartPage addMultipleProducts(List<String> allProductNames) {
		List<WebElement> allProductsElement=driver.findElements(allProducts);
		int count=0;
		for(int i=1;i<=allProductsElement.size();i++) {
			String singleProductName=driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//div[@class='inventory_item_name']")).getText();
			for(int j=0;j<allProductNames.size();j++) {
				if(singleProductName.toLowerCase().contains(allProductNames.get(j))) {
					driver.findElement(By.xpath("//div[@class='inventory_item']["+i+"]//button")).click();
					count++;
					break;
				}
			}
			
			if(count==3) {
				break;
			}
		}
		
		driver.findElement(addToCartButton).click();
		
		return new CartPage(driver);
	}
	
	
}
