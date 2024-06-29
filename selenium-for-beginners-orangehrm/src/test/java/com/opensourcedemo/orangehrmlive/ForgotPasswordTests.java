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

public class ForgotPasswordTests {

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
	public void resetPasswordTests() {
//		Test case 1: Reset Password Test
		System.out.println("Test Started");

//		Open page
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

//		Click forgot password link to reset password
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement forgotPasswordLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[text()='Forgot your password? ']")));

		forgotPasswordLink.click();
		sleep(1);

//		Verifications:
//		Verify the new url after forgot password link is clicked to request password reset
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not as same as expected");

//		verify username input field is displayed to reset password
		WebElement username = driver.findElement(By.name("username"));
		Assert.assertTrue(username.isDisplayed(), "Username input field is not visible");

//		Provide username value into input field to reset password
		username.sendKeys("Admin");

//		verify Reset Button is displayed to reset password
		WebElement resetButton = driver.findElement(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(resetButton.isDisplayed(), "Reset Button is not visible");

//		click Reset Button to reset password
		resetButton.click();
		sleep(1);

//		Verify the new url after Reset Button is clicked to send password reset link
		String expectedResetLinkUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";
		String actualResetLinkUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualResetLinkUrl, expectedResetLinkUrl,
				"Actual Reset Link Url is not as same as expected");

//		Reset Password success message - "Reset Password link sent successfully"
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h6")));
		Assert.assertTrue(successMessage.isDisplayed(), "Reset Password success message is not visible");
	}

	@Test
	public void cancelResetTests() {
//		Test case 2: Cancel Forgot Password Test
		System.out.println("Test Started");

//		Open page
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

//		Click forgot password link to reset password
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement forgotPasswordLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[text()='Forgot your password? ']")));

		forgotPasswordLink.click();
		sleep(1);

//		Verifications:
//		Verify the new url after forgot password link is clicked to request password reset
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not as same as expected");

//		verify Cancel Button is displayed to cancel forgot password
		WebElement cancelButton = driver.findElement(By.xpath("//button[@type='button']"));
		Assert.assertTrue(cancelButton.isDisplayed(), "Cancel Button is not visible");

// 		Click Cancel Button 
		cancelButton.click();
		sleep(1);

//		Return to login page Url after clicking Cancel Button
		String expectedcancelUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String actualcancelUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualcancelUrl, expectedcancelUrl, "Actual login page url is not as same as expected");
	}

	@Test
	public void noUsernameResetTests() {
//		Test case 3: No Username Reset Test
		System.out.println("Test Started");

//		Open page
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);

//		Click forgot password link to reset password
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement forgotPasswordLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[text()='Forgot your password? ']")));

		forgotPasswordLink.click();
		sleep(1);

//		Verifications:
//		Verify the new url after forgot password link is clicked to request password reset
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not as same as expected");

//		Provide username value into input field to reset password
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("");

//		click Reset Button to reset password
		WebElement resetButton = driver.findElement(By.xpath("//button[@type='submit']"));
		resetButton.click();

// 		Verify Required Message is displayed if username input field is empty
		WebElement reqErrorMessage = driver.findElement(By.xpath("//span[text()='Required']"));
		Assert.assertTrue(reqErrorMessage.isDisplayed(), "Error Message is not visible");

// 		Verify Required error message text is "Required" 
		String expectedReqErrorMessage = "Required";
		String actualReqErrorMessage = reqErrorMessage.getText();
		Assert.assertEquals(actualReqErrorMessage, expectedReqErrorMessage,
				"Actual Error Message is not as same as expected");

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
