package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic13_Tab_Window {
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Only2_Windows_orTab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Moi 1 tab/window se co 1 ID dai dien
		
		//lay ra ID cua tab/window truoc khi click
	String parentWindowID =driver.getWindowHandle();
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(3);
	
	switchtoWindowbyID(parentWindowID);
	
	
	Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
	
	String googleWindowID = driver.getWindowHandle();
	switchtoWindowbyID(googleWindowID);
	sleepInSecond(2);
	
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	sleepInSecond(3);
	}

	
	//@Test
	public void TC_02_Greater_than_2Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		
	
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(3);
	
	switchtoWindowbyTitle("Google");
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
	
	switchtoWindowbyTitle("SELENIUM WEBDRIVER FORM DEMO");
	sleepInSecond(2);
	
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	sleepInSecond(3);
	
	switchtoWindowbyTitle("Facebook - Đăng nhập hoặc đăng ký");
	
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='m_login_email']")).isDisplayed());
	sleepInSecond(3);
	
	switchtoWindowbyTitle("SELENIUM WEBDRIVER FORM DEMO");
	sleepInSecond(2);
	
	driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	sleepInSecond(3);
	
	switchtoWindowbyTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
	
	Assert.assertTrue(driver.findElement(By.xpath("//i[@class='tikicon icon-tiki_short']")).isDisplayed());
	sleepInSecond(3);
	
	//chi giu lai trang dau tien, close all tab con lai di
	
		closeAllWindowExceptParent(parentID);
	
	}
	
	@Test
		public void TC_03_Compare_Product() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchtoWindowbyTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(2);
		
	
		Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(), 2);
		closeAllWindowExceptParent(parentID);
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Xiaomi");
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		
	}
	
	
	
    //chi dung cho 2 window/tab
	public void switchtoWindowbyID(String parentID) {
		//Lay ra all ID cua window/tab dang co
		Set<String> allWindowID = driver.getWindowHandles();
		
		//Duyet qua tung ID
		for (String windowID : allWindowID) {
			
			//Neu nhu ID nao khac voi parentID thi nhay vao ham if
			if(!(windowID).equals(parentID)) {
				
				//switch vao 1 window/1 tab theo ID
				driver.switchTo().window(windowID);	
				break;
			}
		}
				
			}
			
	// >=2 window/tab
	public void switchtoWindowbyTitle(String expectedWindowTitle) {
		//Lay ra all window/tab dang co
	Set<String> allWindowIDs =	driver.getWindowHandles();
	System.out.println("So luong tab/window dang co: " + allWindowIDs.size());
	
		//Duyet qua cac gia tri trong Set
		for (String windowID : allWindowIDs) {
			//Switch vao tung window/tab truoc
			driver.switchTo().window(windowID);
			//Get ra Title cua tung window/tab
			String actualWindowTitle = driver.getTitle();
			//Kiem tra neu nhu cai nao bang Title mong muon 
			if(actualWindowTitle.equals(expectedWindowTitle)) {
				//thi thoat khoi vong lap
				break;
				
			}
			
			
			
			
		}
	
	}
			
	public void closeAllWindowExceptParent(String parentID) {
		//Lay ra all ID cua window/tab dang co
				Set<String> allWindowID = driver.getWindowHandles();
				
				//Duyet qua tung ID
				for (String windowID : allWindowID) {
					if(!windowID.equals(parentID)) {
					driver.switchTo().window(windowID);
					//chi dong tab/window dang active
					driver.close();
					}
					if(driver.getWindowHandles().size() == 1) {
						driver.switchTo().window(parentID);
						break;
					
				}
		
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
		
	}
}
	


