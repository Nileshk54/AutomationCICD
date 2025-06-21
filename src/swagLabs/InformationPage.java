package swagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InformationPage extends BaseTestSwagLabs{

	WebDriver driver;
	public InformationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	By firstNameElement=By.id("first-name");
	By lastNameElement=By.id("last-name");
	By pincodeElement=By.id("postal-code");
	By continueElement=By.xpath("//input[@value='CONTINUE']");
	
	public OverViewPage details(String firstName,String lastName,String pinCode) {
		driver.findElement(firstNameElement).sendKeys(firstName);
		driver.findElement(lastNameElement).sendKeys(lastName);
		driver.findElement(pincodeElement).sendKeys(pinCode);
		driver.findElement(continueElement).click();
		return new OverViewPage(driver);
	}
	
	public void details() {
		driver.findElement(continueElement).click();
		String message=driver.findElement(By.tagName("h3")).getText();
		String firstNameValidation=message.split(":")[1].trim();
		Assert.assertEquals(firstNameValidation,expectedFirstNameValidation );
	}
	
	public void detailsFirstNameOnly(String firstName) {
		driver.findElement(firstNameElement).sendKeys(firstName);
		driver.findElement(continueElement).click();
		String message=driver.findElement(By.tagName("h3")).getText();
		String lastNameValidation=message.split(":")[1].trim();
		Assert.assertEquals(lastNameValidation,expectedLastNameValidation );
		
	}
	
	public void detailsFirstNameLastName(String firstName,String lastName) {
		driver.findElement(firstNameElement).sendKeys(firstName);
		driver.findElement(lastNameElement).sendKeys(lastName);
		driver.findElement(continueElement).click();
		String message=driver.findElement(By.tagName("h3")).getText();
		String pinCodeValidation=message.split(":")[1].trim();
		Assert.assertEquals(pinCodeValidation,expectedPinCodeValidation );
		
	}
	
}
