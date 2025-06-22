package orangeHrm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class AdminPageTestCases extends OrangeHrmBaseTest{

	@Test(enabled = false)
	public void adminUserManagementUserRecords() {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.getAllUserRecords();
	}
	
	@Test(dataProvider = "getUserNameData",enabled = false)
	public void searchByUsername(String userName) {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.searchByUserName(userName);
	}
	
	@Test(enabled = false)
	public void searchByUserRole() throws InterruptedException {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.searchByUserRole();
	}
	
	@Test(dataProvider = "getEmployeeDate",enabled = false)
	public void searchByEmployeeName(String employeeName) {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.searchByEmployeeName(employeeName);
	}
	
	@Test(enabled = false)
	public void searchByStatus() throws InterruptedException {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.searchByStatus();
	}
	
	@Test(dataProvider = "editUserData",enabled = false)
	public void editUser(String editUserName) {
		LoginPage login=new LoginPage(driver);
		AdminUserManagement adminPage=login.loginUser();
		adminPage.editUser(editUserName);
	}
	
	@Test(dataProvider = "changePasswordData",enabled=false)
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
	public Object[][] getUserNameData() {
		return new Object[][] {
			{"admin"},{"Nilesh"},{"Khalate"}
		};
	}
	
	@DataProvider
	public Object[][] getEmployeeDate(){
		return new Object[][] {
			{"Jobin"},{"Kenny"},{"Shany"}
		};
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
