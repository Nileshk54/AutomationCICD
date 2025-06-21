package orangeHrm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import bsh.commands.dir;

public class AdminPageRecordFiltration extends OrangeHrmBaseTest{

	WebDriver driver;

	public AdminPageRecordFiltration(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement goToAdminElement;

	@FindBy(xpath = "//div[@class='orangehrm-paper-container']//span")
	private WebElement resultsFoundElement;
	
	By byResultsFoundElement=By.xpath("//div[@class='orangehrm-paper-container']//span");

	@FindBy(xpath = "//div[@class='oxd-table-card']")
	private List<WebElement> allUserLists;

	@FindBy(xpath = "//div[@class='oxd-table-filter']//input[contains(@class,'oxd-input')]")
	private WebElement searchByUsernameElement;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButtonElement;

	@FindBy(xpath = "//label[text()='User Role']/following::div")
	private WebElement userRoleDropdownElement;

	@FindBy(xpath = "//div[@role='listbox']//span[text()='Admin']")
	private WebElement userRoleAdminDropdownElement;

	@FindBy(xpath = "//div[@role='listbox']//span[text()='ESS']")
	private WebElement userRoleEssDropdownElement;

	@FindBy(xpath = "//div[@class='oxd-form-actions']/button[1]")
	private WebElement resetButtonElement;

	@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']//input")
	private WebElement searchByEmployeeNameElement;

	@FindBy(xpath = "//label[text()='Status']/following::div")
	private WebElement statusDropdownElement;
	
	@FindBy(xpath = "//label[text()='Employee Name']//following::input")
	private WebElement fullEmployeeNameElement;
	
	@FindBy(xpath = "//div[@class='oxd-form-actions']//button[1]")
	private WebElement cancelButtonElement;
	
	
	

	public int adminUserManagement() {
		goToAdminElement.click();
		String text = resultsFoundElement.getText();
		String stringNumber = text.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
		int number = Integer.parseInt(stringNumber);
		return number;
	}

	public void getAllUserRecords() {
		int number = adminUserManagement();
		Assert.assertEquals(number, allUserLists.size());
		getAllUserNameList(number);
		getAllUserRolesList(number);
		getAllEmployeeNameList(number);
		getAllUserStatusList(number);

		System.out.println("Total Users :" + number);
	}

	public List<String> getAllUserNameList(int number) {
		List<String> allUsername = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			String userName = driver
					.findElement(By.xpath(
							"//div[@class='oxd-table-card'][" + i + "]//div[contains(@class,'oxd-table-cell')][2]"))
					.getText();
			allUsername.add(userName);
		}

		return allUsername;

	}

	public List<String> getAllUserRolesList(int number) {
		List<String> allUserRole = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			String userRole = driver
					.findElement(By.xpath(
							"//div[@class='oxd-table-card'][" + i + "]//div[contains(@class,'oxd-table-cell')][3]"))
					.getText();
			allUserRole.add(userRole);
		}

