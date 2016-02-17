<?xml version="1.0" encoding="UTF-8"?>
<sch:schema id="owc-atom" 
  xmlns:sch="http://purl.oclc.org/dsdl/schematron" 
  xml:lang="en"
  queryBinding="xslt2"
  see="https://portal.opengeospatial.org/files/?artifact_id=55183">

  <sch:title>Schematron schema for Atom representations of OWS Context documents.</sch:title>

  <sch:ns prefix="owc" uri="http://www.opengis.net/owc/1.0" />
  <sch:ns prefix="atom" uri="http://www.w3.org/2005/Atom" />

  <sch:p>This schema specifies constraints on the structure and content of Atom 
  feeds that represent OWS Context documents in accord OGC 12-084r2 and RFC 4287.</sch:p>

  <sch:phase id="CorePhase">
    <sch:active pattern="AtomFeedPattern" />
    <sch:active pattern="AtomCorePattern" />
  </sch:phase>

  <sch:pattern id="AtomFeedPattern">
    <sch:p xml:lang="en">Defines rules that apply to all valid Atom feeds.</sch:p>
    <sch:rule context="atom:feed" see="http://tools.ietf.org/html/rfc4287#section-4.1.1">
      <sch:assert test="atom:author or not(atom:entry[not(atom:author)])">
      An atom:feed must have an atom:author unless all of its atom:entry children have 
      an atom:author.</sch:assert>
    </sch:rule>
    <sch:rule context="atom:entry" see="http://tools.ietf.org/html/rfc4287#section-4.1.2">
      <sch:assert test="atom:link[@rel='alternate'] or atom:link[not(@rel)] or atom:content">
      An atom:entry must have at least one atom:link element with a rel attribute of 'alternate' 
      or an atom:content.</sch:assert>
      <sch:assert test="atom:author or ../atom:author or atom:source/atom:author">An 
      An atom:entry must have an atom:author if its parent feed does not.</sch:assert>
    </sch:rule>
  </sch:pattern>

  <sch:pattern id="AtomCorePattern">
    <sch:p xml:lang="en">Defines rules that apply to an Atom feed that represents an OWS context document.</sch:p>
    <sch:rule context="atom:feed" see="http://schemas.opengis.net/owc/1.0/owc.rnc">
      <sch:assert test="atom:link[@href='http://www.opengis.net/spec/owc-atom/1.0/req/core' and @rel='profile']">
      An atom:feed must have an atom:link with the @rel attribute equal to 'profile' and 
      @href='http://www.opengis.net/spec/owc-atom/1.0/req/core'.</sch:assert>
    </sch:rule>
    <sch:rule context="atom:entry" see="http://schemas.opengis.net/owc/1.0/owc.rnc">
      <sch:assert test="atom:content">
      An atom:entry must have an atom:content element in a format understandable by generic 
      Atom readers (@type equal to 'html' is recommended).</sch:assert>
    </sch:rule>
  </sch:pattern>

  <sch:diagnostics>
    <sch:diagnostic id="msg.root.en" xml:lang="en">The document element has [local name] = 
    <sch:value-of select="local-name(/*[1])"/> and [namespace name] = <sch:value-of select="namespace-uri(/*[1])"/>.</sch:diagnostic>    
  </sch:diagnostics>
  
</sch:schema>
