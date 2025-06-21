package swagLabs;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestSwagLabs {

	static WebDriver driver;
	static String browser;
	static String URL;
	static String userName;
	static String password;
	static String productName;
	static String firstName;
	static String lastName;
	static String pinCode;
	static String thankYouMessage;
	
	static String productNameOne;
	static String productNameTwo;
	static String productNameThree;
	static String expectedUserNameValidation;
	static String expectedPassWordValidation;
	static String expectedInvalidUserNameAndPasswordValidation;
	static String expectedFirstNameValidation;
	static String	expectedLastNameValidation;
	static String expectedPinCodeValidation;
	
	@BeforeMethod
	public void launchApplication() throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\AutomationProject\\src\\swagLabs\\data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		 browser=prop.getProperty("browser");
		 URL=prop.getProperty("URL");
		 userName=prop.getProperty("userName");
		 password=prop.getProperty("password");
		 productName=prop.getProperty("productName");
		 firstName=prop.getProperty("firstName");
		 lastName=prop.getProperty("lastName");
		 pinCode=prop.getProperty("pinCode");
		 thankYouMessage=prop.getProperty("thankYouMessage");
		 productNameOne=prop.getProperty("productNameOne");
		 productNameTwo=prop.getProperty("productNameTwo");
		 productNameThree=prop.getProperty("productNameThree");
		 expectedUserNameValidation=prop.getProperty("expectedUserNameValidation");
		 expectedPassWordValidation=prop.getProperty("expectedPassWordValidation");
		 expectedInvalidUserNameAndPasswordValidation=prop.getProperty("expectedInvalidUserNameAndPasswordValidation");
		 expectedFirstNameValidation=prop.getProperty("expectedFirstNameValidation");
		 expectedLastNameValidation=prop.getProperty("expectedLastNameValidation");
		 expectedPinCodeValidation=prop.getProperty("expectedPinCodeValidation");
		 
		 
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromiumdriver().setup();
			ChromeOptions chromeOpt=new ChromeOptions();
			chromeOpt.addArguments("---incognito");
			driver=new ChromeDriver(chromeOpt);
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOpt=new EdgeOptions();
			edgeOpt.addArguments("---incognito");
			driver=new EdgeDriver(edgeOpt);
		}else {
			WebDriverManager.firefoxdriver().setup();

			driver=new FirefoxDriver();
		}
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	@AfterMethod
	public void closeApplication() {
		driver.quit();
	}
	
}
