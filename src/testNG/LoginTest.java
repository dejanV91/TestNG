package testNG;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	static WebDriver dr;

	@BeforeTest
	public static void setBrowser() throws InterruptedException {
		dr = new ChromeDriver();
		dr.get("http://localhost/izlet/");
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		Thread.sleep(2000);
	}
	
	@DataProvider
	public Iterator<Object[]> getLogInData() throws IOException {
		ArrayList<Object[]> a=ExcelUtil.LogIn();
		return a.iterator();	
	}
	
	@Test(priority=1,groups="LogIn")
	public void title() {
		String tl=dr.getTitle();
		assertEquals(tl,"Home");
	}
	
	@Test(dataProvider="getLogInData", priority=2,groups="LogIn")
	public void tstLogIn(String userName, String password) {
		dr.navigate().to(Constant.HOME_PAGE);
		
		WebElement user=dr.findElement(By.xpath(Constant.USER_NAME_LOG));
		user.clear();
		user.sendKeys(userName);
		
		WebElement pass=dr.findElement(By.xpath(Constant.PASSWORD_LOG));
		pass.clear();
		pass.sendKeys(password);
		
		dr.findElement(By.xpath(Constant.LOG_IN_BUTTON)).click();
		
		String act=dr.getCurrentUrl();
		
		assertEquals(act, Constant.SUCCESS_LOG);
	}

	@AfterTest
	public static void closeBrowser() {
		dr.quit();
	}
}
