package katalon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends KatalonBaseTest{

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	By getUserNameElement=By.xpath("//span[@id='demo_username_label']/following-sibling::input");
	By getPasswordElement=By.xpath("//span[@id='demo_password_label']/following-sibling::input");

	By userNameElement=By.id("txt-username");
	By passwordElement=By.id("txt-password");
	By loginButtonElement=By.id("btn-login");
	By invalidMessageElement=By.xpath("//p[@class='lead text-danger']");
	
	public BookAppointment validLogin() {
		String username=driver.findElement(getUserNameElement).getAttribute("value");
		String passWord =driver.findElement(getPasswordElement).getAttribute("value");
		
		driver.findElement(userNameElement).sendKeys(username);
		driver.findElement(passwordElement).sendKeys(passWord);
		driver.findElement(loginButtonElement).click();
		
		return new BookAppointment(driver);
	}
	
	public void inValidLogin() {
		driver.findElement(userNameElement).sendKeys(inValidUserName);
		driver.findElement(passwordElement).sendKeys(inValidPassword);
		driver.findElement(loginButtonElement).click();
		
		String expectedInvalidLoginMessage=driver.findElement(invalidMessageElement).getText();
		Assert.assertTrue(expectedInvalidLoginMessage.contains(invalidLoginMessage));
		
		
	}
	
	
}
