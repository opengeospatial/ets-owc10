
# OWS Context 1.0 Conformance Test Suite

## Scope

This executable test suite (ETS) validates OWS context documents in accord 
with the _OGC OWS Context Atom Encoding Standard_ ([OGC 12-084r2](http://docs.opengeospatial.org/is/12-084r2/12-084r2.html)) 
and related specifications. Various conformance classes have been defined as shown 
in Figure 1.

**Figure 1: Conformance classes**

![Conformance classes](img/owc-conformance.png)

The conformance classes listed below are partially covered by this test suite:

* **Atom Core** 
    - Requirement: `http://www.opengis.net/spec/owc-atom/1.0/req/core`
    - Requirement: `http://www.opengis.net/spec/owc-atom/1.0/req/atomRules`
    - Requirement: `http://www.opengis.net/spec/owc-atom/1.0/req/owcEncoding`
* **Atom WMS** 
    - Requirement: `http://www.opengis.net/spec/owc-atom/1.0/req/wms/content`

    
## Test requirements

The documents listed below stipulate requirements that must be satisfied by a 
conforming instance.

1. [OGC 12-084r2](http://docs.opengeospatial.org/is/12-084r2/12-084r2.html): 
OGC OWS Context Atom Encoding Standard 
2. [OGC 12-080r2](https://portal.opengeospatial.org/files/?artifact_id=55182): 
OGC OWS Context Conceptual Model
3. [RFC 4287](http://tools.ietf.org/html/rfc4287): The Atom Syndication Format

If any of the following preconditions are not satisfied then all tests in the 
suite will be marked as skipped.

* The context document is an Atom feed


## Test suite structure

The test suite definition file (testng.xml) is located in the root package, 
`org.opengis.cite.owc10`. A conformance class corresponds to a &lt;test&gt; element, each 
of which includes a set of test classes that contain the actual test methods. 
The general structure of the test suite is shown in Table 1.

<table>
  <caption>Table 1 - Test suite structure</caption>
  <thead>
    <tr style="text-align: left; background-color: LightCyan">
      <th>Conformance class</th>
      <th>Test classes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Atom Core</td>
      <td>org.opengis.cite.owc10.atom.AtomCoreTests</td>
    </tr>
    <tr>
      <td>Atom WMS</td>
      <td>org.opengis.cite.owc10.atom.AtomWmsTests</td>
    </tr>
  </tbody>
</table>

The Javadoc documentation provides more detailed information about the test 
methods that constitute the suite.


## Test run arguments

The test run arguments are summarized in Table 2. The _Obligation_ descriptor can 
have the following values: M (mandatory), O (optional), or C (conditional).

<table>
	<caption>Table 2 - Test run arguments</caption>
	<thead>
    <tr>
      <th>Name</th>
      <th>Value domain</th>
	    <th>Obligation</th>
	    <th>Description</th>
    </tr>
  </thead>
	<tbody>
    <tr>
      <td>iut</td>
      <td>URI</td>
      <td>M</td>
      <td>A URI that refers to a representation of an OWS context document.
    Ampersand ('&amp;') characters must be percent-encoded as '%26'.</td>
    </tr>
	</tbody>
</table>
