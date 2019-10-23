import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class AppiumSimulatorAppTest
{
	static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	static final String SAUCE_URL =
			"https://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com/wd/hub"
			.replace("SAUCE_USERNAME", SAUCE_USERNAME)
			.replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);

	IOSDriver<MobileElement> driver;

	@Rule
	public SauceTestWatcher watcher =
			new SauceTestWatcher(SAUCE_USERNAME, SAUCE_ACCESS_KEY);

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "12.2");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		capabilities.setCapability("browserName", "");
		capabilities.setCapability("app", "sauce-storage:SwagLabs.app.zip");
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("name", "Appium Simulator App Test");
		capabilities.setCapability("build", "Appium Getting Started");

		driver = new IOSDriver<>(url, capabilities);
		watcher.forSession(driver.getSessionId().toString());
	}

	@Test
	public void openApp()
	{
		MobileElement usernameField = driver.findElementByAccessibilityId("test-Username");
		usernameField.sendKeys("standard_user");

		MobileElement passwordField = driver.findElementByAccessibilityId("test-Password");
		passwordField.sendKeys("secret_sauce");

		MobileElement loginButton = driver.findElementByAccessibilityId("test-LOGIN");
		loginButton.click();

		MobileElement heading = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]");

		System.out.println(heading.getText());
		assertThat(heading.getText()).isEqualTo("PRODUCTS");
	}

	@After
	public void cleanup()
	{
		driver.quit();
	}
}