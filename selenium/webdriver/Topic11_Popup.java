package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_Popup {
	WebDriver driver;
	WebDriverWait expliciWait;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//cho trong vong 10s de element dc hien thi
		expliciWait = new WebDriverWait(driver, 10);
		//cho element dc load ra trong DOM
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		//cho cho 1 element dc hien thi
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		
		//cho cho 1 element ko con hien thi
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		
		
	}
	//@Test
	public void TC_02_Fixed_Popup() throws InterruptedException {
		driver.get("https://bni.vn/");
		
		//cho cho 1 element dc hien thi
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();
		
		Thread.sleep(3000);
		
		//chờ cho element có thể dc click hay ko
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		driver.findElement(By.xpath("//img[@alt='Close']")).click();	
		
		
		//cho cho 1 element ko con hien thi
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());		
	
	}
	
	
	@Test
	public void TC_03_Ramdom_In_DOM() {
		//Step1:
		driver.get("https://blog.testproject.io/");
		sleepInSencond(7);
		
		
		//Step2
		//(Co xuat hien - dong popup di) - (qua step tiep)
		//(ko xuat hien) - qua step tiep thep
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			
			//chờ cho element có thể dc click hay ko
			expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='close-mailch']")));
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSencond(2);
		}
		//Step 3
		//Cho 1 element dc hien thi
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
      driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("Excel");
    //chờ cho element có thể dc click hay ko
	  expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
      driver.findElement(By.xpath("//section//span[@class='glass']")).click();
		sleepInSencond(5);
		
}

	
	@Test
	public void TC_03_Ramdom_NOT_In_DOM() {
		driver.get("https://shopee.vn/");
		
		sleepInSencond(5);
	List<WebElement> popup = driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
	if(popup.size()>0 && popup.get(0).isDisplayed()) {
		System.out.println("Close popup");
		 expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='shopee-popup__close-btn']")));
		driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
	}else {
		System.out.println("Popup ko xuat hien");
	
		sleepInSencond(5);
	}
		
		
}
	
	
	
	public void sleepInSencond(long timeInSenconds) {
		try {
			Thread.sleep(timeInSenconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
