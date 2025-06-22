package orangeHrm;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHrmBaseTest {

	static WebDriver driver;

	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	@BeforeMethod
	public void launchApplication() {
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):"Chrome";
		System.out.println(browserName);
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		if(browserName.contains("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("Headless")) {
				options.addArguments("--headless=new"); // Use `new` mode for Chrome 109+
				options.addArguments("--window-size=1440,900"); // REQUIRED in headless
				options.addArguments("--disable-gpu"); // optional, good on Windows
			}
			driver = new ChromeDriver(options);
		
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void closeApplication() {
		driver.quit();
	}

	public static void explicitWaitForNotTextToBe(By element, String value) {

		wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(element, value)));
	}
	
	public static void scrollUp() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-3000)");
	}
	

}
