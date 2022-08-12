package tests;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {

	// Test for cart checkout

	private static WebDriver driver;
	private static final String driverType = "webDriver.chrome.driver";
	private static String driverPath = "./src/test/resources/webDriver/chromedriver.exe";
	private String URL = " https://www.saucedemo.com/ ";

	@BeforeClass

	public static void setUpBeforeClass() {
		System.out.println("Starting tests");
		System.setProperty(driverType, driverPath);
		// driver.manage().window().maximize();

	}

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.get(URL);

	}

	@Test
	// Testing checkout
	public void checkOut() {
		System.out.println("Locating fields");
		WebElement usrname = driver.findElement(By.id("user-name"));
		WebElement passwd = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		System.out.println("Input credentials and login");
		usrname.sendKeys("standard_user");
		passwd.sendKeys("secret_sauce");
		loginBtn.click();
		// add wait
		WebDriverWait wbdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
		driver.findElement(By.linkText("1")).click();
		String cartCheckoutBtn = "*[data-test=\"checkout\"]";
		driver.findElement(By.cssSelector(cartCheckoutBtn)).click();
		// wait
		WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// finding webElements
		WebElement firstName = driver.findElement(By.id("first-name"));
		WebElement lastName = driver.findElement(By.id("last-name"));
		WebElement zipCode = driver.findElement(By.id("postal-code"));

		// Input values
		System.out.println("elements located, adding values");

		firstName.sendKeys("testUsrNm");
		lastName.sendKeys("testLn");
		zipCode.sendKeys("20101");
		driver.findElement(By.id("continue")).click();

		// wait
		WebDriverWait webdrwait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Validation

		String actualUrl = "https://www.saucedemo.com/checkout-step-two.html";
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);

		System.out.println("Test completed, closing browser....");

	}

	@After

	public void tearDown() {
		driver.quit();

	}
}
