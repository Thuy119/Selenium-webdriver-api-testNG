package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Webbrowser_API_excercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Url() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}

	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
		
	}

	@Test
	public void TC_03_Navigate () {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}
		
		@Test
		public void TC_04_Pagesource () {
			driver.get("http://live.demoguru99.com");
			
		    String pageSource;
		    
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			pageSource = driver.getPageSource();
			
			Assert.assertTrue(pageSource.contains("Login or Create an Account"));
			
			driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			
			pageSource = driver.getPageSource();
			
			Assert.assertTrue(pageSource.contains("Create an Account"));
			
			
		}
		
		
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}