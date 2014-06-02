<?xml version="1.0" encoding="UTF-8"?>
<sch:schema xmlns:sch="http://purl.oclc.org/dsdl/schematron"
  xmlns:atom="http://www.w3.org/2005/Atom" 
  xml:lang="en"
  queryBinding="xslt2">

  <sch:title>Schematron constraints for Atom feeds (http://tools.ietf.org/html/rfc4287).</sch:title>
  <sch:ns prefix="sch" uri="http://purl.oclc.org/dsdl/schematron"/>
  <sch:ns prefix="atom" uri="http://www.w3.org/2005/Atom"/>
  <sch:ns prefix="owc" uri="http://www.opengis.net/owc/1.0"/>

  <sch:pattern id="owc">
    <sch:p>Rules extracted from RELAX NG grammars.</sch:p>
    <sch:rule context="atom:feed">
      <sch:assert test="atom:author or not(atom:entry[not(atom:author)])"
        >An atom:feed must have an atom:author unless all of its atom:entry children have an atom:author.
      </sch:assert>
      <sch:assert test="atom:category[@scheme='http://www.opengis.net/owc/specReference']"
        >An atom:feed must have an atom:category with the OWC specification reference identified with 
        @scheme='http://www.opengis.net/owc/specReference'.
      </sch:assert>
    </sch:rule>
    <sch:rule context="atom:entry">
      <sch:assert
        test="atom:link[@rel='alternate'] or atom:link[not(@rel)] or atom:content"
        >An atom:entry must have at least one atom:link element with a rel attribute of 'alternate' or an atom:content.
      </sch:assert>
      <sch:assert
        test="atom:author or ../atom:author or atom:source/atom:author"
        >An atom:entry must have an atom:author if its feed does not.
      </sch:assert>
      <sch:assert test="atom:content"
        >An atom:entry must have an atom:content element in a format understandable 
        by generic Atom readers (@type equal to 'html' is recommended).
      </sch:assert>
    </sch:rule>
  </sch:pattern>

</sch:schema>
