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

public class NewsLetterRadioButton {

	String newsLetter="Yes";
	
	@Test
	public void newsLetterRadioButton() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[contains(text(),'My')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
		String email=getDate()+"@gmail.com";
		
		driver.findElement(By.id("input-firstname")).sendKeys("Nilam");
		driver.findElement(By.id("input-lastname")).sendKeys("Maushi");
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("9535241523");
		driver.findElement(By.id("input-password")).sendKeys("Admin@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//div[@class='pull-right']/child::a/following-sibling::input[1]")).click();
		
		if (newsLetter.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//div[@class='form-group']//input[@value='1']")).click();
		}
		
		driver.findElement(By.xpath("//div[@class='pull-right']/child::a/following-sibling::input[2]")).click();
		
		driver.findElement(By.xpath("//aside[@id='column-right']//a[contains(text(),'My Account')]")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'Sub')]")).click();
		
		if (newsLetter.equalsIgnoreCase("yes")) {
			WebElement yesRadioButton=driver.findElement(By.xpath("//input[@value='1']"));
			System.out.println("If executed");
			Assert.assertTrue(yesRadioButton.isSelected());
		}else {
			WebElement yesRadioButton=driver.findElement(By.xpath("//input[@value='0']"));
			System.out.println("Else Executed");
			Assert.assertTrue(yesRadioButton.isSelected());
		}
		
		
		
		
		
	}
	
	public String getDate() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy HH:mm:ss");
		String dateFormat=sdf.format(date);
		String dateGot=dateFormat.replaceAll(" ", "").replaceAll(":", "");
		return dateGot;
	}
	
}
