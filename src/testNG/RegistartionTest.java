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

public class RegistartionTest {
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
	public Iterator<Object[]> getExcelDatas() throws IOException {
		ArrayList<Object[]> a = ExcelUtil.Lista();
		return a.iterator();
	}
	

	@Test(dataProvider = "getExcelDatas",groups="Registration")
	public void registrationTest(String firstName, String lastName, String userName, String email, String password) {
		
		WebElement first = dr.findElement(By.xpath(Constant.FIRST_NAME));
		first.clear();
		first.sendKeys(firstName);
		
		WebElement last=dr.findElement(By.xpath(Constant.LAST_NAME));
		last.clear();
		last.sendKeys(lastName);
		
		WebElement user=dr.findElement(By.xpath(Constant.USER_NAME_REG));
		user.clear();
		user.sendKeys(userName);
		
		WebElement em=dr.findElement(By.xpath(Constant.EMAIL));
		em.clear();
		em.sendKeys(email);
		
		WebElement pass=dr.findElement(By.xpath(Constant.PASWORD_REG));
		pass.clear();
		pass.sendKeys(password);

		dr.findElement(By.xpath(Constant.REGISTER_BUTTON)).click();
		
		
	}
	

	@AfterTest
	public static void closeBrowser() {
		dr.quit();
	}
}
