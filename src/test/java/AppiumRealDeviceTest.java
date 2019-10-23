import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AppiumRealDeviceTest
{
	static final String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY");

	AppiumDriver<MobileElement> driver;

	@Before
	public void setup() throws MalformedURLException
	{
		/** specify the appium server (testobject US or EU datacenter) **/
		URL url = new URL("https://us1.appium.testobject.com/wd/hub");

		/** specify desired capabilities **/
		DesiredCapabilities capabilities = new DesiredCapabilities();

		/** set testobject capabililites **/
		capabilities.setCapability("testobject_api_key", TESTOBJECT_API_KEY);
		capabilities.setCapability("testobject_suite_name", "Appium Getting Started");
		capabilities.setCapability("testobject_test_name", "Swag Lags Login Test");

		/** set device capabilities **/
		capabilities.setCapability("platformName", "iOS");
//		capabilities.setCapability("platformVersion", "12.2");
		capabilities.setCapability("deviceName", "iPhone X");
//		capabilities.setCapability("appiumVersion", "1.13.0");
//		capabilities.setCapability("orientation", "PORTRAIT");
//		capabilities.setCapability("phoneOnly", "false");
//		capabilities.setCapability("tabletOnly", "false");
//		capabilities.setCapability("privateDevicesOnly", "true");

		driver = new IOSDriver<>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void loginFailureTest()
	{
//		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("test-Username");
//		el1.sendKeys("standard_user");
//		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("test-Password");
//		el2.sendKeys("invalid_password");
//		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
//		el3.click();
//		MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Username and password do not match any user in this service.");

//		driver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
//		driver.findElementByAccessibilityId("test-Password").sendKeys("invalid_password");
//		driver.findElementByAccessibilityId("test-LOGIN").click();

		By usernameField = MobileBy.AccessibilityId("test-Username");
		By passwordField = MobileBy.AccessibilityId("test-Password");
		By loginButton = MobileBy.AccessibilityId("test-LOGIN");

		driver.findElement(usernameField).sendKeys("standard_user");
		driver.findElement(passwordField).sendKeys("invalid_password");
		driver.findElement(loginButton).click();

		String expectedErrorMessage = "Username and password do not match any user in this service.";
		By errorDialog = MobileBy.AccessibilityId(expectedErrorMessage);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String message = wait.until(presenceOfElementLocated(errorDialog)).getText();
		System.out.println(message);
		assertThat(message).isEqualTo(expectedErrorMessage);
	}

	@After
	public void cleanup()
	{
		driver.quit();
	}
}