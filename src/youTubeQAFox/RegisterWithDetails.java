package youTubeQAFox;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterWithDetails {

	@Test
	public void registerWithAllDetails() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//span[contains(text(),'My')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
		String email=getDate()+"@gmail.com";
		
		driver.findElement(By.id("input-firstname")).sendKeys("Nilam");
		driver.findElement(By.id("input-lastname")).sendKeys("Maushi");
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("9535241523");
		driver.findElement(By.id("input-password")).sendKeys("Admin@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Admin@123");
		
		driver.findElement(By.xpath("//label[@class='radio-inline']/child::input[@value='1']")).click();
		driver.findElement(By.xpath("//div[@class='pull-right']/child::a/following-sibling::input[1]")).click();
		
		driver.findElement(By.xpath("//div[@class='pull-right']/child::a/following-sibling::input[2]")).click();
		
		driver.findElement(By.xpath("//div[@class='list-group']//a[contains(text(),'My Account')]")).click();
		
		WebElement editButton=driver.findElement(By.xpath("//a[contains(text(),'Edit your ')]"));
		
		Assert.assertTrue(editButton.isDisplayed());
		
		
		
		
	}
	
	public String getDate() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy HH:mm:ss");
		String dateFormat=sdf.format(date);
		String dateGot=dateFormat.replaceAll(" ", "").replaceAll(":", "");
		return dateGot;
	}
	
}
