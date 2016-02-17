package org.opengis.cite.owc10.atom;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.xml.sax.SAXException;

/**
 * Verifies the test methods in the AtomCoreTests class.
 */
public class VerifyAtomCoreTests {

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
	public void feedNotSchemaValid() throws URISyntaxException, SAXException,
			IOException {
		thrown.expect(AssertionError.class);
		thrown.expectMessage("1 schema validation error(s) detected");
		URL fileUrl = getClass().getResource("/atom-feed-2.xml");
		AtomCoreTests iut = new AtomCoreTests();
		iut.setContextDocument(new File(fileUrl.toURI()));
		iut.initFixture(testContext);
		iut.assessSchemaValidity();
	}

	@Test
	public void missingProfileLink() throws URISyntaxException {
		thrown.expect(AssertionError.class);
		thrown.expectMessage("An atom:feed must have an atom:link with the @rel attribute equal to 'profile'");
		URL fileUrl = getClass().getResource("/atom/missing-profile-link.xml");
		AtomCoreTests iut = new AtomCoreTests();
		iut.setContextDocument(new File(fileUrl.toURI()));
		iut.initFixture(testContext);
		iut.checkSchematronRules();
	}
}
