package katalon;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KatalonBaseTest {

	static String facilityName;
	static String program ;
	static String date ;
	static String comment;
	static String expectedMessage ;
	static String readmission ;
	static String invalidLoginMessage;
	static String browserName;
	static WebDriver driver;
	static String URL;
	static String inValidUserName;
	static String inValidPassword;
	
	@BeforeMethod
	public void launchApplication() throws IOException {
		
		System.out.println("Before TEst");
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\AutomationProject\\src\\dataProperties\\katalonData.dataProperties");
		
		Properties prop=new Properties();
		
		prop.load(fis);
		
		browserName=prop.getProperty("browserName");
		facilityName=prop.getProperty("facilityName");
		program=prop.getProperty("program");
		date=prop.getProperty("date");
		comment=prop.getProperty("comment");
		expectedMessage=prop.getProperty("expectedMessage");
		readmission=prop.getProperty("readmission");
		invalidLoginMessage=prop.getProperty("invalidLoginMessage");
		URL=prop.getProperty("URL");
		
		inValidUserName=prop.getProperty("inValidUserName");
		inValidPassword=prop.getProperty("inValidPassword");
		
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--incognito");
			driver=new ChromeDriver(opt);
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt=new EdgeOptions();
			opt.addArguments("--incognito");
			driver=new EdgeDriver(opt);
		}else {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions opt=new FirefoxOptions();
			opt.addArguments("--incognito");
			driver=new FirefoxDriver(opt);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(URL);
		
		//return driver;
		
	}
	
	public void explicitWaitToAppear(By locator) {
		WebDriverWait explicitWait=new WebDriverWait(driver, Duration.ofSeconds(10));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	@AfterMethod
	public  void closeAplication() {
		driver.quit();
	}
	
}
