import com.saucelabs.saucerest.SauceREST;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SauceTestWatcher extends TestWatcher
{
	public SauceREST api;
	public String sessionId;
	public boolean verbose = true;

	public SauceTestWatcher(String username, String accessKey)
	{
		this.api = new SauceREST(username, accessKey);
	}

	public void forSession(String sessionId)
	{
		this.sessionId = sessionId;
	}

	protected void succeeded(Description description)
	{
		api.jobPassed(sessionId);
		printSessionId(description);
	}

	protected void failed(Throwable e, Description description)
	{
		api.jobFailed(sessionId);
		printSessionId(description);
	}

	private void printSessionId(Description description)
	{
		if (verbose)
		{
			String message = "SauceOnDemandSessionID=" + sessionId +
					" job-name= " + description.getClassName() + " " + description.getDisplayName();
			System.out.println(message);
		}
	}
}