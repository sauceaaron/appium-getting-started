import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloAppiumWebTest
{
	static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	static final String SAUCE_URL = "https://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com/wd/hub"
			.replace("SAUCE_USERNAME", SAUCE_USERNAME)
			.replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);

	RemoteWebDriver driver;

	@Rule
	public SauceTestWatcher watcher = new SauceTestWatcher(SAUCE_USERNAME, SAUCE_ACCESS_KEY);

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("name", "Hello Appium Web Test");
		capabilities.setCapability("build", "Appium Getting Started");

		driver = new RemoteWebDriver(url, capabilities);
		watcher.forSession(driver.getSessionId().toString());
	}

	@Test
	public void openHomePage()
	{
		driver.get("https://www.saucedemo.com");
		String title = driver.getTitle();

		assertThat(title).isEqualTo("Swag Labs");
	}

	@After
	public void cleanup()
	{
		driver.quit();
	}
}
