package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic14_Javascript_Executor {
	WebDriver driver;
	//JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}

	@Test
	public void TC_01() {
		//driver.get
		navigateToUrlByJS( "http://live.demoguru99.com");

		//ep kieu tuong minh trong java: Object -> String
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		System.out.println("Live Guru Domain is: " + liveGuruDomain);
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		//driver.getCurrentURL();
		String homePageURL = (String) executeForBrowser("return document.URL;");
		System.out.println("Homepage URL = : " + homePageURL);
		Assert.assertEquals(homePageURL, "http://live.demoguru99.com/");
		
		
		//driver.finElement(locator).click();
		clickToElementByJS("//a[text()='Mobile']");
		
		//driver.finElement(locator).click();
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
		
		String innerText = getInnerText();
		
		//Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		
		//driver.finElement(locator).getText();
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		//driver.finElement(locator).click();
		clickToElementByJS("//a[text()='Customer Service']");
		
		
		//driver.getTitle();
		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		System.out.println("Customer Service Title is: " + customerServiceTitle);
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		
		
		scrollToElement("//input[@id='newsletter']");
		sleepInSecond(3);
		
		//driver.finElement(locator).sendKeys();
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		sleepInSecond(3);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
		
		
		//Khi lam thuc te Ham Javascript hay dung: Click, Scroll, highlightElement: demo khachang
		
		
		
		
		//Browser
		//jsExecutor.executeScript("Code javascript");
		
		//Element
		//jsExecutor.executeScript("Code javascript", driver.findElement(By.xpath("")));
		
		

		
	}

	
	@Test
	public void TC_02() {
		
		
	}

	public Object executeForBrowser( String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	public boolean areExpectedTextInInnerText( String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS( String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement( String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement( locator);
		//Luu lai trang thai truoc khi highlight
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS( String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement( locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement( String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement( locator); 
		// WebElement element = driver.findElement(By.xpath(locator)); //bang voi hang ben tren
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS( String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement( locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM( String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement( locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean areJQueryAndJSLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage( String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement( locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public WebElement getElement( String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return "thuynguyen" + rand.nextInt(9999) + "@gmail.com";
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
