package org.opengis.cite.owc10.core;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.opengis.cite.owc10.CommonFixture;
import org.opengis.cite.owc10.ETSAssert;
import org.opengis.cite.owc10.ErrorMessage;
import org.opengis.cite.owc10.ErrorMessageKeys;
import org.opengis.cite.owc10.OWC10;
import org.opengis.cite.owc10.SuiteAttribute;
import org.opengis.cite.owc10.util.TestSuiteLogger;
import org.opengis.cite.owc10.util.URIUtils;
import org.opengis.cite.owc10.util.XMLUtils;
import org.opengis.cite.validation.RelaxNGValidator;
import org.opengis.cite.validation.ValidationErrorHandler;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Defines test methods that apply to an Atom (RFC 4287) representation of a
 * context document in accord with <em>OGC OWS Context Atom Encoding 
 * Standard 1.0</em> (OGC 12-084r2).
 *
 * <p style="margin-bottom: 0.5em">
 * <strong>Sources</strong>
 * </p>
 * <ul>
 * <li><a href="https://portal.opengeospatial.org/files/?artifact_id=55183"
 * target="_blank">OGC 12-084r2</a>: <em>OGC OWS Context Atom Encoding 
 * Standard 1.0</em></li>
 * <li><a href="https://tools.ietf.org/html/rfc4287" target="_blank">The Atom
 * Syndication Format</a> (RFC 4287)</li>
 * </ul>
 */
public class AtomCoreTests extends CommonFixture {

	private Document contextDoc;

	@BeforeClass
	public void parseContext(ITestContext testContext) {
		final Object iutRef = testContext.getSuite().getAttribute(
				SuiteAttribute.TEST_SUBJ_URI.getName());
		try {
			this.contextDoc = URIUtils.parseURI(this.contextFile.toURI());
			this.contextDoc.setDocumentURI(iutRef.toString());
		} catch (Exception x) {
			throw new RuntimeException(
					"Failed to parse resource retrieved from " + iutRef, x);
		}
		if (TestSuiteLogger.isLoggable(Level.FINE)) {
			StringBuilder logMsg = new StringBuilder(
					"Parsed resource retrieved from ");
			logMsg.append(iutRef).append("\n");
			logMsg.append(XMLUtils.writeNodeToString(this.contextDoc));
			TestSuiteLogger.log(Level.FINE, logMsg.toString());
		}
	}

	/**
	 * The atom:feed element shall be used in OWS Context to describe the
	 * context file.
	 */
	@Test(description = "OGC 12-084r2: 6.2")
	public void atomFeed() {
		Element docElem = this.contextDoc.getDocumentElement();
		ETSAssert.assertQualifiedName(docElem, OWC10.ATOM_FEED);
	}

	/**
	 * The Atom feed is schema-valid. Note that the (informative) RELAX NG
	 * grammar given in Appendix B of RFC 4287 accepts either a feed or an entry
	 * as the starting element.
	 * 
	 * @throws IOException
	 *             If an I/O error occurs while attempting to read a schema
	 *             grammar or the context file.
	 * @throws SAXException
	 *             If the context file cannot be parsed.
	 * 
	 * @see <a href="http://schemas.opengis.net/owc/1.0/owc.rnc" target=
	 *      "_blank">RELAX NG Compact Syntax Grammar for OGC Context Atom
	 *      Encoding</a>
	 */
	@Test(description = "OGC 12-084r2: http://www.opengis.net/spec/owc-atom/1.0/req/atomRules")
	public void atomRules() throws SAXException, IOException {
		URL schemaRef = getClass().getResource(
				"/org/opengis/cite/owc10/rnc/owc.rnc");
		RelaxNGValidator rngValidator = new RelaxNGValidator(schemaRef);
		Source xmlSource = (null != contextDoc) ? new DOMSource(contextDoc)
				: null;
		rngValidator.validate(xmlSource);
		ValidationErrorHandler err = rngValidator.getErrorHandler();
		Assert.assertFalse(
				err.errorsDetected(),
				ErrorMessage.format(ErrorMessageKeys.NOT_SCHEMA_VALID,
						err.getErrorCount(), err.toString()));
	}
}
