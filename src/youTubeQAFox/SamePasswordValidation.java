package youTubeQAFox;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SamePasswordValidation {

	String expectedConfirmationMessage = "Password confirmation does not match password!";

	@Test
	public void passwordValidation() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String mail = getDate() + "@gmail.com";

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Nilam");
		driver.findElement(By.id("input-lastname")).sendKeys("Maushi");
		driver.findElement(By.id("input-email")).sendKeys(mail);
		driver.findElement(By.id("input-telephone")).sendKeys("9535241523");
		driver.findElement(By.id("input-password")).sendKeys("Admin@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Admi@123");
		
		driver.findElement(By.xpath("//div[@class='pull-right']//input[@value='1']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPasswordMessage=driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText();

		Assert.assertEquals(expectedConfirmationMessage, actualPasswordMessage);
	}

	public String getDate() {

		Date date = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HH:mm:ss");

		String dateWithFormat = dateFormat.format(date);

		String dateWithNoSapce = dateWithFormat.replaceAll(" ", "");
		String dateWithNoColons = dateWithNoSapce.replaceAll(":", "");
		return dateWithNoColons;
	}

}
