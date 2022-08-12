package tests;

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

public class AddCart {

	// Once logged into https://www.saucedemo.com/ test Add to Cart functionality

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
	// Testing Add to cart
	public void AddCart() {
		System.out.println("Locating fields");
		WebElement usrname = driver.findElement(By.id("user-name"));
		WebElement passwd = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		System.out.println("Input credentials and login");
		usrname.sendKeys("standard_user");
		passwd.sendKeys("secret_sauce");
		loginBtn.click();
		driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
		driver.findElement(By.linkText("1")).click();

		// Validation
		String actualUrl = "https://www.saucedemo.com/cart.html";
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);

		System.out.println("Test completed, closing browser....");

	}

	@After

	public void tearDown() {
		driver.quit();

	}
}
