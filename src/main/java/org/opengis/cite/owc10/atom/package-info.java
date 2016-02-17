/**
 * This package contains tests pertaining to Atom representations of OWS 
 * context documents. The general constraints are described in clause 7.2 
 * of the Core Conceptual Model; they apply to the following elements:
 * 
 * <ul>
 * <li>Context</li>
 * <li>Resource</li>
 * <li>Offering</li>
 * <li>Operation</li>
 * <li>Content</li>
 * <li>StyleSet</li>
 * <li>Creator</li>
 * <li>CreatorApplication</li>
 * <li>CreatorDisplay</li>
 * </ul>
 *
 * <p>An Atom representation of a context document (see OGC 12-084r2) is validated, 
 * where the document element is an <code>atom:feed</code> element. Table 1 in 
 * OGC 12-084r2 specifies how a Context element is represented as an Atom feed.
 * </p>
 * 
 * <p style="margin-bottom: 0.5em">
 * <strong>Sources</strong>
 * </p>
 * <ul>
 * <li><a href="http://docs.opengeospatial.org/is/12-084r2/12-084r2.html" 
 * target="_blank">OGC 12-084r2</a>: <em>OGC OWS Context Atom Encoding 
 * Standard, Version 1.0</em>
 * </li>
 * <li><a href="https://portal.opengeospatial.org/files/?artifact_id=55182" 
 * target="_blank">OGC 12-080r2</a>: <em>OGC OWS Context Conceptual Model 1.0</em>
 * </li>
 * </ul>
 */
package org.opengis.cite.owc10.atom;
