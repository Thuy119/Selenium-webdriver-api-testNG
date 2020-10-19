package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_Webbrowser_Element {
	//Khai báo biến đại diện cho Selenium Webdriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//khởi tạo firefox
		driver = new FirefoxDriver();
		//set timeout cho việc tìm kiếm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Web_Browser() {
		// mo app
		driver.get("https://facebook.com");// ***
 
		//lay ra URL cua trang hien tai:
		driver.getCurrentUrl();// ***
		
		//lay tat ca code cua trang hien tai
		driver.getPageSource();//***
		
		//lay title cua trang hien tai
		driver.getTitle();// ***
		
		
		//window/tab: ID
		driver.getWindowHandle();//***
		
		//all ID:
		driver.getWindowHandles();//***
		
		//dong 1 tab/window
		driver.close();
		
		//dong trinh duyet
		driver.quit();//***
		
		//tim 1 element:
		driver.findElement(By.xpath(""));//***
		
		//tim nhieu element:
		driver.findElements(By.xpath(""));//***
		
		//thoi gian cho cho viec tim kiem element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS );///***
		
		
	    //thoi gian cho de page dc tai xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		//thoi gian cho cho doan code js dc thuc thi xong
		
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		//full het window:
		driver.manage().window().maximize();//****
		
		//1 back ve trang truoc
		driver.navigate().back();
		
		
		//chuyen tiep toi trang truoc
		driver.navigate().forward();
		
		//tai lai trang
		driver.navigate().refresh();
		//4//mo url - 1-4 it dung
		driver.navigate().to("");
		//alert
		driver.switchTo().alert();
		
		//Frame/iframe
		driver.switchTo().frame("facebook"); /// ****
		
		//window/tab
		driver.switchTo().window("ak49-123"); ///***
		
		
	}

	
	@Test
	public void TC_02_Web_Element() {

		
	}

	@Test
	public void TC_03 () {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
