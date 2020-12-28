package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_Upload_File {
	WebDriver driver;
	//dinh nghia duong dan
	//duong dan: D:\Automation Test\02- Selenium API
	String projectLocation = System.getProperty("user.dir");
	
	//dinh nghia ten file
		String daNangFileName="Da Nang.jpg";
		String haNoiFileName="Ha Noi.jpg";
		String saiGonFileName="Sai Gon.jpg";
		String seleniumPdfFile = "Selemium Testing Tools Cookbook.pdf";
	
	
	//dinh nghia duong dan file: D:\Automation Test\02- Selenium API - //uploadfile - ten file
	String daNangFileNamePath= projectLocation + "\\uploadFile\\" + daNangFileName ;
	String haNoiFileNamePath= projectLocation + "\\uploadFile\\" + haNoiFileName;
	String saiGonFileNamePath= projectLocation + "\\uploadFile\\" + saiGonFileName;
	String seleniumPdfFilePath = projectLocation + "\\uploadFile\\" + seleniumPdfFile;
	

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_SenKeys_One_File_Per_Time() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//Only work voi element co the (//input[@type='file'])
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(daNangFileNamePath); //relative path: tuong doi
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(haNoiFileNamePath); 
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(saiGonFileNamePath); 
		
		
		//Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Da Nang.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Ha Noi.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Sai Gon.jpg']")).isDisplayed());
		
		
//		        //Verify file loaded success (dung theo khai bao bien ben tren)
//				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + daNangFileName + "']")).isDisplayed());
//				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + haNoiFileName + "']")).isDisplayed());
//				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + saiGonFileName + "']")).isDisplayed());
		
		//Click start upload each file
		List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
		for (WebElement startButton : startUpload) {
			startButton.click();
			sleepInSecond(1);
		}
		
		
		//Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Da Nang.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Ha Noi.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Sai Gon.jpg']")).isDisplayed());
		
		
	}

	
	@Test
	public void TC_02_SenKeys_Multiple_File_Per_Time() {
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		//Only work voi element co the (//input[@type='file']) //relative path: tuong doi
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(daNangFileNamePath + "\n" + haNoiFileNamePath +"\n" + saiGonFileNamePath );
		
		
		
		//Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Da Nang.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Ha Noi.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Sai Gon.jpg']")).isDisplayed());
		
		
//		        //Verify file loaded success (dung theo khai bao bien ben tren)
//				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + daNangFileName + "']")).isDisplayed());
//				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + haNoiFileName + "']")).isDisplayed());
//				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + saiGonFileName + "']")).isDisplayed());
		
		//Click start upload each file
		List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
		for (WebElement startButton : startUpload) {
			startButton.click();
			sleepInSecond(1);
		}
		
		
		//Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Da Nang.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Ha Noi.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Sai Gon.jpg']")).isDisplayed());
		
		
	
	}

	@Test
	public void TC_03_Google_Translate () {
		driver.get("https://translate.google.com/?hl=vi&sl=en&tl=vi&op=docs");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(seleniumPdfFilePath);
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
