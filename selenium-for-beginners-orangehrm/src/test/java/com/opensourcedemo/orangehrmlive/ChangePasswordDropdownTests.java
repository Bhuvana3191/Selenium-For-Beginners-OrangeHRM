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

public class ChangePasswordDropdownTests {

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
	public void saveNewPasswordTest() {
//		Test case 1: Change Password Test with New Password is given and Save Button is clicked
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement dashboardTab = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
		Assert.assertTrue(dashboardTab.isDisplayed(), "Dashboard tab is not visible");

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		 Click user on the new page url 
		WebElement userButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		userButton.click();
		sleep(1);

//		Verify Change Password Button is displayed
		WebElement updPassButton = driver.findElement(By.xpath("//a[text()='Change Password']"));
		Assert.assertTrue(updPassButton.isDisplayed(), "Change Password button is not visible");

//		Click Change Password Button
		updPassButton.click();
		sleep(1);

//		Verify new Url after clicking Change Password Button
		String expectedupdPassUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/updatePassword";
		String actualupdPassUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualupdPassUrl, expectedupdPassUrl,
				"Actual Upadate Password url is not as same as expected");

//		Verify PIM Tab is displayed in Change Password Url
		WebElement PIMTab = driver.findElement(By.xpath("//span[text()='PIM']"));
		Assert.assertTrue(PIMTab.isDisplayed(), "PIM tab is not visible");

//		Verify PIM Title is displayed in Change Password Url
		WebElement PIMTitle = driver.findElement(By.xpath("//h6[text()='PIM']"));
		Assert.assertTrue(PIMTitle.isDisplayed(), "PIM title is not visible");

//		Verify Update Password title is displayed in Change Password Url
		WebElement updPassTitle = driver
				.findElement(By.xpath("//h6[text()='Update Password']"));
		Assert.assertTrue(updPassTitle.isDisplayed(), "Update Password title is not visible");

//		Enter value in Current Password input field 
		WebElement currPassword = driver.findElement(By.cssSelector(".oxd-form .oxd-form-row:nth-of-type(1) [type]"));
		currPassword.sendKeys("admin123");

//		Enter value in New Password input field 
		WebElement newPassword = driver.findElement(By.cssSelector(
				".oxd-grid-item.oxd-grid-item--gutters.user-password-cell > .oxd-input-field-bottom-space.oxd-input-group  .oxd-input"));
		newPassword.sendKeys("Password123!");

//		Enter value in Confirm New Password input field 
		WebElement confirmPassword = driver
				.findElement(By.cssSelector(".user-password-row .oxd-grid-item--gutters:nth-of-type(2) [type]"));
		confirmPassword.sendKeys("Password123!");

//		Click Save Button
		WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
		saveButton.click();
		sleep(1);

//		Verify Success Message after clicking Save Button
		wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Successfully Saved']")));
		Assert.assertTrue(successMessage.isDisplayed(), "Success Message is not visible");

		String expectedMessage = "Successfully Saved";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual Message does not contain Expected Message\nActual Message: " + actualMessage
						+ "\nExpected Message :" + expectedMessage);

	}

	@Test
	public void cancelNewPasswordTest() {
//		Test case 2: No Change of Password Test by clicking Cancel Button is clicked
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement dashboardTab = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
		Assert.assertTrue(dashboardTab.isDisplayed(), "Dashboard tab is not visible");

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		 Click user on the new page url 
		WebElement userButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		userButton.click();
		sleep(1);

//		Verify Change Password Button is displayed
		WebElement updPassButton = driver.findElement(By.xpath("//a[text()='Change Password']"));
		Assert.assertTrue(updPassButton.isDisplayed(), "Change Password button is not visible");

//		Click Change Password Button
		updPassButton.click();
		sleep(1);

//		Verify new Url after clicking Change Password Button
		String expectedupdPassUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/updatePassword";
		String actualupdPassUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualupdPassUrl, expectedupdPassUrl,
				"Actual Upadate Password url is not as same as expected");

//		Verify PIM Tab is displayed in Change Password Url
		WebElement PIMTab = driver.findElement(By.xpath("//span[text()='PIM']"));
		Assert.assertTrue(PIMTab.isDisplayed(), "PIM tab is not visible");

//		Verify PIM Title is displayed in Change Password Url
		WebElement PIMTitle = driver.findElement(By.xpath("//h6[text()='PIM']"));
		Assert.assertTrue(PIMTitle.isDisplayed(), "PIM title is not visible");

//		Verify Update Password title is displayed in Change Password Url
		WebElement updPassTitle = driver
				.findElement(By.xpath("//h6[text()='Update Password']"));
		Assert.assertTrue(updPassTitle.isDisplayed(), "Update Password title is not visible");

