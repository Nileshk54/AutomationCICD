package orangeHrm;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AdminUserManagement extends OrangeHrmBaseTest {

	WebDriver driver;

	public AdminUserManagement(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement goToAdminElement;

	@FindBy(xpath = "//span[text()='PIM']")
	private WebElement goToPIMElement;

	@FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]//span")
	private WebElement resultsFoundElement;

	@FindBy(xpath = "//div[@class='oxd-table-filter']//input[contains(@class,'oxd-input')]")
	private WebElement searchByUserNameElement;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButtonElement;

	@FindBy(xpath = "//div[@class='oxd-table-cell-actions']//button[2]")
	private WebElement editButtonElement;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement employeeNameElement;

	@FindBy(xpath = "//label[text()='Username']//following::input[contains(@class,'oxd-input')]")
	private WebElement usernameElement;

	@FindBy(xpath = "//div[contains(@class,'oxd-grid-item')][1]//div[@class='oxd-select-text-input']")
	private WebElement userRoleElement;

	@FindBy(xpath = "//div[contains(@class,'oxd-grid-item')][3]//div[@class='oxd-select-text-input']")
	private WebElement statusElement;

	@FindBy(xpath = "//span[contains(@class,'oxd-checkbox-input')]")
	private WebElement changePasswordCheckboxElement;

	@FindBy(xpath = "//div[@class='oxd-form-row user-password-row']//following::input[1]")
	private WebElement passwordElement;

	@FindBy(xpath = "//div[@class='oxd-form-row user-password-row']//following::input[2]")
	private WebElement confirmPasswordElement;

	@FindBy(xpath = "//span[contains(@class,'orangehrm-password-chip')]")
	private WebElement passwordStrengthElement;

	@FindBy(xpath = "//span[contains(@class,'oxd-input-field-error-message')]")
	private WebElement errorMessageElement;

	@FindBy(xpath = "//button[text()=' Save ']")
	private WebElement saveButtonElement;

	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	private WebElement userTabElement;

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutButtonElement;

	@FindBy(xpath = "//i[contains(@class,'bi-chevron-right')]")
	private WebElement nextButtonElement;

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement firstNameElement;
	
	@FindBy(xpath = "//input[@name='middleName']")
	private WebElement middleNameElement;
	
	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement lastNameElement;
	
	// Base Methods
	public void goToAdmin() {
		goToAdminElement.click();
	}

	public void goToPIM() {
		goToPIMElement.click();
	}
	
	
	//edit users
	public void editUser(String username) {
		goToAdmin();
		AdminPageRecordFiltration admin = new AdminPageRecordFiltration(driver);
		int count = admin.searchByUserNameAndGetCount(username);
		if (count == 1) {
			List<String> allUserDetailsList = getInformation();
			editButtonElement.click();
			checkDetails(allUserDetailsList);
		}
	}
	
	public List<String> getInformation() {
		List<String> getInfo = new ArrayList<String>();
		for (int i = 2; i <= 5; i++) {
			getInfo.add(driver.findElement(By.xpath("//div[@role='cell'][" + i + "]/div")).getText());

		}
		// System.out.println(getInfo);
		return getInfo;
	}
	
	public void checkDetails(List<String> getUserInformation) {
		System.out.println(getUserInformation);

		// User name Check
		usernameElement.click();
		String userName = usernameElement.getAttribute("value");
		Assert.assertEquals(userName, getUserInformation.get(0));

		// Employee Name Check

		employeeNameElement.click();
		String editedString = employeeNameElement.getAttribute("value");
		// System.out.println(editedString + "editedString ");
		String editedName[] = editedString.split(" ");
		String editedFirstName = editedName[0];
		String editedLastName = editedName[editedName.length - 1];

		// System.out.println(" editedFirstName " + editedFirstName);
		// System.out.println(" editedLastName " + editedLastName);

		String actualString = getUserInformation.get(2);
		// System.out.println(actualString +" actualString");
		String actualName[] = actualString.split(" ");
		String actualFirstName = actualName[0];
		String actualLastName = actualName[1];
		// System.out.println(actualName[0] + "actualName[0] ");
		// System.out.println(actualName[1] + "actualName[1] ");

		// System.out.println(" actualFirstName " + actualFirstName);
		// System.out.println(" actualLastName " + actualLastName);

		Assert.assertTrue(actualFirstName.equals(editedFirstName));
		Assert.assertTrue(actualLastName.equals(editedLastName));

		// user Role Check

		Assert.assertEquals(userRoleElement.getText(), getUserInformation.get(1));

		// Status check

		Assert.assertEquals(statusElement.getText(), getUserInformation.get(3));

	}

	// change password
	public int changePassword(String username, String password) throws InterruptedException {
		int count = getEditCount(username);
		if (count == 1) {
			changePasswordCheckboxElement.click();
			passwordElement.sendKeys(password);
			Thread.sleep(2000);
			String strength = passwordStrengthElement.getText();
			if (strength.equalsIgnoreCase("strong")) {
				confirmPasswordElement.sendKeys(password);
				saveButtonElement.click();
				userTabElement.click();
				logoutButtonElement.click();
				Thread.sleep(5000);
				System.out.println("Password Changed For User " + username + " New Password : " + password);
			} else {
				System.out.println(strength + " Password");
				// System.out.println(errorMessageElement.getText());
				count = 0;
			}
		}

		return count;

	}

	public int getEditCount(String username) {
		goToAdmin();
		AdminPageRecordFiltration admin = new AdminPageRecordFiltration(driver);
		int count = admin.searchByUserNameAndGetCount(username);
		if (count == 1) {
			List<String> allUserDetailsList = getInformation();
			editButtonElement.click();
			checkDetails(allUserDetailsList);
		} else {

			count = 0;
		}

		return count;
	}

	// All Create User Imp Method
	
	public void createUser(String userid) throws InterruptedException {
		goToPIMElement.click();
		int count = getResultsCount(resultsFoundElement.getText());
		boolean flag=forLoop(count,userid);
		Thread.sleep(2000);
		//System.out.println(flag);
		if(flag==true) {
			getEmployeeName();
		}
		
	}
	
	public void getEmployeeName() {
		String fullName="";
		firstNameElement.click();
		fullName=firstNameElement.getAttribute("value");
		middleNameElement.click();
		if(middleNameElement.getAttribute("value").equals(" ")) {
			
		}else {
			fullName=fullName+" "+middleNameElement.getAttribute("value");
		}
		System.out.println(middleNameElement.getAttribute("value"));
		lastNameElement.click();
		fullName=fullName+" "+lastNameElement.getAttribute("value");
		System.out.println(fullName + "fullName ");
	}

	
	
	public boolean forLoop(int outerloop,String userid) throws InterruptedException {
		System.out.println(outerloop);
		int remainder=outerloop%50;
		int outerLoopValue=outerloop/50;
		System.out.println("remainder :" +remainder);
		boolean flag=false;
		if(remainder!=0) {
			outerLoopValue=outerLoopValue+1;
		}
		
		int innerLoop=0;
		innerLoop=(outerloop>50)?50:remainder;
		int number=50;
		for(int i=1;i<=outerLoopValue;i++) {
			//System.out.println(" innerLoop " + innerLoop);
			for(int j=1;j<=innerLoop;j++) {
				String text=driver.findElement(By.xpath("//div[@class='oxd-table-card'][" + j + "]//div[contains(@class,'oxd-table-cell')][2]/div")).getText();
				//System.out.println(text + " " + j  );
				if(text.equalsIgnoreCase(userid)) {
					flag=true;
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='oxd-table-card']["+j+"]//div[contains(@class,'oxd-table-cell')][9]//button[1]")).click();
					return flag;
				}
				if(j==50) {
					number=number+50;
					if(number>outerloop) {
						innerLoop=outerloop-(50*i);
						nextButtonElement.click();
						scrollUp();
					}else {
						nextButtonElement.click();
						scrollUp();
					}
				}
				
			}
		}
		
		if(flag==false) {
			System.out.println("No User Found");
		}
		
		return flag;
	}

	public int getResultsCount(String text) {
		String splitText[] = text.split(" ");
		String check = splitText[0].replaceAll("\\(", "").replaceAll("\\)", "");
		int number = Integer.parseInt(check);
		return number;
	}



}
