package edu.uclm.esi.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestGames {
	private WebDriver driverPepe, driverAna;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public TestGames() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\asg_9\\OneDrive\\Escritorio\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\asg_9\\OneDrive\\Escritorio\\chromedriver.exe");

	}

	@Before
	public void setUp() throws Exception {
		driverPepe = new FirefoxDriver();
		driverAna = new FirefoxDriver();
		baseUrl = "https://www.katalon.com/";
		driverPepe.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driverAna.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testGame() throws Exception {
		login();
		
		/*JoinGame*/
		driverPepe.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/a[3]")).click();
		driverAna.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/a[3]")).click();
		
		/*Jugada 1*/
		driverPepe.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/a[4]")).click();
		driverAna.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/a[4]")).click();
		
		/*Jugada 2*/
		driverPepe.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/a[5]")).click();
		driverAna.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/a[5]")).click();
		
		/*Jugada 3*/
		driverPepe.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/a[6]")).click();
		driverAna.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/a[6]")).click();
		
		Thread.sleep(500);
		String textoquetienequeestar = "\"winner\":{\"userName\":\"ana\",\"email\":\"ana@ana.com\"}";
		assertTrue(driverPepe.getPageSource().contains(textoquetienequeestar));
		assertTrue(driverAna.getPageSource().contains(textoquetienequeestar));
	}
	
	private void login () throws Exception {
		driverPepe.get("http://localhost:8080/index.html");
		driverPepe.findElement(By.linkText("Piedra, papel, tijera")).click();
		
		driverAna.get("http://localhost:8080/index.html");
		driverAna.findElement(By.linkText("Piedra, papel, tijera")).click();
		
		driverPepe.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]/a[2]")).click();
		driverAna.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/a[2]")).click();
		
		assertTrue(driverPepe.getPageSource().contains("userName"));
		assertTrue(driverAna.getPageSource().contains("userName"));
		/*driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Login'])[2]/following::option[1]"))
				.click();
		driver.findElement(By.xpath("//html")).click();*/
	
	}

	@After
	public void tearDown() throws Exception {
		driverPepe.quit();
		driverAna.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverPepe.findElement(by);
			driverAna.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverPepe.switchTo().alert();
			driverAna.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
