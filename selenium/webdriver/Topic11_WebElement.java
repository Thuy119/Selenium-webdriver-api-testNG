package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_WebElement {
	//1 khai báo biến
	WebDriver driver;
	//Biến global
	

	//khai báo và gán giá trị để đại diện cho By (chưa hề đi tìm element)
	By emailTextbox = By.xpath("//input[@id='email']");
	By ageUnderRadio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By user5Text = By.xpath("//h5[text()='Name: User5']");
	By jobRole01Dropdown = By.id("job1");
	By javaLanguageCheckbox = By.id("job3");
	
	
	
	
	By passwordTextbox = By.id("password");
	By disableRadio = By.id("radio-disabled");
	By jobRole03Dropdown = By.id("job3");
	
	
	

	@BeforeClass
	public void beforeClass() {
		//2 khởi tạo trình duyệt
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
    //All elements: checkbox/radio/textbox/link/text...
	@Test
	public void TC_01_Element_Displayed() throws InterruptedException { 
		//3. mở app
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(emailTextbox).isDisplayed()){
			System.out.println("Element is displayed with:" + emailTextbox);
			
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		} else
			System.out.println("Element is not displayed with:" + emailTextbox );
		
		Thread.sleep(3000);
		
		if (driver.findElement(ageUnderRadio).isDisplayed()) {
			System.out.println("Element is displayed with:" + ageUnderRadio);
			driver.findElement(ageUnderRadio).click();
	
			
			
		} else
			System.out.println("Element is not displayed with:" + ageUnderRadio);
		
		Thread.sleep(3000);
		
		if (driver.findElement(educationTextArea).isDisplayed()) {
			System.out.println("Element is display with:" + educationTextArea);
			driver.findElement(educationTextArea).sendKeys("Automation Testing");
			
			
		}
		
		if (driver.findElement(user5Text).isDisplayed()) {
			System.out.println("Element is display with:" + user5Text);
		}else
			
			System.out.println("Element is not displayed with:" + user5Text);
		
		
		Thread.sleep(3000);
		
		
		
		
	
		
	}

	
	@Test
	//Checkbox/Textbox/Radio/button/image...
//	public boolean TC_02_Element_Enable() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		driver.navigate().refresh();
//		
//		Assert.assertTrue(isElementEnabled(emailTextbox));
//		Assert.assertTrue(isElementEnabled(ageUnderRadio));
//		Assert.assertTrue(isElementEnabled(educationTextArea));
//		Assert.assertTrue(isElementEnabled(user5Text));
//		
		
	//Common function (Call function)
	public boolean isElementDisplayed(By by) {
			if (driver.findElement(by).isDisplayed()){
				System.out.println("Element is displayed");
			return true;
			}else {
				System.out.println("Element is not displayed");
				return false;
			}
			
		}
	
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
		return true;
		}else {
			System.out.println("Element is not enabled");
			return false;
		}
		
	}
		
		

	/*
	 * private boolean isElementEnabled(By emailTextbox2) { // TODO Auto-generated
	 * method stub return false; }
	 */
	


	/*
	 * @Test //Checkbox/Radio public void TC_03_Element_Selected () {
	 * driver.get("https://automationfc.github.io/basic-form/index.html");
	 * driver.navigate().refresh(); //Verify age under 18/java checkbox deselect
	 */
		
		
	//}
	
	@Test
	//Checkbox/Radio
	public void TC_04_Validate_Register_Form () throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("Automation@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Automationtest");
		
		//kiemtra verify sign up button disable
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		
		//lower case
		driver.findElement(By.id("new_password")).sendKeys("auto");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		
		//specical characters
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto!@#");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		
		//uppercases
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Auto!@#");
		Thread.sleep(2000);
		
		
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		
		//8 characters minium
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation!@#");
		Thread.sleep(2000);
		
		
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		
		//number
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation123");
		Thread.sleep(2000);
		
		
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		
		//full data
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation123!@#");
		Thread.sleep(2000);
		Assert.assertTrue(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		
		
		
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
