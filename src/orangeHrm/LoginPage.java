package orangeHrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import bsh.commands.dir;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage extends OrangeHrmBaseTest{

	static WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//input[@name='username']" ) 
	private WebElement userNameElement;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordElement;
	
	@FindBy(xpath = "//button[contains(@class,'oxd-button')]")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'oxd-sheet')]/p[1]")
	private WebElement getUserNameElement;
	
	@FindBy(xpath = "//div[contains(@class,'oxd-sheet')]/p[2]")
	private WebElement getPasswordElement;
	
	@FindBy(xpath = "//div[@role='alert']//p")
	private WebElement invalidCredentialElement;
	
	@FindBy(xpath = "//span[contains(@class,'oxd-text')]")
	private WebElement ValidationElement;
	
	public String getUserName() {
		String usernameField=getUserNameElement.getText();
		String username=usernameField.split(":")[1].trim();
		return username;
	}
	
	public String getPassword() {
		String passwordField=getPasswordElement.getText();
		String password=passwordField.split(":")[1].trim();
		return password;
	}
	
	
	
	public AdminPageRecordFiltration login() {
		String username=getUserName();
		String password=getPassword();
		userNameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		loginButton.click();
		return new AdminPageRecordFiltration(driver);
	}
	
	public AdminUserManagement loginUser() {
		String username=getUserName();
		String password=getPassword();
		userNameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		loginButton.click();
		System.out.println("Login Done");
		return new AdminUserManagement(driver);
	}
	
	public void invalidLogin() {
		String username=getUserName();
		
		userNameElement.sendKeys(username);
		passwordElement.sendKeys(username);
		loginButton.click();
		
		String alertText=invalidCredentialElement.getText();
		Assert.assertEquals("Invalid credentials", alertText);
	}
	
	public void userNameValidation() {
		String password=getPassword();
		
		passwordElement.sendKeys(password);
		loginButton.click();
		String usernameValidationText=ValidationElement.getText();
		Assert.assertEquals(usernameValidationText, "Required");
	}
	
	public void passwordValidation() {
		String username=getUserName();
		userNameElement.sendKeys(username);
		loginButton.click();
		String passwordValidationText=ValidationElement.getText();
		Assert.assertEquals(passwordValidationText, "Required");
	}
	
	public void bothUsernameAndPasswordValidation() {
		loginButton.click();
		String usernameValidationText=ValidationElement.getText();
		Assert.assertEquals(usernameValidationText, "Required");
		String passwordValidationText=ValidationElement.getText();
		Assert.assertEquals(passwordValidationText, "Required");
	}
	
	public void userLogin(String username,String password) {
		userNameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		loginButton.click();
	}
	
}
