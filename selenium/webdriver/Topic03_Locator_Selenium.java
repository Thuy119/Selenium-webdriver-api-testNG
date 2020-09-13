package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Locator_Selenium {
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

	@Test
	public void TC_01() {
		//Mở ra 1 trang web
		driver.get("http://live.demoguru99.com/");
		
		
		// <button id="u_0_13" class= "_6j mvm_6wk_6wl_58mi_6o_6v"
		// name="websubmit" type="submit">Đăng ký </button>
		
		
		//html tag (thẻ): button
		//html attribute name(thuộc tính của element): id class name type
		//html attribute value: u_0_13 6j mvm_6wk_6wl_58mi_6o_6v websubmit submit
		
		
		//Click vào button Đăng ký
		//Đi tìm element
		//Với loại locator gì
		//Thực hiện thao tác gì
		//driver.findElement(By.name("websubmit)).click();
		
		
		
		
		
	}

	
	@Test
	public void TC_02() {
		
		
	}

	@Test
	public void TC_03 () {
		
	}

	@AfterClass
	public void afterClass() {
		//Đóng trình duyệt
		driver.quit();
	}
	

}
