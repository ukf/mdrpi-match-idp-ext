/*
 * Licensed to the University Corporation for Advanced Internet Development, Inc.
 * under one or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.org.ukfederation.mdrpi.opensaml;

import javax.xml.namespace.QName;

import org.opensaml.samlext.saml2mdui.LocalizedURI;

/**
 * Representation of the <code>&lt;mdrpi:UsagePolicy&gt</code> element.
 * <br/>
 * See <a href="http://docs.oasis-open.org/security/saml/Post2.0/saml-metadata-rpi/v1.0/">http://docs.oasis-open.org/security/saml/Post2.0/saml-metadata-rpi/v1.0/</a>
 */
public interface UsagePolicy extends LocalizedURI {

    /** Name of the element inside the Extensions. */
    public static final String DEFAULT_ELEMENT_LOCAL_NAME = "UsagePolicy";

    /** Default element name. */
    public static final QName DEFAULT_ELEMENT_NAME = new QName(Mdrpi.MDRPI_NS, DEFAULT_ELEMENT_LOCAL_NAME,
            Mdrpi.MDRPI_PREFIX);

}
