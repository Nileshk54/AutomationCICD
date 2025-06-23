package orangeHrm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdminPageUserManagementTestCases extends OrangeHrmBaseTest{
	
	
	
	@Test(dataProvider = "editUserData")
	public void editUser(String editUserName) {
		LoginPage login=new LoginPage(driver);
		AdminUserManagement adminPage=login.loginUser();
		adminPage.editUser(editUserName);
	}
	
	@Test(dataProvider = "changePasswordData")
	public void changePassword(String username,String password) throws InterruptedException {
		LoginPage login=new LoginPage(driver);
		AdminUserManagement adminPage=login.loginUser();
		int count=adminPage.changePassword(username,password);
		if(count==1) {
			login.userLogin(username,password);
		}
	
	}
	
	@Test
	public void createUser() throws InterruptedException {
		LoginPage login=new LoginPage(driver);
		AdminUserManagement adminPage=login.loginUser();
		adminPage.createUser("0313");
	}
	
	
	
	
	
	
	@DataProvider
	public Object[][] editUserData(){
		return new Object[][] {
			{"PetMac"},{"samprit"},{"Nilesh"},{"laura perez"}
		};
	}
	
	@DataProvider 
	public Object[][] changePasswordData(){
		return new Object[][] {
			{"satya_nadella","Nileshk_54"},
			{"ranga_user_9492","Admin@123"},
			{"Nilesh","Nilesh"},
			{"Jobinsam@6742","Admin@123"}
		};
	}
}
