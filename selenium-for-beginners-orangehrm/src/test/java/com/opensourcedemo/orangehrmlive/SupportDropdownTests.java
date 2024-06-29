package com.opensourcedemo.orangehrmlive;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SupportDropdownTests {

	private WebDriver driver;
	private WebDriverWait wait;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setup(@Optional("chrome") String browser) {
//		Create Driver
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			System.out.println("Browser Started");

			break;

		case "firefox":
			driver = new FirefoxDriver();
			System.out.println("Browser Started");

			break;

		default:
			System.out.println("Default Browser: " + browser);
			driver = new ChromeDriver();
			System.out.println("Browser Started");

			break;
		}

		driver.manage().window().maximize(); // Maximize the browser window
		sleep(1);
		System.out.println("Page is opened");

	}

	@Test
	public void supportDropdownTest() {
//		Test case 1: Support Dropdown Test
		System.out.println("Test Started");

//		Open page
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

//		Type username Admin into Username field
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

		username.sendKeys("Admin");

//		Type password admin123 into Password field
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("admin123");

//		Click LogIn Button 
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
		sleep(1);

//		Verifications:
//		Verify new page URL after clicking LogIn Button 
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not as same as expected");
		
//		Verify Dashboard tab in side menu bar is displayed after user logged in
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement dashboardTab = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
		Assert.assertTrue(dashboardTab.isDisplayed(), "Dashboard tab is not visible");

//		Verify Dashboard tab name is displayed 
		WebElement dashboardTabname = driver.findElement(By.tagName("h6"));
		Assert.assertTrue(dashboardTabname.isDisplayed(), "Dashboard tabname is not visible");

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		 Click user on the new page url 
		WebElement userButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		userButton.click();
		sleep(1);

//		Verify Support Button is displayed
		WebElement supportButton = driver.findElement(By.xpath("//a[text()='Support']"));
		Assert.assertTrue(supportButton.isDisplayed(), "Support button is not visible");

//		Click Support Button
		supportButton.click();
		sleep(1);
		
//		Verify new Url is displayed after clicking Support Button
		String expectedSupportUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/help/support";
		String actualSupportUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualSupportUrl, expectedSupportUrl, "Actual Support page url is not as same as expected");

//		Verify Support Title is displayed
		WebElement supportTitle = driver.findElement(By.xpath("//h6[text()='Getting Started with OrangeHRM']"));
		Assert.assertTrue(supportTitle.isDisplayed(), "Support title is not visible");

//		Verify Support Description is displayed
		WebElement supportDesc = driver.findElement(By.xpath("//div[@class='orangehrm-support']"));
		Assert.assertTrue(supportDesc.isDisplayed(), "Support description is not visible");
		sleep(1);

	}

	/**
	 * Stop the execution in given period of time - in seconds
	 * 
	 * @param seconds
	 */
	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void close() {
		// Close the browser
		driver.close();
		System.out.println("Test Finished");
	}

}
