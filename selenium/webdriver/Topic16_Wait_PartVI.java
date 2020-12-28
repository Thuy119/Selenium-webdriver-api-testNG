package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic16_Wait_PartVI {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Not_Found_Element_Only_Implictit() {
		driver.get("http://automationpractice.com/index.php?controller=my-account");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		
	}

	
	@Test
	public void TC_02() {
		
	}
	 public String getDateTimeNow() {
		 Date date = new Date();
		 return date.toString();
	 }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
