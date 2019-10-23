import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class AppiumEmulatorAppTest
{
	static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	static final String SAUCE_URL =
			"https://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com/wd/hub"
			.replace("SAUCE_USERNAME", SAUCE_USERNAME)
			.replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);

	AppiumDriver driver;

	@Rule
	public SauceTestWatcher watcher =
			new SauceTestWatcher(SAUCE_USERNAME, SAUCE_ACCESS_KEY);

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("browserName", "");
		capabilities.setCapability("app", "sauce-storage:SwagLabs.apk");
		capabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("name", "Appium Emulator App Test");
		capabilities.setCapability("build", "Appium Getting Started");

		driver = new AndroidDriver(url, capabilities);
		watcher.forSession(driver.getSessionId().toString());
	}

	@Test
	public void openApp()
	{
		MobileElement usernameField = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		usernameField.sendKeys("standard_user");

		MobileElement passwordField = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		passwordField.sendKeys("secret_sauce");

		MobileElement loginButton = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
		loginButton.click();

		MobileElement heading = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView");
		System.out.println(heading.getText());

		assertThat(heading.getText()).isEqualTo("PRODUCTS");

//		AndroidDriver<MobileElement> android = (AndroidDriver<MobileElement>) driver;
//		MobileElement productsHeading = android.findElementByAndroidUIAutomator(("new UiSelector().text(\"PRODUCTS\")"));
	}

	@After
	public void cleanup()
	{
		driver.quit();
	}
}
