package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_Upload_File3 {
	WebDriver driver;
	//dinh nghia duong dan
	String projectLocation = System.getProperty("user.dir");
	
	
	//dinh nghia ten file
	String daNangimageFile = "Da Nang.jpg";
	String saiGonimageFile = "Sai Gon.jpg";
	String haNoiimageFile = "Ha Noi.jpg";
	
	//dinh nghia duong dan file
	String daNangfileNamePath = projectLocation + "\\uploadFile\\" + daNangimageFile;
	String saiGonfileNamePath = projectLocation + "\\uploadFile\\" + saiGonimageFile;
	String haNoifileNamePath = projectLocation + "\\uploadFile\\" + haNoiimageFile;
	
	

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_SenKeys_One_File_Per_Time() {
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(daNangfileNamePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(saiGonfileNamePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(haNoifileNamePath);
		
		
		
		//Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Da Nang.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Sai Gon.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Ha Noi.jpg']")).isDisplayed());
		
		
		//Click Start Upload each file
		List<WebElement> startUpload =	driver.findElements(By.cssSelector("table .start"));
		for (WebElement startButton : startUpload) {
			startButton.click();
			sleepInSecond(1);
			
			
			
		//Verify file uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Da Nang.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Sai Gon.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Ha Noi.jpg']")).isDisplayed());
			
		}
	}

	
	@Test
	public void TC_02_SenKeys_Multiple_File_Per_Time() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(daNangfileNamePath + "\n" + saiGonfileNamePath + "\n" + haNoifileNamePath);
		
	}
		
		
		

	//@Test
	public void TC_03 () {
		
	}
	
	

	public void sleepInSecond(long timeInSecond) {

		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	

}
