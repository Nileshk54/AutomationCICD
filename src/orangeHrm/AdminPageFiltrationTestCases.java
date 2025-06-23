package orangeHrm;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class AdminPageFiltrationTestCases extends OrangeHrmBaseTest{

	@Test
	public void adminUserManagementUserRecords() {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.getAllUserRecords();
	}
	
	@Test(dataProvider = "getUserNameData")
	public void searchByUsername(String userName) {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.searchByUserName(userName);
	}
	
	@Test
	public void searchByUserRole() throws InterruptedException {
		LoginPage login=new LoginPage(driver);
		AdminPageRecordFiltration adminPage=login.login();
		adminPage.searchByUserRole();
	}
	
	@Test(dataProvider = "getEmployeeDate")
	public void searchByEmployeeName(String employeeName) throws InterruptedException {
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

	@DataProvider
	public Object[][] getUserNameData() {
		return new Object[][] {
			{"admin"},{"Nilesh"},{"Khalate"}
		};
	}
	
	@DataProvider
	public Object[][] getEmployeeDate(){
		return new Object[][] {
			{"Jobin"},{"Kenny"},{"Shany"},{"Ravi"}
		};
	}
	

}
