package swagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BaseTestSwagLabs{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	By userNameElement=By.id("user-name");
	By passwordElement=By.id("password");
	By loginButtonElement=By.id("login-button");
	
	public ProductPage login(String user,String pass) {
		driver.findElement(userNameElement).sendKeys(user);
		driver.findElement(passwordElement).sendKeys(pass);
		driver.findElement(loginButtonElement).click();
		return new ProductPage(driver);
	}
	
	public void userNameValidation() throws InterruptedException {
		driver.findElement(loginButtonElement).click();
		String message=driver.findElement(By.tagName("h3")).getText();
		String userNameValidationMessage=message.split(":")[1].trim();
		Assert.assertEquals(userNameValidationMessage,expectedUserNameValidation );
		
	}
	
	public void passwordValidation(String user) throws InterruptedException {
		driver.findElement(userNameElement).sendKeys(user);
		driver.findElement(loginButtonElement).click();
		String message=driver.findElement(By.tagName("h3")).getText();
		String passwordValidationMessage=message.split(":")[1].trim();
		Assert.assertEquals(passwordValidationMessage,expectedPassWordValidation );
		
	}
	
	public void invalidUserNameAndPassword(String username,String password) throws InterruptedException {
		driver.findElement(userNameElement).sendKeys(username);
		driver.findElement(passwordElement).sendKeys(password);
		driver.findElement(loginButtonElement).click();
		String message=driver.findElement(By.tagName("h3")).getText();
		String userNameAndPasswordValidationMessage=message.split(":")[1].trim();
		Assert.assertEquals(userNameAndPasswordValidationMessage,expectedInvalidUserNameAndPasswordValidation );
		
	}
	

	
	
}
