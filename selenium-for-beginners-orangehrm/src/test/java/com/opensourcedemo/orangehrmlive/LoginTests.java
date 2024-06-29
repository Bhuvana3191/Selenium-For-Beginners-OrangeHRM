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

public class LoginTests {

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

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveLoginTest() {
//		Test case 1: Positive LogIn test
		System.out.println("Test Started");

//		Open page
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

//		Type username Admin into Username field
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
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
		WebElement dashboardTab = driver.findElement(By.xpath("//span[text()='Dashboard']"));
		Assert.assertTrue(dashboardTab.isDisplayed(), "Dashboard tab is not visible");

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		 Click user on the new page url 
		WebElement userButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		userButton.click();
		sleep(1);

//		Verify Log out Button is displayed
		WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Logout']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

//		Click Log out Button
		logoutButton.click();
		sleep(1);

//		Verify new page URL after clicking log out button
		String expectedloginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String actualloginUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualloginUrl, expectedloginUrl, "Actual Login Url is not as same as expected");

		sleep(1);

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
//		Test case 2: Negative username test
		System.out.println("Test Started");

//		Open page
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

//		Type incorrect into Username field
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

		usernameElement.sendKeys(username);

//		Type password into Password field
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

//		Click LogIn Button button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
		sleep(1);

//		Verifications:
//		Verify error message is displayed
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement errorMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[text()='Invalid credentials']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error Message is not visible");

//		Verify error message text is Invalid credentials
		String actualErrorMessage = errorMessage.getText();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
				"Actual Error Message is not as same as expected");
	}

	@Test(priority = 3, groups = { "negativeNoUserPasswordTests", "smokeTests" })
	public void negativeNoUserPasswordTest() {
		// Test case 4: Negative No User Password Test
		System.out.println("Test Started");

		// Open page String url =
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

		// No username is typed into Username field
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

		username.sendKeys("");

		// No password is typed into Password field
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("");

		// Click LogIn Button button WebElement logInButton
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();
		sleep(1);

		// Verifications:
		// Verify Required Message is displayed if username input field are empty
		WebElement reqErrorUserMessage = driver.findElement(By.xpath(
				"//div[@id='app']/div[@class='orangehrm-login-layout']//form[@action='/web/index.php/auth/validate']/div[1]/div/span[.='Required']"));
		Assert.assertTrue(reqErrorUserMessage.isDisplayed(), "Error Message is not visible");

		// Verify Required error message text is "Required"
		String expectedReqErrorUserMessage = "Required";
		String actualReqErrorUserMessage = reqErrorUserMessage.getText();
		Assert.assertEquals(actualReqErrorUserMessage, expectedReqErrorUserMessage,
				"Actual Error Username Message is not as same as expected");

		// Verify Required Message is displayed if password input field are empty
		WebElement reqErrorPassMessage = driver.findElement(By.xpath(
				"//div[@id='app']/div[@class='orangehrm-login-layout']//form[@action='/web/index.php/auth/validate']/div[2]/div/span[.='Required']"));
		Assert.assertTrue(reqErrorPassMessage.isDisplayed(), "Error Message is not visible");

		// Verify Required error message text is "Required"
		String expectedReqErrorPassMessage = "Required";
		String actualReqErrorPassMessage = reqErrorPassMessage.getText();
		Assert.assertEquals(actualReqErrorPassMessage, expectedReqErrorPassMessage,
				"Actual Error Password Message is not as same as expected");

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
