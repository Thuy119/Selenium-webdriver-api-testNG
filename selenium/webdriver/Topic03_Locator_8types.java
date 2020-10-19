package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Locator_8types {
	//khai báo biến đại diện cho WebDriver
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		//Khởi tạo trình duyệt lên (Chrome/FF/IE/Edge/Opera/Safari/...)
		driver = new FirefoxDriver();
		
		//Chờ element được hiển thị để thao tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Phóng to trình duyệt (ko phải fullscreen)
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Locator() throws InterruptedException {

		
		//Mở ra 1 trang web
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		
		//ID
		driver.findElement(By.id("email")).sendKeys("thanhthuynguyendn94@gmail.com");
		Thread.sleep(3000);
		
				
		
		
		//Class
		driver.findElement(By.className("validate-password")).sendKeys("123456");
		Thread.sleep(3000);
		
		
		//Name
		driver.findElement(By.name("send")).click();
		Thread.sleep(3000);
		
		
		//Link text
		driver.findElement(By.linkText("ADVANCED SEARCH")).click();
		Thread.sleep(3000);
		
		
		
		//Partial Link Text
		driver.findElement(By.partialLinkText("ORDERS AND")).click();
		Thread.sleep(3000);
		
		
		//Tagname (Muốn tìm xem page này có bao nhiêu textbox)
		int textboxSize = driver.findElements(By.tagName("input")).size();
		System.out.println(textboxSize);
		Thread.sleep(3000);
		
		
		//Css
		driver.findElement(By.cssSelector("#oar_email")).sendKeys("css@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[name='oar_order_id']")).sendKeys("1234566");
		Thread.sleep(3000);
		
		//Xpath
		driver.findElement(By.xpath(".//input[@id='oar_billing_lastname']")).sendKeys("Automation Testing");
	    Thread.sleep(3000);
	    
	}
		
		
		
		@Test
		public void TC_02_Matching_Node() {
			
			
		
		
	
	}

	@AfterClass
	public void afterClass() {
		//Đóng trình duyệt
		driver.quit();
	}
	
   
}
