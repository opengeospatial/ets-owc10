package org.opengis.cite.owc10;

import javax.xml.namespace.QName;

/**
 * Provides various constants pertaining to OWS Context documents as declared in
 * <em>OGC OWS Context Conceptual Model</em> and
 * <em>OGC OWS Context Atom Encoding Standard</em>.
 */
public class OWC10 {

	/** Top-level element of an Atom feed (RFC 4287). */
	public static final QName ATOM_FEED = new QName(Namespaces.ATOM, "feed");
	/** Identifier for WMS offerings. */
	public static final String ATOM_WMS_OFFERING = "http://www.opengis.net/spec/owc-atom/1.0/req/wms";
}
