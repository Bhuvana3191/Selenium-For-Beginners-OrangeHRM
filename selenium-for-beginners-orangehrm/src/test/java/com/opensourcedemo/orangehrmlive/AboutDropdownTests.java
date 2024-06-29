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

public class AboutDropdownTests {

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
	public void aboutDropdownTest() {
//		Test case 1: About Dropdown Test
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

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		 Click user on the new page url 
		WebElement userButton = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		userButton.click();
		sleep(1);

//		Verify About Button is displayed
		WebElement aboutButton = driver.findElement(By.xpath("//a[text()='About']"));
		Assert.assertTrue(aboutButton.isDisplayed(), "About button is not visible");

//		Click About Button
		aboutButton.click();
		sleep(1);

//		Verify About Title is displayed
		WebElement aboutTitle = driver.findElement(By.xpath("//h6[text()='About']"));
		Assert.assertTrue(aboutTitle.isDisplayed(), "About title is not visible");

//		Verify About Description is displayed
		WebElement aboutDesc = driver.findElement(By.xpath("//div[@class='oxd-grid-2 orangehrm-about']"));
		Assert.assertTrue(aboutDesc.isDisplayed(), "About description is not visible");

//		Click Close Button
		WebElement closeButton = driver
				.findElement(By.xpath("//button[text()='×']"));
		closeButton.click();
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
