package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Xpath_CSS_Part1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@name='send']")).click();

		String emailErrorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();

		Assert.assertEquals(emailErrorMessage, "This is a required field.");

		String passwordErrorMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();

		Assert.assertEquals(passwordErrorMessage, "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("12341234@12312.123123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@name='send']")).click();

		String emailErrorMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailErrorMessage, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Invalid_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12");
		driver.findElement(By.xpath("//button[@name='send']")).click();

		String passwordErrorMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordErrorMessage,
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1231234");
		driver.findElement(By.xpath("//button[@name='send']")).click();

		String errorMessage = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errorMessage, "Invalid login or password.");
	}

	@Test
	public void TC_05_Login_With_Valid_Email_And_Invalid_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thuy@mailinator.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String myDashboardText = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(myDashboardText, "MY DASHBOARD");

		String contactInfomation = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		Assert.assertEquals(contactInfomation, "Hello, Thuy Thanh Ng!");

		String contactInfomationText = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();
		// Assert.assertEquals(contactInfomationText, "Thuy Thanh Ng");
		// Assert.assertEquals(contactInfomationText, "thuy@mailinator.com");//

		Assert.assertTrue(contactInfomationText.contains("Thuy Thanh Ng"));
		Assert.assertTrue(contactInfomationText.contains("thuy@mailinator.com"));

		// driver.findElement(By.xpath("//a[@class='skip-link skip-account
		// skip-active']//span[@class='label']")).click();
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// Cach1: text ngắn, ko có kí tự xuống dòng, tab hoặc khoảng trắng ở đầu cuối
		// chuỗi
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='My
		// Dashboard')]")).isDisplayed());

		// Cach2: tương đối: text dài, có kí tự xuống dòng, tab hoặc khoảng trắng ở đầu
		// cuối chuỗi

		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[contains(text(),'My
		// Dashboard')]")).isDisplayed());

		// Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),
		// "Hello, Thuy Thanh Ng!");

		// Tuong doi:
		// String contactInfomationText =
		// driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();

		// Assert.assertTrue(contactInfomationText.contains("Thuy Thanh Ng"));
		// Assert.assertTrue(contactInfomationText.contains("thuy@mailinator.com"));

		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p[contains(.,'Thuy
		// Thanh Ng')]")).isDisplayed());
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p[contains(.,'thuy@mailinator.com')]")).isDisplayed());

	}

	@Test
	public void TC_06_Register_A_New_Account_To_System() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		String firstname = "Tony";
		String lastname = "Six";
		String email = "Tonysix" + randomNumber() +"@mailinator.com";
		
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String registerSuccess = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(registerSuccess, "Thank you for registering with Main Website Store.");
		
		
		String contactInfo = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();
		
		Assert.assertTrue(contactInfo.contains(firstname + " "+ lastname ));
		Assert.assertTrue(contactInfo.contains(email));
		
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		
//		driver.findElement(By.id("firstname")).sendKeys("QApro");
//		driver.findElement(By.id("lastname")).sendKeys("Test");
//		driver.findElement(By.id("email_address")).sendKeys("qaproteam@mailinator.com");
//		driver.findElement(By.id("password")).sendKeys("123456");
//		driver.findElement(By.id("confirmation")).sendKeys("123456");
//		driver.findElement(By.xpath("//button[@title='Register']")).click();
//		
//		String registerSuccess = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
//		Assert.assertEquals(registerSuccess, "Thank you for registering with Main Website Store.");
//		
//		
//		String contactInfo = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();
//		
//		Assert.assertTrue(contactInfo.contains("QApro Test"));
//		Assert.assertTrue(contactInfo.contains("qaproteam@mailinator.com"));
//		

	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
		
	}
	

	@Test
	public void TC_07_Register_To_System() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
