package org.opengis.cite.owc10;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.logging.Level;

import org.opengis.cite.owc10.util.ClientUtils;
import org.opengis.cite.owc10.util.TestSuiteLogger;
import org.opengis.cite.owc10.util.URIUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.sun.jersey.api.client.Client;

/**
 * A listener that performs various tasks before and after a test suite is run,
 * usually concerned with maintaining a shared test suite fixture. Since this
 * listener is loaded using the ServiceLoader mechanism, its methods will be
 * called before those of other suite listeners listed in the test suite
 * definition and before any annotated configuration methods.
 *
 * Attributes set on an ISuite instance are not inherited by constituent test
 * group contexts (ITestContext). However, suite attributes are still accessible
 * from lower contexts.
 *
 * @see org.testng.ISuite ISuite interface
 */
public class SuiteFixtureListener implements ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		processSuiteParameters(suite);
		registerClientComponent(suite);
	}

	@Override
	public void onFinish(ISuite suite) {
		deleteTempFiles(suite);
	}

	/**
	 * Processes test suite arguments and sets suite attributes accordingly. The
	 * entity referenced by the {@link TestRunArg#IUT iut} argument is retrieved
	 * and stored in a local file that is set as the value of the suite
	 * attribute {@link org.opengis.cite.owc10.SuiteAttribute#TEST_SUBJ_FILE}.
	 * 
	 * @param suite
	 *            An ISuite object representing a TestNG test suite.
	 */
	void processSuiteParameters(ISuite suite) {
		Map<String, String> params = suite.getXmlSuite().getParameters();
		TestSuiteLogger.log(Level.CONFIG,
				"Suite parameters\n" + params.toString());
		String iutParam = params.get(TestRunArg.IUT.toString());
		if ((null == iutParam) || iutParam.isEmpty()) {
			throw new IllegalArgumentException(
					"Required test run parameter not found: "
							+ TestRunArg.IUT.toString());
		}
		URI iutRef = URI.create(iutParam.trim());
		suite.setAttribute(SuiteAttribute.TEST_SUBJ_URI.getName(), iutRef);
		File entityFile = null;
		try {
			entityFile = URIUtils.dereferenceURI(iutRef);
		} catch (IOException iox) {
			throw new RuntimeException(
					"Failed to dereference resource located at " + iutRef, iox);
		}
		suite.setAttribute(SuiteAttribute.TEST_SUBJ_FILE.getName(), entityFile);
	}

	/**
	 * A client component is added to the suite fixture as the value of the
	 * {@link SuiteAttribute#CLIENT} attribute; it may be subsequently accessed
	 * via the {@link org.testng.ITestContext#getSuite()} method.
	 *
	 * @param suite
	 *            The test suite instance.
	 */
	void registerClientComponent(ISuite suite) {
		Client client = ClientUtils.buildClient();
		if (null != client) {
			suite.setAttribute(SuiteAttribute.CLIENT.getName(), client);
		}
	}

	/**
	 * Deletes temporary files created during the test run if TestSuiteLogger is
	 * enabled at the INFO level or higher (they are left intact at the CONFIG
	 * level or lower).
	 *
	 * @param suite
	 *            The test suite.
	 */
	void deleteTempFiles(ISuite suite) {
		if (TestSuiteLogger.isLoggable(Level.CONFIG)) {
			return;
		}
		File testSubjFile = (File) suite
				.getAttribute(SuiteAttribute.TEST_SUBJ_FILE.getName());
		if (testSubjFile.exists()) {
			testSubjFile.delete();
		}
	}
}
