package org.opengis.cite.owc10;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import com.sun.jersey.api.client.Client;

/**
 * A supporting base class that sets up a common test fixture. These
 * configuration methods are invoked before those defined in a subclass.
 */
public class CommonFixture {

	/** Root test suite package (absolute path). */
	public static final String ROOT_PKG_PATH = "/org/opengis/cite/owc10/";
	/** A file containing a representation of a context document. */
	protected File contextFile;
	/** HTTP client component (JAX-RS Client API). */
	protected Client client;

	/**
	 * Initializes the common test fixture. The fixture includes the following
	 * components:
	 * <ul>
	 * <li>a File representing a context document;</li>
	 * <li>a Client for accessing HTTP endpoints.</li>
	 * </ul>
	 * .
	 *
	 * @param testContext
	 *            The test context that contains all the information for a test
	 *            run, including suite attributes.
	 */
	@BeforeClass
	public void initCommonFixture(ITestContext testContext) {
		final Object testFile = testContext.getSuite().getAttribute(
				SuiteAttribute.TEST_SUBJ_FILE.getName());
		if (testFile == null || !File.class.isInstance(testFile)) {
			throw new IllegalArgumentException(String.format(
					"Suite attribute value is not a File: %s",
					SuiteAttribute.TEST_SUBJ_FILE.getName()));
		}
		this.contextFile = File.class.cast(testFile);
		Object obj = testContext.getSuite().getAttribute(
				SuiteAttribute.CLIENT.getName());
		if (null != obj) {
			this.client = Client.class.cast(obj);
		}

	}

}
