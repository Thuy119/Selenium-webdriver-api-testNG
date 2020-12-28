package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Wait_PartIV {
	WebDriver driver;
	WebDriverWait expliciWait;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);
		
		//Neu nhu chua thoa man dk thi co co che tim lap lai trong moi 0.5s
		// Neu nhu thoa man dk roi thi ko can phai cho het timeout nua
		
	}

	@Test
	public void TC_01_Clickable_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//Cho cho start button dc click hay chua(phu hop cho case button bi disable va se dc trigger 1 su kien nao do)
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));
	
		//Click to start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//Wait for loading icon Invisible
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
				
		
		//Verify text
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
				
		
		
	}

	
	@Test
	public void TC_02_Clickable_Visible() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//Cho cho start button dc click hay chua(phu hop cho case button bi disable va se dc trigger 1 su kien nao do)
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));
	
		//Click to start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		
		//Wait for text Hello world visible
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4")));
		
		
		//Verify text
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
				
		
	}

	@Test
	public void TC_03 () {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
