package youTubeQAFox;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class messageValidations {

	String expectedPrivacyMessage="Warning: You must agree to the Privacy Policy!";
	String expectedFirstNameMessage="First Name must be between 1 and 32 characters!";
	String expectedLastNameMessage="Last Name must be between 1 and 32 characters!";
	String expectedEmailMessage="E-Mail Address does not appear to be valid!";
	String expectedTelephoneMessage="Telephone must be between 3 and 32 characters!";
	String expectedPassWordMessage="Password must be between 4 and 20 characters!";
	
	@Test
	public void validationMessages() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.xpath("//div[@class='pull-right']/child::input[2]")).click();
		
		String actualPrivacyMessage=driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
		
		Assert.assertEquals(expectedPrivacyMessage, actualPrivacyMessage);
		
		String actualFirstNameMessage=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		
		Assert.assertEquals(expectedFirstNameMessage,actualFirstNameMessage );
		
		
		
		String actualLastNameMessage=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		
		Assert.assertEquals(expectedLastNameMessage,actualLastNameMessage );
		
		String actualEmailMessage=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		
		Assert.assertEquals(expectedEmailMessage,actualEmailMessage );
		
		String actualTelephoneMessage=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		
		Assert.assertEquals(expectedTelephoneMessage,actualTelephoneMessage );
		
		String actualPasswordMessage=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		
		Assert.assertEquals(expectedPassWordMessage,actualPasswordMessage );
		
		driver.quit();
		
		
	}
	
}
