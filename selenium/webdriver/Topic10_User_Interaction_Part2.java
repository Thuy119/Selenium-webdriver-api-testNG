package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic10_User_Interaction_Part2 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;

	String project_location = System.getProperty("user.dir");
	String jsHelperPath = project_location + "\\drapAndDrop\\drag_and_drop_helper.js";
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	public void TC_01_Right_CLick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		//Verify Quit not contain (visible/hover status)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') "
				+ "and not(contains(@class,'context-menu-visible')) and not(contains(@class, 'context-menu-hover'))]")).isDisplayed());
	
	
		
		//Hover to Quit
		 action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
		 		+ " and not(contains(@class,'context-menu-visible')) and not(contains(@class, 'context-menu-hover'))]"))).perform();
		 
		 sleepInSecond(3);
		 
		
		
		//Verify contain (visible/hover)
		 Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
					+ "and (contains(@class,'context-menu-visible')) and (contains(@class, 'context-menu-hover'))]")).isDisplayed());
	
		 sleepInSecond(3);
		 
		//Click to Quit
		driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
					+ "and (contains(@class,'context-menu-visible')) and (contains(@class, 'context-menu-hover'))]")).click();
		
		 sleepInSecond(3);
		 
		 
		//Verify alert xuat hien
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		
		
		
	}
	
	
	@Test
	public void TC_01_Right_CLick_Recode() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	sleepInSecond(3);
	
	//Verify Quit not contain(visible/hover status)
	Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')"
			+ "and not(contains(@class, 'context-menu-hover'))" +
			"and not(contains(@class, 'context-menu-visible'))]")).isDisplayed());
	
	//Hover Quit
	action.moveToElement(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')"
			+ "and not(contains(@class, 'context-menu-hover'))" +
			"and not(contains(@class, 'context-menu-visible'))]"))).perform();
	sleepInSecond(3);
	
	//Verify Quit contain (visible/hover status)

	Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
	+ "and (contains(@class, 'context-menu-hover')) and (contains(@class,'context-menu-visible'))]")).isDisplayed());
	
	driver.findElement(By.xpath("//span[text()='Quit']")).click();
	
	sleepInSecond(3);
	
	Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	
	}
	 
	//@Test
	public void TC_02_Drag_And_Drop_HTML4() {
	driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
	WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
	WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
	
	action.dragAndDrop(sourceCircle, targetCircle).perform();
	sleepInSecond(3);
	
	Assert.assertEquals(targetCircle.getText(), "You did great!");
	sleepInSecond(3);
	
	
	
	}
		
	//@Test
	public void TC_03_Drag_And_Drop_HTML5_JQuery() throws IOException {
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	
	
	String sourceRectangleCSS = "#column-a";
	String targetRectangleCSS = "#column-b";
	
	String jsHelpercontent = getJSFileContent(jsHelperPath);
	
	
	jsHelpercontent = jsHelpercontent + "$(\"" + sourceRectangleCSS + "\").simulateDragDrop({ dropTarget: \"" + targetRectangleCSS + "\"});";
	jsExecutor.executeScript(jsHelpercontent);
	
	
	
	
	
	
	}
	
	//@Test
	public void TC_04_Drag_And_Drop_HTML5_Position() {
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	
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
	public String getJSFileContent(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}
