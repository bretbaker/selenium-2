package selenium2;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeBrowser {
	
//	Initialize WebDriver
	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Start of test class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("End of test class");
//		if needed closes all open browser windows
//		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Start of test method");
//		if standard executable is working go with:
//		WebDriverManager.chromedriver().setup();
//		^^ usualy doesn't work for my machine so instead I go with:
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\bretb\\Desktop\\chrome-driver-selenium\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("End of test method");
//		driver.close is used to close the current browser so good for @After
		driver.close();
//		driver.quit is used to close all open browser windows so good for @AfterClass
//		driver.quit()
	}

	@Test
	public void initBrowser() {
		try {
			driver.get("http://www.google.com");
//			sleep(3);
		} catch (Exception e) {
			fail("fail");
		}
	}
	
	@Test
	public void maximizeWindow() {
		try {
			driver.get("http://www.google.com");
			driver.manage().window().maximize();
//			sleep(3);
		} catch (Exception e) {
			fail("fail");
		}
	}
	
	@Test
	public void impWait() {
		try {
			driver.get("http://www.google.com");
			driver.manage().window().maximize();
			WebElement searchBar = driver.findElement(By.name("q"));
			searchBar.sendKeys("weather");
			searchBar.sendKeys(Keys.RETURN);
//			WebElement searchBtn = driver.findElement(By.name("btnK"));
//			searchBtn.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			List<WebElement> linkTexts = driver.findElements(By.className("iUh30"));
			String firstLinkText = linkTexts.get(0).getText();
			System.out.println(firstLinkText);
			sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
			fail("fail");
		}
	}
	
	@Test
	public void expWait() {
		try {
			driver.get("http://www.google.com");
			driver.manage().window().maximize();
			WebElement searchBar = driver.findElement(By.name("q"));
			searchBar.sendKeys("weather");
			searchBar.sendKeys(Keys.RETURN);
//			WebElement searchBtn = driver.findElement(By.name("btnK"));
//			searchBtn.click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement tempBtn = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("wob_temp"))));
			String tempBtnText = tempBtn.getText();
			System.out.println(tempBtnText);
			sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
			fail("fail");
		}
	}
	
	@Test 
	public void alertPractice() {
		try {
			/*
			 * three ways to handle alerts in selenium
			 * accept, just clicks ok
			 * dismiss, closes alert without accepting
			 * sendkeys, sends text then either accept or dismiss
			 */
			driver.get("https://www.tutorialsteacher.com/codeeditor?cid=js-1");
			driver.manage().window().maximize();
			WebElement btn = driver.findElement(By.id("btnRun"));
			sleep(3);
			btn.click();
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println(alertText);
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
			fail("fail");
		}
	}
	
	static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
