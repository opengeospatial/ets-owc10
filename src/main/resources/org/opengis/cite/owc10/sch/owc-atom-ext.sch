<?xml version="1.0" encoding="UTF-8"?>
<sch:schema id="owc-atom-ext" 
  xmlns:sch="http://purl.oclc.org/dsdl/schematron" 
  xml:lang="en"
  queryBinding="xslt2"
  see="http://docs.opengeospatial.org/is/12-084r2/12-084r2.html">

  <sch:title>Schematron schema for Atom representations of OWS Context documents that contain extension elements.</sch:title>

  <sch:ns prefix="owc" uri="http://www.opengis.net/owc/1.0" />
  <sch:ns prefix="atom" uri="http://www.w3.org/2005/Atom" />

  <sch:p>This schema specifies constraints on the structure and content of Atom 
  feeds that represent OWS Context documents containing extension elements.</sch:p>

  <sch:let name="atom-wms-id" value="'http://www.opengis.net/spec/owc-atom/1.0/req/wms'" />

  <sch:phase id="AtomWmsPhase">
    <sch:active pattern="AtomWmsPattern" />
  </sch:phase>

  <sch:pattern id="AtomWmsPattern">
    <sch:p xml:lang="en">Defines rules that apply to WMS offerings.</sch:p>
    <sch:rule context="owc:offering[@code = $atom-wms-id]" see="http://docs.opengeospatial.org/is/12-084r2/12-084r2.html#87">
      <sch:assert test="owc:operation[@code='GetCapabilities']">WMS offering is missing GetCapabilities operation.</sch:assert>
      <sch:assert test="owc:operation[@code='GetMap']">WMS offering is missing GetMap operation.</sch:assert>
      <sch:assert flag="WARNING" test="doc-available(owc:operation[@code='GetCapabilities']/@href)" 
        diagnostics="getcapabilities.uri">Capabilities document for WMS offering is not available.</sch:assert>
    </sch:rule>
  </sch:pattern>

  <sch:diagnostics>
    <sch:diagnostic id="getcapabilities.uri" xml:lang="en">The target URI is '<sch:value-of select="owc:operation[@code='GetCapabilities']/@href"/>'.</sch:diagnostic>
    <sch:diagnostic id="msg.root.en" xml:lang="en">The document element has [local name] = 
    <sch:value-of select="local-name(/*[1])"/> and [namespace name] = <sch:value-of select="namespace-uri(/*[1])"/>.</sch:diagnostic>    
  </sch:diagnostics>

</sch:schema>
