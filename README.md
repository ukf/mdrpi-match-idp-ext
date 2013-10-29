mdrpi-filter
============

Shibboleth IdP V2 filter plugin for MDRPI elements.

This plugin interprets metadata specified by the [SAML V2.0 Metadata Extensions for Registration and Publication Information Version 1.0](https://wiki.oasis-open.org/security/SAML2MetadataDRI) (MDRPI) extension specification. It enables the identity provider to include a requesting entity's `registrationAuthority` attribute in attribute release policies.

## Requirements

This plugin was developed for v2.4.x of the Shibboleth identity provider. It may function on earlier releases in the v2.x series, but this is not supported.

## Installation and Configuration

For installation and configuration instructions, refer to the [`INSTALL.txt`](doc/INSTALL.txt) file in the `doc` directory.
