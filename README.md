mdrpi-filter
============

Shibboleth IdP V2 filter plugin for MDRPI elements.

This plugin interprets metadata specified by the [SAML V2.0 Metadata Extensions for Registration and Publication Information Version 1.0](https://wiki.oasis-open.org/security/SAML2MetadataDRI) (MDRPI) extension specification. It enables the identity provider to include a requesting entity's `registrationAuthority` attribute in attribute release policies.

## Requirements

This plugin was developed for v2.4.x of the Shibboleth identity provider. It may function on earlier releases in the v2.x series, but this is not supported.

## Installation and Configuration

You can download `.zip` and `.tar.gz` distributions of the plugin from the [GitHub project's releases page](https://github.com/ukf/mdrpi-match-idp-ext/releases). These are signed using one of the developers' PGP keys, available from the [`PGP_KEYS.asc`](doc/PGP_KEYS.asc) file in the `doc` directory.

For installation and configuration instructions, refer to the [`INSTALL.txt`](doc/INSTALL.txt) file in the `doc` directory.

## Copyright and License

The entire package is Copyright (C) 2013, University of Edinburgh.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

See the License for the specific language governing permissions and
limitations under the License.
