package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Wait_PartIII {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// Timeout nay dc apply duy nhat cho viec tim element (findElement, findElements), ko lien quan den open browser
		//Neu nhu ko set timeout = 0
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		
	}

	
	public void TC_01() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//Click to start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Loading icon 5s
		//ImplicitWait 5s de cho cho element verify text xuat hien
		//Verify text
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
		
	}

	@Test
	public void TC_02_Upload_Image() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\Automation Test\\02- Selenium API\\uploadFile\\background.jpg");
		
		driver.findElement(By.cssSelector("table .start")).click();
		
		
		// Lam sao de cho cho no upload thanh cong
		//Day la ham e Wait cho upload file image thanh cong
		driver.findElement(By.xpath("//p/a[@title='background.jpg']")).isDisplayed();
		
		
		
	
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
