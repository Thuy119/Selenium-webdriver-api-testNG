package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Wait_PartV {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Only_Implicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		driver.findElement(By.xpath("//a[text()='14']/parent::td")).click();
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Monday, December 14, 2020']")).isDisplayed());
		
	}

	
	//@Test
	public void TC_02() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Cho cho ngay 14 co the dc click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='14']/parent::td")));
		driver.findElement(By.xpath("//a[text()='14']/parent::td")).click();
		
		//Cho cho ngay dc chon thanh cong
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='14']/parent::td[@class='rcSelected']")));
		
		//Cho cho loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Monday, December 14, 2020");
		
		
		
		
	}

	//@Test
	public void TC_03_Only_ExplicitWait () {
		driver.get("https://www.file.io/");
		//explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']")));
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectLocation + "\\uploadFile\\background.jpg");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='progress-button-total']")));
	 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='download-url']")));
		
		driver.findElement(By.xpath("//a[@id='download-url']")).click();
		
	
	}
	
	@Test
	public void TC_04_Only_ExplicitWait_Alert () {
		driver.get("http://demo.guru99.com/V4/");
		driver.findElement(By.name("btnLogin")).click();
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		alert.accept();
		
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	

}
