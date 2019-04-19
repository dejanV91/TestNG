package testNG;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OneLogInAndReg {
	static WebDriver dr;
	
	@BeforeTest
	public static void setBrowser() throws InterruptedException {
		dr = new ChromeDriver();
		dr.get("http://localhost/izlet/");
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		Thread.sleep(2000);
	}
	
	@Test(groups="Registration")
	@Parameters({"first","last", "user", "email", "pass"})
	public void oneRegis(String first,String last,String user, String email, String pass) {
		WebElement f = dr.findElement(By.xpath(Constant.FIRST_NAME));
		f.clear();
		f.sendKeys(first);
		
		WebElement l=dr.findElement(By.xpath(Constant.LAST_NAME));
		l.clear();
		l.sendKeys(last);
		
		WebElement u=dr.findElement(By.xpath(Constant.USER_NAME_REG));
		u.clear();
		u.sendKeys(user);
		
		WebElement em=dr.findElement(By.xpath(Constant.EMAIL));
		em.clear();
		em.sendKeys(email);
		
		WebElement pas=dr.findElement(By.xpath(Constant.PASWORD_REG));
		pas.clear();
		pas.sendKeys(pass);

		dr.findElement(By.xpath(Constant.REGISTER_BUTTON)).click();
		
	}
	
	@Test (dependsOnGroups="Registration",groups="LogIn")
	@Parameters({"user","pass"})
	public void OneLogIn(String u,String p) {
		WebElement user=dr.findElement(By.xpath(Constant.USER_NAME_LOG));
		user.clear();
		user.sendKeys(u);
		
		WebElement pass=dr.findElement(By.xpath(Constant.PASSWORD_LOG));
		pass.clear();
		pass.sendKeys(p);
		
		dr.findElement(By.xpath(Constant.LOG_IN_BUTTON)).click();
		
		String act=dr.getCurrentUrl();
		
		assertEquals(act, Constant.SUCCESS_LOG);
	}
	
	@AfterTest
	public static void closeBrowser() {
		dr.quit();
	}
}
