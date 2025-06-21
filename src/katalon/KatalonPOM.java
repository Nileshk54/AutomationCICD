package katalon;

import java.io.IOException;

import org.testng.annotations.Test;

public class KatalonPOM extends KatalonBaseTest{

	@Test
	public void addAppointment() throws IOException, InterruptedException {
		
		MakeAppointmentPage appointment=new MakeAppointmentPage(driver);
		LoginPage loginPage=appointment.makeAppointmentButton();
		BookAppointment bookAppointment=loginPage.validLogin();
		ConfirmationPage confirmPage=bookAppointment.bookAppointment();
		confirmPage.assertionsCheck();
		
	}
	
	@Test
	public void invalidLogin() {
		MakeAppointmentPage appointment=new MakeAppointmentPage(driver);
		LoginPage loginPage=appointment.makeAppointmentButton();
		loginPage.inValidLogin();
	}
	
}
