package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	By loginButton = By.cssSelector(".fhs-btn-box");
	By loginUsername = By.cssSelector("#login_username");
	By loginPassword = By.cssSelector("#login_password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		jsExecutor =(JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		System.out.println("thuy" + isElementEnabled(loginButton) );
		//Disabled field
		Assert.assertFalse(isElementEnabled(loginButton));
		
		
		driver.findElement(loginUsername).sendKeys("thuy@gmail.com");
		driver.findElement(loginPassword).sendKeys("123456");
		
		sleepInSecond(2);
		//Enable field
		Assert.assertTrue(isElementEnabled(loginButton));
		
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector("popup-login-tab-login")).click();
		
		removeDisabledAttributeByJS(loginButton);
		sleepInSecond(2);
		
		//Enable field
		Assert.assertTrue(isElementEnabled(loginButton));
		
		driver.findElement(loginButton).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		
			
	
		
	}

	
	@Test
	public void TC_02_Checkbox_SelectAll() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		//Select all
		for (WebElement checkbox :checkboxes) {
			checkbox.click();
			sleepInSecond(1);	
			
		}
		//Verify
	for (WebElement checkbox : checkboxes) {
		Assert.assertTrue(isElementSelected(checkbox));
		
		
	}
		
	}
	
	
	public void TC_02_Checkbox_Custom() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
	}

	@Test
	public void TC_03_radioButton () {
		
	}
	
	public void sleepInSecond(long timeInSecond) {

		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	//ham dung chung
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is enable");
			return true;
		}else {
			System.out.println("Element is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println("Element is selected");
			return true;
		}else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	
	public boolean isElementSelected(WebElement element) {
		if(element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		}else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	
	
public void removeDisabledAttributeByJS(By by) {
	WebElement element = driver.findElement(by);
	jsExecutor.executeScript("argumants[0].removeAttribute('disabled')", element);
}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
