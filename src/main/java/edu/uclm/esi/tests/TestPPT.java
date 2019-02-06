package edu.uclm.esi.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import edu.uclm.esi.mongolabels.dao.MongoBroker;

public class TestPPT {
	private WebDriver driverLuismi, driverAmanda;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public TestPPT() {
		System.setProperty("webdriver.gecko.driver", "/Users/Luismi/geckodriver");
		//System.setProperty("webdriver.chrome.driver", "/Users/Luismi/chromedriver");
	}

	@Before
	public void setUp() throws Exception {
		driverLuismi = new FirefoxDriver();
		driverAmanda = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driverLuismi.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driverAmanda.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void mainTest() throws Exception {
		driverLuismi.get("http://localhost:8080/index.html");
		driverAmanda.get("http://localhost:8080/index.html");
		testLogin();
		testElegirJuego();
		testJoinGame();
		testPartida();
	}
	
	public void testLogin() throws Exception {
		driverLuismi.findElement(By.id("lUserName")).click();
		driverAmanda.findElement(By.id("lUserName")).click();
		driverLuismi.findElement(By.id("lUserName")).clear();
		driverAmanda.findElement(By.id("lUserName")).clear();
		driverLuismi.findElement(By.id("lUserName")).sendKeys("luismi");
		driverAmanda.findElement(By.id("lUserName")).sendKeys("amanda");
		
		driverLuismi.findElement(By.id("lPwd")).click();
		driverAmanda.findElement(By.id("lPwd")).click();
		driverLuismi.findElement(By.id("lPwd")).clear();
		driverAmanda.findElement(By.id("lPwd")).clear();
		driverLuismi.findElement(By.id("lPwd")).sendKeys("luismi1234");
		driverAmanda.findElement(By.id("lPwd")).sendKeys("amanda123");

		driverLuismi.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='He olvidado mi contraseña'])[1]/following::button[1]")).click();
		driverAmanda.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='He olvidado mi contraseña'])[1]/following::button[1]")).click();
	}
	
	public void testElegirJuego() throws Exception {
	    driverLuismi.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Elige un juego:'])[1]/following::button[1]")).click();
	    driverAmanda.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Elige un juego:'])[1]/following::button[1]")).click();
	}
	
	public void testJoinGame() throws Exception {
	    driverLuismi.findElement(By.id("join")).click();
	    driverAmanda.findElement(By.id("join")).click();
	}
	
	public void testPartida() throws Exception {
	    driverLuismi.findElement(By.id("piedra")).click();	
	    driverAmanda.findElement(By.id("papel")).click(); 
	    
		Thread.sleep(5000);
		
	    assertTrue(driverLuismi.findElement(By.id("winner")).getText().equals("¡Has perdido!"));
	    assertTrue(driverAmanda.findElement(By.id("winner")).getText().equals("¡Has ganado!"));
	}

	@After
	public void tearDown() throws Exception {
		driverLuismi.quit();
		driverAmanda.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverLuismi.findElement(by);
			driverAmanda.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverLuismi.switchTo().alert();
			driverAmanda.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverLuismi.switchTo().alert();
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
