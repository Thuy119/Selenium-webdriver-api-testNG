package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

public class Topic12_Iframe_Frame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Ifame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		//Switch vao iframe cua fb
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title, 'Facebook Social Plugin')]")));
	
	Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
	
	//Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText(), "1,939 likes");
		
	String likeText = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
	int likeNumber = Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
	System.out.println(likeNumber);
	
//	assertThat(likeNumber, greaterThan(1900));
	
	//Switch to Top window
	driver.switchTo().defaultContent();
	
	
	Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(),"[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
	
	//Switch to Google doc iframe
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
	
	Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
	}

	
	@Test
	public void TC_02() {
		
	}

	@Test
	public void TC_03 () {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
