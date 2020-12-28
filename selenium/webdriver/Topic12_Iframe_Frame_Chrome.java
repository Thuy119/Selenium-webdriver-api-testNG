package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic12_Iframe_Frame_Chrome {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Ifame() {
		driver.get("https://kyna.vn/");
		
		//Switch to Chat iframe
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).sendKeys("Automation");
		//Nhan enter:
		driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).sendKeys(Keys.ENTER);
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@ng-model='userInfo.username']")).isDisplayed());
		
		
		//Switch to Top window (Homepage)
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector(".search-button")).click();
		
		List<WebElement> courseNameHeader = driver.findElements(By.cssSelector("div.content.h4"));
		List<String> courseNameText = new ArrayList<String>();
		
		
		for (WebElement courseElement : courseNameHeader) {
			System.out.println(courseElement.getText());
			courseNameText.add(courseElement.getText());
			
			
		}
		
		for (String courseName : courseNameText) {
			Assert.assertTrue(courseName.contains("Excel"));
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
		driver.quit();
	}
}
	
	
