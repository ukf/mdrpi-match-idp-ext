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

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.opensaml.common.BaseSAMLObjectProviderTestCase;
import org.opensaml.saml2.metadata.LocalizedString;

import uk.org.ukfederation.mdrpi.opensaml.RegistrationInfo;
import uk.org.ukfederation.mdrpi.opensaml.RegistrationPolicy;


public class RegistrationInfoTest extends BaseSAMLObjectProviderTestCase {

    private static String expectedAuthority = "https://www.aai.dfn.de";

    private static DateTime expectedRegistrationInstant = new DateTime(2010, 8, 11, 14, 59, 1, 2, ISOChronology.getInstanceUTC());

    private static String[] langs = {"en", "de",};
    private static String[] uris = {"grhttps://www.aai.dfn.de/en/join/","https://www.aai.dfn.de/teilnahme/",};

    /**
     * Constructor.
     */
    public RegistrationInfoTest() {
        MdrpiImpl.configure();
        singleElementFile = "/uk/org/ukfederation/mdrpi/opensaml/RegistrationInfo.xml";
        singleElementOptionalAttributesFile = "/uk/org/ukfederation/mdrpi/opensaml/RegistrationInfoOptionalAttr.xml";
        childElementsFile = "/uk/org/ukfederation/mdrpi/opensaml/RegistrationInfoChildren.xml";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        RegistrationInfo info = (RegistrationInfo) unmarshallElement(singleElementFile);
        assertEquals(info.getRegistrationAuthority(), expectedAuthority);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        RegistrationInfo info = (RegistrationInfo) unmarshallElement(singleElementOptionalAttributesFile);
        assertEquals(info.getRegistrationAuthority(), expectedAuthority);
        assertEquals(info.getRegistrationInstant(), expectedRegistrationInstant);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        RegistrationInfo info = (RegistrationInfo) buildXMLObject(RegistrationInfo.DEFAULT_ELEMENT_NAME);

        info.setRegistrationAuthority(expectedAuthority);

        assertEquals(expectedDOM, info);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        RegistrationInfo info = (RegistrationInfo) buildXMLObject(RegistrationInfo.DEFAULT_ELEMENT_NAME);

        info.setRegistrationAuthority(expectedAuthority);
        info.setRegistrationInstant(expectedRegistrationInstant);

        assertEquals(expectedOptionalAttributesDOM, info);
    }
    public void testChildElementsUnmarshall() {
        RegistrationInfo info = (RegistrationInfo) unmarshallElement(childElementsFile);
        assertEquals(info.getRegistrationAuthority(), expectedAuthority);
        assertEquals(info.getRegistrationInstant(), expectedRegistrationInstant);
        RegistrationPolicy policy = info.getRegistrationPolicies().get(0);
        assertEquals(policy.getXMLLang(), langs[0]);
        assertEquals(policy.getURI().getLocalString(), uris[0]);
        policy = info.getRegistrationPolicies().get(1);
        assertEquals(policy.getXMLLang(), langs[1]);
        assertEquals(policy.getURI().getLocalString(), uris[1]);
    }

    public void testChildElementsMarshall() {
        RegistrationInfo info = (RegistrationInfo) buildXMLObject(RegistrationInfo.DEFAULT_ELEMENT_NAME);
        info.setRegistrationAuthority(expectedAuthority);
        info.setRegistrationInstant(expectedRegistrationInstant);

        for (int i = 0; i < 2; i++) {
            LocalizedString string = new LocalizedString();
            string.setLocalizedString(uris[i]);

            RegistrationPolicy policy = (RegistrationPolicy) buildXMLObject(RegistrationPolicy.DEFAULT_ELEMENT_NAME);
            policy.setURI(string);
            policy.setXMLLang(langs[i]);
            info.getRegistrationPolicies().add(policy);
        }
        assertEquals(expectedChildElementsDOM, info);
    }
}
