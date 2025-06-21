package katalon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BookAppointment extends KatalonBaseTest{

	WebDriver driver;
	
	public BookAppointment(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	String dateArray[] = KatalonBaseTest.date.split(" ");

	String date = dateArray[0];
	String month = dateArray[1];
	int year = Integer.parseInt(dateArray[2]);
	
	By selectDropdownElement=By.id("combo_facility");
	By readMissionElement=By.id("chk_hospotal_readmission");
	By medicareProgramElement=By.id("radio_program_medicare");
	By medicaidProgramElement=By.id("radio_program_medicaid");
	By noneProgramElement=By.id("radio_program_none");
	By addCommentElement=By.id("txt_comment");
	
	
	// Date related Element
	
	By visitDateElement=By.id("txt_visit_date");
	By decadeCalendarDayElement=By.xpath("//div[@class='datepicker-days']//tr[2]//th[2]");
	By decadeCalendarMonthElement=By.xpath("//div[@class='datepicker-months']//tr[2]//th[2]");
	By decadeCalendarYearElement=By.xpath("//div[@class='datepicker-years']//tr[2]//th[2]");
	By yearsListElement=By.xpath("//div[@class='datepicker-years']//child::span");
	By nextButtonElement=By.xpath("//div[@class='datepicker-years']//tr[2]//th[@class='next']");
	By prevButtonElement=By.xpath("//div[@class='datepicker-years']//tr[2]//th[@class='prev']");
	By monthsListElement=By.xpath("//span[contains(@class,'month')]");
	By datesListElement=By.xpath("//div[@class='datepicker-days']//td[contains(text()," + date + ")]");
	By bookAppointmentButton=By.id("btn-book-appointment");
	
	public ConfirmationPage bookAppointment() throws InterruptedException {
		WebElement selectDropdown = driver.findElement(selectDropdownElement);
		Select select = new Select(selectDropdown);
		select.selectByContainsVisibleText(facilityName);
	
		readMission();
		selectProgram();
		selectDate();
		addComment();
		
		driver.findElement(bookAppointmentButton).click();
			
		return new ConfirmationPage(driver);
	}
	
	public void readMission() {
		if (readmission.equalsIgnoreCase("yes")) {
			driver.findElement(readMissionElement).click();
		}
	}
	
	public void selectProgram() {
		if (program.equalsIgnoreCase("Medicare")) {
			driver.findElement(medicareProgramElement).click();
		} else if (program.equalsIgnoreCase("Medicaid")) {
			driver.findElement(medicaidProgramElement).click();
		} else {
			driver.findElement(noneProgramElement).click();
		}
	}
	
	public void addComment() {
		driver.findElement(addCommentElement).sendKeys(comment);
	}
	
	public void selectDate() throws InterruptedException {
		driver.findElement(visitDateElement).click();
		goToDecadeCalendar();
		selectYear();
		selectMonth();
		selectDateToVisit();
	}
	
	public void goToDecadeCalendar() {
		driver.findElement(decadeCalendarDayElement).click();
		driver.findElement(decadeCalendarMonthElement).click();
	}
	
	public void selectYear() throws InterruptedException {
		boolean flag = false;
		
		do {
			String yearDecade = driver.findElement(decadeCalendarYearElement).getText();
			String yearDecadeArray[] = yearDecade.split("-");
			
			int fromYear = Integer.parseInt(yearDecadeArray[0]);
			int toYear = Integer.parseInt(yearDecadeArray[1]);

			if (year >= fromYear && year <= toYear) {
				List<WebElement> yearsList = driver.findElements(yearsListElement);

				for (WebElement singleYears : yearsList) {
					int selectyear = Integer.parseInt(singleYears.getText());
					if (selectyear == year) {
						singleYears.click();
						break;
					}
				}

				flag = true;

			} else if (year > toYear) {
				driver.findElement(nextButtonElement).click();
				Thread.sleep(500);
			} else {
				driver.findElement(prevButtonElement).click();
				Thread.sleep(500);
			}
		} while (flag == false);
	}
	
	public void selectMonth() throws InterruptedException {

		List<WebElement> monthsList = driver.findElements(monthsListElement);

		for (WebElement singleMonth : monthsList) {

			if (month.equalsIgnoreCase(singleMonth.getText())) {
				singleMonth.click();
				Thread.sleep(500);
				break;
			}
		}
	}
	
	public void selectDateToVisit() {
		List<WebElement> datesList = driver.findElements(datesListElement);

		for (WebElement singleDate : datesList) {
			if (singleDate.getText().equalsIgnoreCase(date)) {
				singleDate.click();
				break;
			}
		}
	}
}