//		Click Cancel Button
		WebElement cancelButton = driver
				.findElement(By.xpath("//button[text()=' Cancel ']"));
		cancelButton.click();
		sleep(1);

//		Verify new Url after clicking Cancel Button
		String expectedCancelUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String actualCancelUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualCancelUrl, expectedCancelUrl, "Actual Cancel url is not as same as expected");

	}

	@Test
	public void reqPasswordTest() {
//		Test case 3: Change Password Test with Required Password field after clicking Save Button
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement dashboardTab = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
		Assert.assertTrue(dashboardTab.isDisplayed(), "Dashboard tab is not visible");

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		 Click user on the new page url 
		WebElement userButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		userButton.click();
		sleep(1);

//		Verify Change Password Button is displayed
		WebElement updPassButton = driver.findElement(By.xpath("//a[text()='Change Password']"));
		Assert.assertTrue(updPassButton.isDisplayed(), "Change Password button is not visible");

//		Click Change Password Button
		updPassButton.click();
		sleep(1);

//		Verify new Url after clicking Change Password Button
		String expectedupdPassUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/updatePassword";
		String actualupdPassUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualupdPassUrl, expectedupdPassUrl,
				"Actual Upadate Password url is not as same as expected");

//		Verify PIM Tab is displayed in Change Password Url
		WebElement PIMTab = driver.findElement(By.xpath("//span[text()='PIM']"));
		Assert.assertTrue(PIMTab.isDisplayed(), "PIM tab is not visible");

//		Verify PIM Title is displayed in Change Password Url
		WebElement PIMTitle = driver.findElement(By.xpath("//h6[text()='PIM']"));
		Assert.assertTrue(PIMTitle.isDisplayed(), "PIM title is not visible");

//		Verify Update Password title is displayed in Change Password Url
		WebElement updPassTitle = driver
				.findElement(By.xpath("//h6[text()='Update Password']"));
		Assert.assertTrue(updPassTitle.isDisplayed(), "Update Password title is not visible");

//		Enter value in Current Password input field 
		WebElement currPassword = driver.findElement(By.cssSelector(".oxd-form .oxd-form-row:nth-of-type(1) [type]"));
		currPassword.sendKeys("");

//		Enter value in New Password input field 
		WebElement newPassword = driver.findElement(By.cssSelector(
				".oxd-grid-item.oxd-grid-item--gutters.user-password-cell > .oxd-input-field-bottom-space.oxd-input-group  .oxd-input"));
		newPassword.sendKeys("");

//		Enter value in Confirm New Password input field 
		WebElement confirmPassword = driver
				.findElement(By.cssSelector(".user-password-row .oxd-grid-item--gutters:nth-of-type(2) [type]"));
		confirmPassword.sendKeys("");

//		Click Save Button
		WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
		saveButton.click();
		sleep(1);

		// Verify Required Message is displayed if Current Password input field is empty
		WebElement currreqErrorMessage = driver
				.findElement(By.cssSelector(".oxd-form .oxd-form-row:nth-of-type(1) .oxd-input-field-error-message"));
		Assert.assertTrue(currreqErrorMessage.isDisplayed(), "Error Message is not visible");

		String expectedCurrReqErrorMessage = "Required";
		String actualCurrReqErrorMessage = currreqErrorMessage.getText();
		Assert.assertEquals(actualCurrReqErrorMessage, expectedCurrReqErrorMessage,
				"Actual Current Password Error Message is not as same as expected");

		// Verify Required Message is displayed if New Password input field is empty
		WebElement newpassreqErrorMessage = driver.findElement(By.cssSelector(
				".oxd-grid-item.oxd-grid-item--gutters.user-password-cell  .oxd-input-field-error-message.oxd-input-group__message.oxd-text.oxd-text--span"));
		Assert.assertTrue(newpassreqErrorMessage.isDisplayed(), "Error Message is not visible");

		String expectedNewPassReqErrorMessage = "Required";
		String actualNewPassReqErrorMessage = newpassreqErrorMessage.getText();
		Assert.assertEquals(actualNewPassReqErrorMessage, expectedNewPassReqErrorMessage,
				"Actual New Password Error Message is not as same as expected");

		// Verify Passwords do not match Message is displayed if Confirm Password input field is empty
		WebElement confpassreqErrorMessage = driver.findElement(By.cssSelector(
				".user-password-row .oxd-grid-item--gutters:nth-of-type(2) .oxd-input-field-error-message"));
		Assert.assertTrue(confpassreqErrorMessage.isDisplayed(), "Error Message is not visible");

		String expectedConfPassReqErrorMessage = "Passwords do not match";
		String actualConfPassReqErrorMessage = confpassreqErrorMessage.getText();
		Assert.assertEquals(actualConfPassReqErrorMessage, expectedConfPassReqErrorMessage,
				"Actual Confirm Password Error Message is not as same as expected");
		
		sleep(2);

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
