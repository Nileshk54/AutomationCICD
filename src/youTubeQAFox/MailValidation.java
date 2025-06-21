package youTubeQAFox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class MailValidation {

	String expectedMailValidation = "Warning: E-Mail Address is already registered!";
	int i = 0;

	@Test(dataProvider = "getData")
	public void mailValidation(String email) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String mail = "neel54@gmail.com";

		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("Nilam");
		driver.findElement(By.id("input-lastname")).sendKeys("Maushi");
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("9535241523");
		driver.findElement(By.id("input-password")).sendKeys("Admin@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Admin@123");

		driver.findElement(By.xpath("//div[@class='pull-right']//input[@value='1']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(2000);
		File scrFile = driver.findElement(By.xpath("//div[@id='content']")).getScreenshotAs(OutputType.FILE);
		i++;
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\ScreenShot\\ActualImage" + i + ".png"));

		boolean b=comparePhoto();

		System.out.println(b);

		driver.quit();
	}

	public boolean comparePhoto() throws IOException {
		BufferedImage actualImage = ImageIO
				.read(new File(System.getProperty("user.dir") + "\\ScreenShot\\ActualImage" + i + ".png"));

		BufferedImage expectedImage = ImageIO
				.read(new File(System.getProperty("user.dir") + "\\ScreenShot\\ImageExpected" + i + ".png"));

		ImageDiffer imageDiff = new ImageDiffer();
		ImageDiff imageDifference = imageDiff.makeDiff(expectedImage, actualImage);
		
		boolean b = imageDifference.hasDiff();
		return b;
	}

	@DataProvider
	public Object[] getData() {
		return new Object[][] { { "neel" }, { "neel@" }, { "neel@gmail" }, { "neel@gmail." } };
	}
}
