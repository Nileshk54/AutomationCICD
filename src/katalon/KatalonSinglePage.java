package katalon;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KatalonSinglePage {

	static String facilityName = "Hongkong";
	static String program = "Medicare";
	static String date = "2 jul 1999";
	static String comment = "Adding comment here";
	static String expectedMessage = "Appointment Confirmation";
	static String readmission = "No";
	static String invalidLoginMessage="Login failed!";

	@Test
	public void singlePageKatalon() throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.findElement(By.xpath("//div[@class='text-vertical-center']/child::a")).click();

		String userName = driver.findElement(By.xpath("//span[@id='demo_username_label']/following-sibling::input"))
				.getAttribute("value");

		String passWord = driver.findElement(By.xpath("//span[@id='demo_password_label']/following-sibling::input"))
				.getAttribute("value");

		driver.findElement(By.id("txt-username")).sendKeys(userName);
		driver.findElement(By.id("txt-password")).sendKeys(passWord);
		driver.findElement(By.id("btn-login")).click();

		WebElement selectDropdown = driver.findElement(By.id("combo_facility"));

		Select select = new Select(selectDropdown);
		select.selectByContainsVisibleText(facilityName);

		if (readmission.equalsIgnoreCase("yes")) {
			driver.findElement(By.id("chk_hospotal_readmission")).click();
		}

		if (program.equalsIgnoreCase("Medicare")) {
			driver.findElement(By.id("radio_program_medicare")).click();
		} else if (program.equalsIgnoreCase("Medicaid")) {
			driver.findElement(By.id("radio_program_medicaid")).click();
		} else {
			driver.findElement(By.id("radio_program_none")).click();
		}

		driver.findElement(By.id("txt_comment")).sendKeys(comment);

		// Date selection

		driver.findElement(By.id("txt_visit_date")).click();

		driver.findElement(By.xpath("//div[@class='datepicker-days']//tr[2]//th[2]")).click();
		driver.findElement(By.xpath("//div[@class='datepicker-months']//tr[2]//th[2]")).click();

		String dateArray[] = date.split(" ");

		String date = dateArray[0];
		String month = dateArray[1];
		int year = Integer.parseInt(dateArray[2]);

		boolean flag = false;

		do {

			String yearDecade = driver.findElement(By.xpath("//div[@class='datepicker-years']//tr[2]//th[2]"))
					.getText();
			String yearDecadeArray[] = yearDecade.split("-");
			int fromYear = Integer.parseInt(yearDecadeArray[0]);
			int toYear = Integer.parseInt(yearDecadeArray[1]);

			if (year >= fromYear && year <= toYear) {
				List<WebElement> yearsList = driver
						.findElements(By.xpath("//div[@class='datepicker-years']//child::span"));

				for (WebElement singleYears : yearsList) {
					int selectyear = Integer.parseInt(singleYears.getText());
					if (selectyear == year) {
						singleYears.click();
						break;
					}
				}

				flag = true;

			} else if (year > toYear) {
				driver.findElement(By.xpath("//div[@class='datepicker-years']//tr[2]//th[@class='next']")).click();
				Thread.sleep(500);
			} else {
				driver.findElement(By.xpath("//div[@class='datepicker-years']//tr[2]//th[@class='prev']")).click();
				Thread.sleep(500);
			}
		} while (flag == false);

		List<WebElement> monthsList = driver.findElements(By.xpath("//span[contains(@class,'month')]"));

		for (WebElement singleMonth : monthsList) {

			if (month.equalsIgnoreCase(singleMonth.getText())) {
				singleMonth.click();
				Thread.sleep(500);
				break;
			}
		}

		List<WebElement> datesList = driver
				.findElements(By.xpath("//div[@class='datepicker-days']//td[contains(text()," + date + ")]"));

		for (WebElement singleDate : datesList) {
			if (singleDate.getText().equalsIgnoreCase(date)) {
				singleDate.click();
				break;
			}
		}

		driver.findElement(By.id("btn-book-appointment")).click();

		String actualMessage = driver.findElement(By.tagName("h2")).getText();
		Assert.assertEquals(expectedMessage, actualMessage);
	
		String expectedFacility=driver.findElement(By.id("facility")).getText();
		Assert.assertTrue(expectedMessage.contains(actualMessage));
		
		String expectedhospital_readmission=driver.findElement(By.id("hospital_readmission")).getText();
		Assert.assertEquals(expectedhospital_readmission, readmission);
		
		String expectedProgram=driver.findElement(By.id("program")).getText();
		Assert.assertEquals(expectedProgram, program);
		
		String expectedvisit_date=driver.findElement(By.id("visit_date")).getText();
		System.out.println("expectedvisit_date "  + expectedvisit_date	) ;
		
		String expectedComment=driver.findElement(By.id("comment")).getText();
		Assert.assertEquals(expectedComment, comment);
		
		driver.quit();

	}
	
	@Test
	public void invalidLogin() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.findElement(By.xpath("//div[@class='text-vertical-center']/child::a")).click();
		
		driver.findElement(By.id("txt-username")).sendKeys("admin");
		driver.findElement(By.id("txt-password")).sendKeys("admin");
		driver.findElement(By.id("btn-login")).click();
		
		String expectedInvalidLoginMessage=driver.findElement(By.xpath("//p[@class='lead text-danger']")).getText();
		
		Assert.assertTrue(expectedInvalidLoginMessage.contains(invalidLoginMessage));
		
		driver.quit();
	}
	

}
