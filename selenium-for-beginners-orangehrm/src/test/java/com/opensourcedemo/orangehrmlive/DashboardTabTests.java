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

public class DashboardTabTests {

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
	public void dashboardTabTest() {
//		Test case 1: Dashboard Tab Test
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
		WebElement dashboardTab = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
		Assert.assertTrue(dashboardTab.isDisplayed(), "Dashboard tab is not visible");

//		Verify Dashboard tab name is displayed 
		WebElement dashboardTabname = driver.findElement(By.tagName("h6"));
		Assert.assertTrue(dashboardTabname.isDisplayed(), "Dashboard tabname is not visible");

//		Verify Help icon is displayed
		WebElement helpIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-question-lg']"));
		Assert.assertTrue(helpIcon.isDisplayed(), "Help icon is not visible");

//		Verify Time at Work chart is displayed
		WebElement workTimeTitle = driver.findElement(By.xpath("//p[text()='Time at Work']"));
		Assert.assertTrue(workTimeTitle.isDisplayed(), "Time at Work title is not visible");

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement workTimeCard = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-attendance-card-profile']")));
		Assert.assertTrue(workTimeCard.isDisplayed(), "Time at Work Card is not visible");

		WebElement workTimeChart = driver.findElement(By.xpath("//div[@class='emp-attendance-chart']"));
		Assert.assertTrue(workTimeChart.isDisplayed(), "Time at Work Chart is not visible");

//		Verify My Actions Card is displayed
		WebElement myActionsTitle = driver.findElement(By.xpath("//p[text()='My Actions']"));
		Assert.assertTrue(myActionsTitle.isDisplayed(), "My Actions title is not visible");

		WebElement myActionsCard = driver.findElement(By.xpath("//div[@class='orangehrm-todo-list']"));
		Assert.assertTrue(myActionsCard.isDisplayed(), "My Actions card is not visible");

//		Verify Quick Launch Card is displayed
		WebElement quickLaunchTitle = driver.findElement(By.xpath("//p[text()='Quick Launch']"));
		Assert.assertTrue(quickLaunchTitle.isDisplayed(), "Quick Launch title is not visible");

		WebElement quickLaunchCard = driver.findElement(By.xpath("//div[@class='oxd-grid-3 orangehrm-quick-launch']"));
		Assert.assertTrue(quickLaunchCard.isDisplayed(), "Quick Launch card is not visible");

//		Verify Buzz Latest Posts card is displayed
		WebElement buzzLatestPostsTitle = driver.findElement(By.xpath("//p[text()='Buzz Latest Posts']"));
		Assert.assertTrue(buzzLatestPostsTitle.isDisplayed(), "Buzz Latest Posts title is not visible");

		WebElement buzzLatestPostsCard = driver
				.findElement(By.xpath("//div[@class='oxd-grid-1 orangehrm-buzz-widget']"));
		Assert.assertTrue(buzzLatestPostsCard.isDisplayed(), "Buzz Latest Posts card is not visible");

//		Verify Employee On Leave Today card is displayed
		WebElement empOnLeaveTodayTitle = driver.findElement(By.xpath("//p[text()='Employees on Leave Today']"));
		Assert.assertTrue(empOnLeaveTodayTitle.isDisplayed(), "Employee On Leave Today title is not visible");

		WebElement empOnLeaveTodayCard = driver
				.findElement(By.xpath("//div[@id='app']//div[@class='oxd-layout-context']/div/div[5]/div"));
		Assert.assertTrue(empOnLeaveTodayCard.isDisplayed(), "Employee On Leave Today card is not visible");

//		Verify Employee Distribution by Sub Unit card is displayed
		WebElement empDistBySubUnitTitle = driver
				.findElement(By.xpath("//p[text()='Employee Distribution by Sub Unit']"));
		Assert.assertTrue(empDistBySubUnitTitle.isDisplayed(),
				"Employee Distribution by Sub Unit title is not visible");

		WebElement empDistBySubUnitCard = driver.findElement(By.cssSelector(
				"div:nth-of-type(6) > .orangehrm-dashboard-widget.oxd-sheet.oxd-sheet--rounded.oxd-sheet--white"));
		Assert.assertTrue(empDistBySubUnitCard.isDisplayed(), "Employee Distribution by Sub Unit card is not visible");

//		Verify Employee Distribution by Location card is displaye
		WebElement empDistBySubLocTitle = driver
				.findElement(By.xpath("//p[text()='Employee Distribution by Location']"));
		Assert.assertTrue(empDistBySubLocTitle.isDisplayed(), "Employee Distribution by Location title is not visible");

		WebElement empDistBySubLocCard = driver.findElement(By.cssSelector(
				"div:nth-of-type(7) > .orangehrm-dashboard-widget.oxd-sheet.oxd-sheet--rounded.oxd-sheet--white"));
		Assert.assertTrue(empDistBySubLocCard.isDisplayed(), "Employee Distribution by Location card is not visible");

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
