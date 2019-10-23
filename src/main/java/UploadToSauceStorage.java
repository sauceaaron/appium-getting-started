import com.saucelabs.saucerest.SauceREST;

import java.io.IOException;
import java.io.InputStream;

public class UploadToSauceStorage
{
	static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	public static void main(String[] args) throws IOException
	{
		String appName = "SwagLabs.apk";
		InputStream stream = UploadToSauceStorage.class.getResourceAsStream(appName);

		SauceREST api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
		api.uploadFile(stream, appName, false);
	}
}