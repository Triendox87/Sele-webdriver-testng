package webdrivers;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterUserAlada {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.navigate().to("https://alada.vn/tai-khoan/dang-ky.html");
  }

  
  @Test(priority = 1)
  public void TC_01() {
	  //register with empty field
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String errorFullName = driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText();
	  String errorEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
	  String errorCEmail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
	  String errorPassWord = driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
	  String errorCPassWord = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
	  String errorPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	  Assert.assertEquals(errorFullName, "Vui lòng nhập họ tên");
	  Assert.assertEquals(errorEmail, "Vui lòng nhập email");
	  Assert.assertEquals(errorCEmail, "Vui lòng nhập lại địa chỉ email");
	  Assert.assertEquals(errorPassWord, "Vui lòng nhập mật khẩu");
	  Assert.assertEquals(errorCPassWord, "Vui lòng nhập lại mật khẩu");
	  Assert.assertEquals(errorPhone, "Vui lòng nhập số điện thoại.");
  }
  
  @Test(priority = 2)
  public void TC_02() throws InterruptedException {
	//Register incorrect confirm email and email
	  driver.findElement(By.id("txtFirstname")).sendKeys("Trien Do");	  
	  driver.findElement(By.name("txtEmail")).sendKeys("2321312@dsdfd@gcom");
	  driver.findElement(By.name("txtCEmail")).sendKeys("2321312@dsdfd@om");
	  driver.findElement(By.name("txtPassword")).sendKeys("12345@Fra");
	  driver.findElement(By.name("txtCPassword")).sendKeys("12345@Fra");
	  driver.findElement(By.name("txtPhone")).sendKeys("0902955998");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String errorEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
	  String errorCEmail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
	  Assert.assertEquals(errorEmail, "Vui lòng nhập email hợp lệ");
	  Assert.assertEquals(errorCEmail, "Email nhập lại không đúng");
	  Thread.sleep(2000);
  }
  
  @Test(priority = 3)
  public void TC_03() {
	  //Register incorrect confirm email
	  driver.findElement(By.id("txtFirstname")).clear();	  
	  driver.findElement(By.name("txtEmail")).clear();
	  driver.findElement(By.name("txtCEmail")).clear();
	  driver.findElement(By.name("txtPassword")).clear();
	  driver.findElement(By.name("txtCPassword")).clear();
	  driver.findElement(By.name("txtPhone")).clear();
	  
	  driver.findElement(By.id("txtFirstname")).sendKeys("Trien Do");	  
	  driver.findElement(By.name("txtEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtCEmail")).sendKeys("2321312@dsdfd@om");
	  driver.findElement(By.name("txtPassword")).sendKeys("12345@Fra");
	  driver.findElement(By.name("txtCPassword")).sendKeys("12345@Fra");
	  driver.findElement(By.name("txtPhone")).sendKeys("0902955998");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String errorCEmail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
	  Assert.assertEquals(errorCEmail, "Email nhập lại không đúng");	
  }
  
  @Test(priority = 4)
  public void TC_04() {
	  //Password < 6 characters
	  driver.findElement(By.id("txtFirstname")).clear();	  
	  driver.findElement(By.name("txtEmail")).clear();
	  driver.findElement(By.name("txtCEmail")).clear();
	  driver.findElement(By.name("txtPassword")).clear();
	  driver.findElement(By.name("txtCPassword")).clear();
	  driver.findElement(By.name("txtPhone")).clear();
	  
	  driver.findElement(By.id("txtFirstname")).sendKeys("Trien Do");	  
	  driver.findElement(By.name("txtEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtCEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtPassword")).sendKeys("1234");
	  driver.findElement(By.name("txtCPassword")).sendKeys("1234");
	  driver.findElement(By.name("txtPhone")).sendKeys("0902955998");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String errorPassword = driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
	  String errorCPassword = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
	  Assert.assertEquals(errorPassword, "Mật khẩu phải có ít nhất 6 ký tự");
	  Assert.assertEquals(errorCPassword, "Mật khẩu phải có ít nhất 6 ký tự");	  
  }
  
  @Test(priority = 5)
  public void TC_05() {	  
	  //Register incorrect Confirm Password
	  driver.findElement(By.id("txtFirstname")).clear();	  
	  driver.findElement(By.name("txtEmail")).clear();
	  driver.findElement(By.name("txtCEmail")).clear();
	  driver.findElement(By.name("txtPassword")).clear();
	  driver.findElement(By.name("txtCPassword")).clear();
	  driver.findElement(By.name("txtPhone")).clear();
	  
	  driver.findElement(By.id("txtFirstname")).sendKeys("Trien Do");	  
	  driver.findElement(By.name("txtEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtCEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtPassword")).sendKeys("12345@Fra");
	  driver.findElement(By.name("txtCPassword")).sendKeys("12345@Hgb");
	  driver.findElement(By.name("txtPhone")).sendKeys("0902955998");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String errorCPassword = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
	  System.out.print(errorCPassword);
	  Assert.assertEquals(errorCPassword, "Mật khẩu bạn nhập không khớp");	  
  }
  
  @Test(priority = 6)
  public void TC_06() throws InterruptedException {	  
	  //Register invalid phone number
	  driver.findElement(By.id("txtFirstname")).clear();	  
	  driver.findElement(By.name("txtEmail")).clear();
	  driver.findElement(By.name("txtCEmail")).clear();
	  driver.findElement(By.name("txtPassword")).clear();
	  driver.findElement(By.name("txtCPassword")).clear();
	  driver.findElement(By.name("txtPhone")).clear();
	  
	  driver.findElement(By.id("txtFirstname")).sendKeys("Trien Do");	  
	  driver.findElement(By.name("txtEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtCEmail")).sendKeys("2321312@gmail.com");
	  driver.findElement(By.name("txtPassword")).sendKeys("1234");
	  driver.findElement(By.name("txtCPassword")).sendKeys("1234");
	  driver.findElement(By.name("txtPhone")).sendKeys("0902");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  String errorPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	  Assert.assertEquals(errorPhone, "Số điện thoại phải từ 10-11 số.");
	  Thread.sleep(2000);
	  driver.findElement(By.name("txtPhone")).clear();
	  driver.findElement(By.name("txtPhone")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  Thread.sleep(2000);
	  String errorPhone1 = driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
	  Assert.assertEquals(errorPhone1, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
