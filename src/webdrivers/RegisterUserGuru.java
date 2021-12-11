package webdrivers;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterUserGuru {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	static String emailAddress;
	static String firstname = "Trien";
	static String middlename = "Dang";
	static String lastname = "Do";	
	static String fullName = firstname +" "+ middlename+" "+ lastname;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("http://live.techpanda.org/");
	}

	@Test(priority = 1)
	public void TC_01() {
		// login empty email and password
		driver.findElement(By.xpath("(//a[@title='My Account'][normalize-space()='My Account'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		String errorEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String errorPassWord = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(errorEmail, "This is a required field.");
		Assert.assertEquals(errorPassWord, "This is a required field.");
	}

	@Test(priority = 2)
	public void TC_02() {
		// login invalid email
		driver.findElement(By.xpath("(//a[@title='My Account'][normalize-space()='My Account'])[2]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123432@4343.4");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234556");
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		String errorEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorEmail, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test(priority = 3)
	public void TC_03() {
		// login password < 6 characters
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		String errorPassWord = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(errorPassWord, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test(priority = 4)
	public void TC_04() {
		// login incorrect email and password

		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		String errorEmailPassWord = driver
				.findElement(By.xpath("//span[normalize-space()='Invalid login or password.']")).getText();
		Assert.assertEquals(errorEmailPassWord, "Invalid login or password.");
	}

	@Test(priority = 5)
	public void TC_05() throws InterruptedException {			
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		driver.findElement(By.name("firstname")).sendKeys(firstname);
		driver.findElement(By.id("middlename")).sendKeys(middlename);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		Random randomGenerator = new Random();		
		int randomInt = randomGenerator.nextInt(1000);
		emailAddress = "username" + randomInt + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys("123@Abc");
		driver.findElement(By.name("confirmation")).sendKeys("123@Abc");
		driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
		String msgCreateUser = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span")).getText();
		System.out.print("######Message######: " + msgCreateUser + '\n');

		String dashboardText = driver.findElement(By.cssSelector("div[class='page-title'] h1")).getText();
		System.out.print("########Text########: " + dashboardText + '\n');

		String fullnameText = driver.findElement(By.cssSelector("p[class='hello'] strong")).getText();
		System.out.print("########Message########: " + fullnameText + '\n');
		Assert.assertEquals(msgCreateUser, "Thank you for registering with Main Website Store.");
		Assert.assertEquals(dashboardText, "MY DASHBOARD");
		Assert.assertEquals(fullnameText,"Hello,"+" "+ firstname +" "+ middlename+" "+ lastname+"!");
		driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();

		String urlLink = driver.getCurrentUrl();
		String msgredirected = driver.findElement(By.xpath("//p[contains(text(),'You have logged out and will be redirected to our homepage in 5 seconds.')]")).getText();
		String msgLogout = driver.findElement(By.xpath("//h1[normalize-space()='You are now logged out']")).getText();
		System.out.print("########URL########: " + urlLink + '\n');
		Assert.assertEquals(urlLink, "http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
		Assert.assertEquals(msgredirected, "You have logged out and will be redirected to our homepage in 5 seconds.");
		Assert.assertEquals(msgLogout, "YOU ARE NOW LOGGED OUT");
		Thread.sleep(5000);
		String urlHomePage = driver.getCurrentUrl();
		Assert.assertEquals(urlHomePage, "http://live.techpanda.org/index.php/");

	}
	
	@Test(priority = 6)
	public void TC_06() throws InterruptedException {
		driver.findElement(By.xpath("(//a[@title='My Account'][normalize-space()='My Account'])[2]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123@Abc");
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();

		String dashboardText = driver.findElement(By.cssSelector("div[class='page-title'] h1")).getText();		
		System.out.print("########dashboard########: " + dashboardText + '\n');

		String fullnameHello = driver.findElement(By.cssSelector("p[class='hello'] strong")).getText();
		System.out.print("########fullname########: " + fullnameHello + '\n');		
		

		String contactText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div")).getText();
		System.out.print("########contactText########: " + contactText + '\n');	
		Assert.assertTrue(contactText.contains(fullName));
		Assert.assertTrue(contactText.contains(emailAddress));
		
		Assert.assertEquals(dashboardText, "MY DASHBOARD");
		Assert.assertEquals(fullnameHello,"Hello,"+" "+ firstname +" "+ middlename+" "+ lastname+"!");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
