package org.opengis.cite.owc10.atom;

import java.net.URL;
import java.util.Collections;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmValue;

import org.opengis.cite.owc10.CommonFixture;
import org.opengis.cite.owc10.ETSAssert;
import org.opengis.cite.owc10.Namespaces;
import org.opengis.cite.owc10.OWC10;
import org.opengis.cite.owc10.util.XMLUtils;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Defines test methods that apply to an Atom (RFC 4287) representation of a
 * context document that includes one or more WMS offerings.
 *
 * <p style="margin-bottom: 0.5em">
 * <strong>Sources</strong>
 * </p>
 * <ul>
 * <li><a href="http://docs.opengeospatial.org/is/12-084r2/12-084r2.html"
 * target="_blank">OGC 12-084r2</a>: <em>OGC OWS Context Atom Encoding 
 * Standard, Version 1.0</em></li>
 * <li><a href="https://tools.ietf.org/html/rfc4287" target="_blank">The Atom
 * Syndication Format</a> (RFC 4287)</li>
 * </ul>
 */
public class AtomWmsTests extends CommonFixture {

	private static final String WMS_PHASE = "AtomWmsPhase";

	/**
	 * Run the tests to assess "Atom WMS" conformance only if the context
	 * document contains WMS service offerings. Otherwise they will be skipped.
	 */
	@BeforeTest
	public void hasWmsOfferings() {
		String xpath = String.format("//owc:offering[@code='%s']",
				OWC10.ATOM_WMS_OFFERING);
		Source source = new StreamSource(this.contextFile);
		XdmValue result;
		try {
			result = XMLUtils.evaluateXPath2(source, xpath,
					Collections.singletonMap(Namespaces.OWC, "owc"));
		} catch (SaxonApiException e) {
			throw new AssertionError(e.getMessage());
		}
		if (result.size() == 0) {
			throw new SkipException("No WMS offerings found.");
		}
	}

	/**
	 * [{@code Test}] Checks the content of WMS service offerings in the context
	 * document. In particular, each offering must satisfy all of the following
	 * constraints:
	 * <ul>
	 * <li>has an owc:operation element with {@literal @code} =
	 * 'GetCapabilities'</li>
	 * <li>has an owc:operation element with {@literal @code} = 'GetMap'</li>
	 * <li>the referenced WMS is available</li>
	 * </ul>
	 * 
	 * @see <a
	 *      href="http://docs.opengeospatial.org/is/12-084r2/12-084r2.html#87"
	 *      target= "_blank">Atom WMS Offering</a>
	 */
	@Test(description = "Req: http://www.opengis.net/spec/owc-atom/1.0/req/wms/content")
	public void checkSchematronRulesForWmsOfferings() {
		Source source = new StreamSource(this.contextFile);
		URL schemaUrl = getClass().getResource(
				ROOT_PKG_PATH + "sch/owc-atom-ext.sch");
		ETSAssert.assertSchematronValid(schemaUrl, source, WMS_PHASE);
	}
}
