package orangeHrm;

import org.testng.annotations.Test;

public class LoginPageTestCases extends OrangeHrmBaseTest{

	@Test
	public void login() {
		LoginPage login=new LoginPage(driver);
		login.login();
		
	}
	
	@Test
	public void invalidLogin() {
		LoginPage login=new LoginPage(driver);
		login.invalidLogin();
	}
	
	@Test
	public void usernameValidation() {
		LoginPage login=new LoginPage(driver);
		login.userNameValidation();
	}
	
	@Test
	public void passwordValidation() {
		LoginPage login=new LoginPage(driver);
		login.passwordValidation();
	}
	
	@Test
	public void bothUsernameAndValidation() {
		LoginPage login=new LoginPage(driver);
		login.bothUsernameAndPasswordValidation();
	}
	
}
