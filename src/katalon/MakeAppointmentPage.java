package katalon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MakeAppointmentPage {

	WebDriver driver;
	
	public MakeAppointmentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	By MakeAppointmentElement=By.xpath("//div[@class='text-vertical-center']/child::a");
	
	public LoginPage makeAppointmentButton() {
		driver.findElement(MakeAppointmentElement).click();
		return new LoginPage(driver);
	}
	
	
	
	
}
