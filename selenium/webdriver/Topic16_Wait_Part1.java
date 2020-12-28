package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Wait_Part1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
	public void TC_01_Display_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//Wait cho 1 element hien thi/visible
		// Co hien thi tren UI, CO trong DOM(HTML)
		// Cho cho email textbox hien thi trong vong 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
	}

	

	public void TC_02_Undisplay_Invisible_In_DOM() {
		//Wait cho 1 element ko hien thi(undisplay/invisible)
		//Khong hien thi tren UI - nguoi dung ko nhin thay va thao tac dc
		//TH1: Element van co trong DOM
		//Tim element dau tien: tim thay ngay va apply dieu kien
		//Pass dieu kien luon ko can cho het timeline
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']/ol/li")));
		
	}

	
	
	public void TC_03_Undisplay_Invisible_NOT_In_DOM() {
		//Wait cho 1 element ko hien thi(undisplay/invisible)
		//Khong hien thi tren UI - nguoi dung ko nhin thay va thao tac dc
		//TH2: Element ko co trong DOM
		//Tim element dau tien: ko tim thay element -> tIM di tim lai nhieu lan cho den khi het timeout cua implict ->10s
		// Sau khi het 10s moi apply dieu kien vao (invisibility) -> Pass
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='edit_account_error']/ol/li")));
		
	}
	
	

	public void TC_04_Presence() {
		//Wait cho element co trong DOM
		//TH1: Element co trong DOM + hien thi tren UI 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
		
		//TH2: Element co trong DOM + ko hien thi tren UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']/ol/li")));
		
	}
	
	@Test
	public void TC_05_Clickable_Enabled() {
		//Button/Radio/Checkbox/List/Dropdown -> Stable truoc khi thao tac
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));
		
		
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys(generateEmail());
		
		driver.findElement(By.id("new_username")).sendKeys("Thuy");
		
		driver.findElement(By.id("new_password")).sendKeys("Thuy!@qa123");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));
		
		driver.findElement(By.id("create-account")).click();
		
	}
	
	//@Test
	public void TC_06_Staleness() {
		driver.get("https://shopee.vn/");
		
		//Wait cho 1 element Satleness:
		//Ko co/ko con trong DOM
		//Step 1 - Thao tac voi 1 element -> Error message xuat hien (*) - hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='home_popup_banner']")));
		WebElement popup = driver.findElement(By.xpath("//img[@alt='home_popup_banner']"));
		
		//Step2- Thao tac vs ... -> (*) ko con xuat hien
		driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		
		
		//Step3 - Can cho cho (*) ko con trong DOM nua lun
		explicitWait.until(ExpectedConditions.stalenessOf(popup));
	}
	
	
	
	public String generateEmail() {
		Random rand = new Random();
		return "thuynguyen" + rand.nextInt(9999) + "@gmail.com";
		
	}
	
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	

}

