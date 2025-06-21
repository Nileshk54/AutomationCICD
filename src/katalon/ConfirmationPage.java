package katalon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ConfirmationPage extends KatalonBaseTest{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver){
		this.driver=driver;
	}
	
	By actualMessageElement=By.tagName("h2");
	By expectedFacilityElement=By.id("facility");
	By expectedhospital_readmission_element=By.id("hospital_readmission");
	By expectedProgramElement=By.id("program");
	By expectedvisit_date_element=By.id("visit_date");
	By expectedCommentElement=By.id("comment");
	By toggleMenuElement=By.xpath("//a[@id='menu-toggle']");
	By logoutElement=By.xpath("//a[text()='Logout']");	
	
	public void assertionsCheck() {
		String actualMessage = driver.findElement(actualMessageElement).getText();
		Assert.assertEquals(KatalonBaseTest.expectedMessage, actualMessage);

		String expectedFacility=driver.findElement(expectedFacilityElement).getText();
		Assert.assertTrue(KatalonBaseTest.expectedMessage.contains(actualMessage));
		
		String expectedhospital_readmission=driver.findElement(expectedhospital_readmission_element).getText();
		Assert.assertEquals(expectedhospital_readmission, KatalonBaseTest.readmission);
		
		String expectedProgram=driver.findElement(expectedProgramElement).getText();
		Assert.assertEquals(expectedProgram, KatalonBaseTest.program);
		
		String expectedvisit_date=driver.findElement(expectedvisit_date_element).getText();
		System.out.println("expectedvisit_date "  + expectedvisit_date	) ;
		
		String expectedComment=driver.findElement(expectedCommentElement).getText();
		Assert.assertEquals(expectedComment, KatalonBaseTest.comment);
		//logout();
		
	}
	
	public void logout() {
		explicitWaitToAppear(toggleMenuElement);
		driver.findElement(toggleMenuElement).click();
		explicitWaitToAppear(logoutElement);
		driver.findElement(logoutElement).click();
		
	}
	
}
