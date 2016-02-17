package org.opengis.cite.owc10.atom;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the test methods in the AtomWmsTests class.
 */
public class VerifyAtomWmsTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private static ITestContext testContext;
	private static ISuite suite;

	@BeforeClass
	public static void setUpClass() throws Exception {
		testContext = mock(ITestContext.class);
		suite = mock(ISuite.class);
		when(testContext.getSuite()).thenReturn(suite);
	}

	@Test
	public void missingGetCapabilities() throws URISyntaxException {
		thrown.expect(AssertionError.class);
		thrown.expectMessage("WMS offering is missing GetCapabilities operation");
		URL fileUrl = getClass().getResource(
				"/atom/wms-missing-getcapabilites.xml");
		AtomWmsTests iut = new AtomWmsTests();
		iut.setContextDocument(new File(fileUrl.toURI()));
		iut.checkSchematronRulesForWmsOfferings();
	}
}
