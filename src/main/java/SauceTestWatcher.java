import com.saucelabs.saucerest.SauceREST;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SauceTestWatcher extends TestWatcher
{
	private SauceREST api;
	private String sessionId;

	public boolean verbose;

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
	}

	protected void failed(Throwable e, Description description)
	{
		api.jobFailed(sessionId);
	}

	private void printSessionId(Description description)
	{
		if (verbose)
		{
			description.getClassName();
			description.getMethodName();

			String message = "SauceOnDemandSessionID=" + sessionId +
					" job-name= " + description.getClassName() + " " + description.getDisplayName();
			System.out.println(message);
		}
	}
}
