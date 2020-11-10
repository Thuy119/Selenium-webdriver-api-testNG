package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic10_User_Interaction_Part1 {
	WebDriver driver;
	Actions action;
	
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	public void TC_01_Hover_Mouse() {
		driver.get("https://tiki.vn");
		
		//Verify login button undisplay
		Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		
		WebElement shortCutAccount = driver.findElement(By.xpath("//div[@data-view-id='header_user_shortcut_account']"));
		
		
		//muon hover vao dung thu vien cua selenium: Actions
		
		//Perform: bắt buộc cho all hàm sau khi đã sử dụng, dùng để thực hiện hành động đó, ko có perform sẽ ko chạy
		//Release: nhả chuột trái ( dùng cho Click and hold)
		action.moveToElement(shortCutAccount).perform();
		sleepInSecond(4);
		
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		
	}
	
	

	public void TC_02_Hover_Mouse1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
		
	
	}


	public void TC_03_Hover_Mouse2() {
		driver.get("https://hn.telio.vn/");
		
		action.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ uống']"))).perform();
		
		action.click(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='page-title-heading']/span")).getText(), "BIA");
		
		
	}
	
	
	public void TC_03_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//Tạo ra 1 list chứa all 12 numbers
		
	List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	System.out.println("All number = " + allNumber.size());
	// 0 -11: index
	// 1-2-....12: value
	
	action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(10)).release().perform();
	sleepInSecond(1);
	
	List<WebElement> allNumberSelected =  driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
	System.out.println("Number selected = " + allNumberSelected.size());
	
	
	Assert.assertEquals(allNumberSelected.size(), 9);
	
	}
	

	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//Tạo ra 1 list chưa all 12 numbers
	List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	System.out.println("All number " + allNumber.size());
	
	//nhấn xuống phím Ctrl
	action.keyDown(Keys.CONTROL);
	//click vào 1,3,5,9
	
	action.click(allNumber.get(0))
	.click(allNumber.get(2))
	.click(allNumber.get(4))
	.click(allNumber.get(8))
	.perform();
	sleepInSecond(3);
	//nhả phím Ctrl
	action.keyDown(Keys.CONTROL);
	sleepInSecond(3);
	
	
	
	//Verify các số đã dc chọn
	 List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
	 System.out.println("All number selected " + allNumberSelected.size());
	 
	 
	 Assert.assertEquals(allNumberSelected.size(), 4);
	 

	}
	
	@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(doubleButton).perform();
		sleepInSecond(3);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()= 'Hello Automation Guys!']")).isDisplayed());
		
		
	
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
