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

public class RegisterAccount {

	static String accountCreationMessage="Your Account Has Been Created!";
	static String expectedProperDetails1="Congratulations! Your new account has been successfully created!";
	static String expectedProperDetails2="You can now take advantage of member privileges to enhance your online shopping experience with us.";
	static String expectedProperDetails3="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
	static String expectedProperDetails4="A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";
	
	@Test
	public void registerAccount() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
		String email=getDate()+"@gamil.com";
		
		driver.get("https://tutorialsninja.com/demo");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Neel");
		driver.findElement(By.id("input-lastname")).sendKeys("Nitin");
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("7020725236");
		
		driver.findElement(By.id("input-password")).sendKeys("Nileshk_54");
		driver.findElement(By.id("input-confirm")).sendKeys("Nileshk_54");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		System.out.println(email);
		
		WebElement logoutButton=driver.findElement(By.linkText("Logout"));
		Assert.assertTrue(logoutButton.isDisplayed());
		
		String actualMessage=driver.findElement(By.xpath("//div[@id='content']/child::h1")).getText();
		
		Assert.assertEquals(actualMessage, accountCreationMessage);
		
		String actualProperties1=driver.findElement(By.xpath("//div[@id='content']/p[1]")).getText();
		
		String actualProperties2=driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText();
		String actualProperties3=driver.findElement(By.xpath("//div[@id='content']/p[3]")).getText();
		String actualProperties4=driver.findElement(By.xpath("//div[@id='content']/p[4]")).getText();
		
		Assert.assertEquals(actualProperties1, expectedProperDetails1);
		Assert.assertEquals(actualProperties2, expectedProperDetails2);
		Assert.assertEquals(actualProperties3, expectedProperDetails3);
		Assert.assertEquals(actualProperties4, expectedProperDetails4);
		
		driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
		
		By account_information_element=By.xpath("//a[text()='Edit your account information']");
		
		Assert.assertTrue(driver.findElement(account_information_element).isDisplayed());
		
		driver.quit();
	}
	
	public String getDate() {
		
		Date date=new Date();
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyyyy HH:mm:ss");
		
		String dateWithFormat=dateFormat.format(date);
		
		String dateWithNoSapce=dateWithFormat.replaceAll(" ", "");
		String dateWithNoColons=dateWithNoSapce.replaceAll(":", "");
		return dateWithNoColons;
	}
	
	
}
