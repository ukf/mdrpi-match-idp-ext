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
package uk.org.ukfederation.mdrpi.opensaml.impl;

import org.opensaml.common.BaseSAMLObjectProviderTestCase;
import org.opensaml.saml2.metadata.LocalizedString;

import uk.org.ukfederation.mdrpi.opensaml.UsagePolicy;

public class UsagePolicyTest extends BaseSAMLObjectProviderTestCase {

    /**
     * Constructor.
     */
    public UsagePolicyTest() {
        MdrpiImpl.configure();
        singleElementFile = "/uk/org/ukfederation/mdrpi/opensaml/UsagePolicy.xml";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        UsagePolicy policy = (UsagePolicy) unmarshallElement(singleElementFile);
        assertEquals(policy.getXMLLang(), "en");
        assertEquals(policy.getURI().getLocalString(), "https://www.aai.dfn.de/en/join/");
        assertEquals(policy.getURI().getLanguage(), "en");
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        UsagePolicy policy = (UsagePolicy) buildXMLObject(UsagePolicy.DEFAULT_ELEMENT_NAME);

        LocalizedString string = new LocalizedString();
        string.setLocalizedString("https://www.aai.dfn.de/en/join/");
        policy.setURI(string);
        policy.setXMLLang("en");

        assertEquals(expectedDOM, policy);
    }

}