		return allUserRole;

	}

	public List<String> getAllEmployeeNameList(int number) {
		List<String> allEmployeeName = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			String employeeName = driver
					.findElement(By.xpath(
							"//div[@class='oxd-table-card'][" + i + "]//div[contains(@class,'oxd-table-cell')][4]"))
					.getText();
			allEmployeeName.add(employeeName);
		}
		return allEmployeeName;
	}

	public List<String> getAllUserStatusList(int number) {
		List<String> allUserStatus = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			String userStatus = driver
					.findElement(By.xpath(
							"//div[@class='oxd-table-card'][" + i + "]//div[contains(@class,'oxd-table-cell')][5]"))
					.getText();
			allUserStatus.add(userStatus);
		}

		return allUserStatus;
	}

	public void searchByUserName(String username) {
		int actualcount = 0;

		int number = adminUserManagement();
		List<String> userNameList = getAllUserNameList(number);
		int expectedCount = 0;
		for (int i = 0; i < number; i++) {
			if (userNameList.get(i).equalsIgnoreCase(username)) {
				expectedCount++;
			}
		}
		searchByUsernameElement.sendKeys(username);
		searchButtonElement.click();
		String text = resultsFoundElement.getText();
		if (text.split(" ")[0].equalsIgnoreCase("no")) {
			int actutalCount = 0;
			System.out.println("No Records found for user " + username);
			Assert.assertEquals(expectedCount, actutalCount);
		} else {
			String stringNumber = text.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
			actualcount = Integer.parseInt(stringNumber);

			Assert.assertEquals(expectedCount, actualcount);
		}

		System.out.println("Actual UserName Count For " + username + " : " + actualcount);
		System.out.println("Expected Username Count For " + username + " : " + expectedCount);
	}

	public void searchByUserRole() throws InterruptedException {
		int expectedAdminCount = 0;
		int expectedEssCount = 0;

		int number = adminUserManagement();
		System.out.println("Total Users Count : " + number);
		List<String> userRoleList = getAllUserRolesList(number);

		for (int i = 0; i < number; i++) {
			if (userRoleList.get(i).equalsIgnoreCase("admin")) {
				expectedAdminCount++;

			} else if (userRoleList.get(i).equalsIgnoreCase("ess")) {
				expectedEssCount++;
			}
		}

		userRoleDropdownElement.click();
		userRoleAdminDropdownElement.click();
		searchButtonElement.click();

		String text1 = resultsFoundElement.getText();
		String stringNumber1 = text1.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
		int actualAdmincount = Integer.parseInt(stringNumber1);

		Assert.assertEquals(actualAdmincount, expectedAdminCount);
		resetButtonElement.click();
		Thread.sleep(1000);
		// select ess from dropdown
		userRoleDropdownElement.click();
		userRoleEssDropdownElement.click();
		searchButtonElement.click();

		String text2 = resultsFoundElement.getText();
		String stringNumber2 = text2.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
		int actualEsscount = Integer.parseInt(stringNumber2);

		Assert.assertEquals(actualEsscount, expectedEssCount);

		System.out.println("Expected Admin Role Count :" + expectedAdminCount);
		System.out.println("Expected ESS Role Count :" + expectedEssCount);
		System.out.println("Actual Admin Role Count :" + actualAdmincount);
		System.out.println("Actual ESS Role Count :" + actualEsscount);
	}

	public void searchByStatus() throws InterruptedException {
		int number = adminUserManagement();
		int actutalEnabledCount = 0;
		int actutalDisabledCount = 0;
		int expectedEnabledCount = 0;
		int expectedDisabledCount = 0;

		List<String> statusList = getAllUserStatusList(number);
		for (int i = 0; i < number; i++) {
			if (statusList.get(i).equalsIgnoreCase("enabled")) {
				expectedEnabledCount++;
			} else {
				expectedDisabledCount++;
			}
		}

		statusDropdownElement.click();
		List<WebElement> actualStatus = driver.findElements(By.xpath("//div[@class='oxd-select-wrapper']//span"));
		List<String> statusValues = new ArrayList<>();
		for (WebElement singleStatus : actualStatus) {
			statusValues.add(singleStatus.getText());
		}

		for (String value : statusValues) {
			driver.findElement(By.xpath("//div[@class='oxd-select-wrapper']//span[text()='" + value + "']")).click();
			searchButtonElement.click();
			if (value.equalsIgnoreCase("enabled")) {
				String text = resultsFoundElement.getText();
				if (text.split(" ")[0].equalsIgnoreCase("no")) {
					System.out.println("No Records found for Status " + value);
					Assert.assertEquals(expectedEnabledCount, actutalEnabledCount);
				} else {
					String stringNumber = text.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
					actutalEnabledCount = Integer.parseInt(stringNumber);
					Assert.assertEquals(expectedEnabledCount, actutalEnabledCount);
					resetButtonElement.click();
					statusDropdownElement.click();
					Thread.sleep(500);
				}
			}

			if (value.equalsIgnoreCase("disabled")) {

				String text = resultsFoundElement.getText();
				if (text.split(" ")[0].equalsIgnoreCase("no")) {

					System.out.println("No Records found for Status " + value);
					Assert.assertEquals(expectedDisabledCount, actutalDisabledCount);
				} else {
					String stringNumber = text.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
					actutalDisabledCount = Integer.parseInt(stringNumber);

					Assert.assertEquals(expectedDisabledCount, actutalDisabledCount);
				}

			}

		}

		System.out.println("expectedEnabledCount :" + expectedEnabledCount);
		System.out.println("expectedDisabledCount :" + expectedDisabledCount);
		System.out.println("actutalEnabledCount :" + actutalEnabledCount);
		System.out.println("actutalDisabledCount :" + actutalDisabledCount);
	}
	
	public void searchByEmployeeName(String name) {
		int expectedEmployeeCount=0;
		int actutalEmployeeCount = 0;
		
		int number = adminUserManagement();
		System.out.println("Total Users : " + number);
		List<WebElement> allEditButton=driver.findElements(By.xpath("//div[@class='oxd-table-card']//button[2]"));
		HashMap<String, Integer> hashmap=new HashMap<String, Integer>();
		System.out.println(hashmap);
		for(int i=1;i<=allEditButton.size();i++) {
			driver.findElement(By.xpath("//div[@class='oxd-table-card']["+i+"]//button[2]")).click();
			
			fullEmployeeNameElement.click();
			String value=fullEmployeeNameElement.getAttribute("value");
			//System.out.println("value"+i+" :" + value);
			if(hashmap.containsKey(value)) {
				hashmap.put(value, hashmap.get(value)+1);
			}else {
				hashmap.put(value, 1);
			}
			cancelButtonElement.click();
		}
		

		for(Map.Entry<String, Integer> map :hashmap.entrySet()) {
			if(map.getKey().contains(name)) {
			//	System.out.println("Found " + name);
				expectedEmployeeCount=map.getValue();
			}
		}
		
		System.out.println(expectedEmployeeCount);
		
		searchByEmployeeNameElement.sendKeys(name);
		
		List<WebElement> allActualNames=driver.findElements(By.xpath("//div[@role='listbox']//span"));
		
		for(WebElement singleName:allActualNames) {
			if (singleName.getText().contains(name)) {
				singleName.click();
			}
		}
		
		
		searchButtonElement.click();
		
		String text = resultsFoundElement.getText();
		if (text.split(" ")[0].equalsIgnoreCase("no")) {
			
			System.out.println("No Records found for Employee " + name);
			Assert.assertEquals(actutalEmployeeCount, expectedEmployeeCount);
		} else {
			String stringNumber = text.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
			actutalEmployeeCount = Integer.parseInt(stringNumber);

			Assert.assertEquals(actutalEmployeeCount, expectedEmployeeCount);
		}
		
		
		System.out.println("actutalEmployeeCount :" + actutalEmployeeCount  );
		System.out.println("expectedEmployeeCount :" + expectedEmployeeCount);
	}
	
	
	public int searchByUserNameAndGetCount(String username) {
		String beforeText=resultsFoundElement.getText();
		//System.out.println(" beforeText " + beforeText);
		searchByUsernameElement.sendKeys(username);
		searchButtonElement.click();
		explicitWaitForNotTextToBe(byResultsFoundElement, beforeText);
		
		String text = resultsFoundElement.getText();
		//System.out.println(" text " + text);
		//System.out.println(text);
		int count=0;
		if (text.split(" ")[0].equalsIgnoreCase("no")) {
			
			System.out.println("No Records found for user " + username);
			
		} else {
			//System.out.println(count);
			String stringNumber = text.split(" ")[0].replaceAll("\\(", "").replaceAll("\\)", "");
			//System.out.println(stringNumber +" stringNumber ");
			count=Integer.parseInt(stringNumber);
			//System.out.println(count +" count ");
		}
		
		return count;

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
