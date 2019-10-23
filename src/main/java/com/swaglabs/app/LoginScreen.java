package com.swaglabs.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen
{
	AppiumDriver driver;

	@iOSBy(accessibility="test-Username")
	@AndroidBy(accessibility="test-Username")
	protected MobileElement usernameField;

	@iOSBy(accessibility="test-Password")
	@AndroidBy(accessibility="test-Password")
	protected MobileElement passwordField;

	@iOSBy(accessibility="test-LOGIN")
	@AndroidBy(accessibility="test-LOGIN")
	protected MobileElement loginButton;

	public LoginScreen(AppiumDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Login(String username, String password)
	{
		usernameField.setValue(username);
		passwordField.setValue(password);
		loginButton.click();
	}
}