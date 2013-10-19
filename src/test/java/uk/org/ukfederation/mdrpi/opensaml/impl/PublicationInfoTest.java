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

import uk.org.ukfederation.mdrpi.opensaml.PublicationInfo;
import uk.org.ukfederation.mdrpi.opensaml.UsagePolicy;


public class PublicationInfoTest extends BaseSAMLObjectProviderTestCase {

    private static String expectedPublisher = "publisher";
    private static String expectedPublicationId = "Ident";
    private static DateTime expectedCreationInstant = new DateTime(2010, 8, 11, 14, 59, 1, 2, ISOChronology.getInstanceUTC());

    private static String[] langs = {"en", "fr",};
    private static String[] uris = {"https://www.aai.dfn.de/en/join/","https://www.example.fr/fr/",};

    /**
     * Constructor.
     */
    public PublicationInfoTest() {
        MdrpiImpl.configure();
        singleElementFile = "/uk/org/ukfederation/mdrpi/opensaml/PublicationInfo.xml";
        singleElementOptionalAttributesFile = "/uk/org/ukfederation/mdrpi/opensaml/PublicationInfoOptionalAttr.xml";
        childElementsFile = "/uk/org/ukfederation/mdrpi/opensaml/PublicationInfoChildren.xml";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        PublicationInfo info = (PublicationInfo) unmarshallElement(singleElementFile);
        assertEquals(info.getPublisher(), expectedPublisher);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        PublicationInfo info = (PublicationInfo) unmarshallElement(singleElementOptionalAttributesFile);
        assertEquals(info.getPublisher(), expectedPublisher);
        assertEquals(info.getPublicationId(), expectedPublicationId);
        assertEquals(info.getCreationInstant(), expectedCreationInstant);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        PublicationInfo info = (PublicationInfo) buildXMLObject(PublicationInfo.DEFAULT_ELEMENT_NAME);

        info.setPublisher(expectedPublisher);

        assertEquals(expectedDOM, info);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        PublicationInfo info = (PublicationInfo) buildXMLObject(PublicationInfo.DEFAULT_ELEMENT_NAME);

        info.setPublisher(expectedPublisher);
        info.setCreationInstant(expectedCreationInstant);
        info.setPublicationId(expectedPublicationId);

        assertEquals(expectedOptionalAttributesDOM, info);
    }
    public void testChildElementsUnmarshall() {
        PublicationInfo info = (PublicationInfo) unmarshallElement(childElementsFile);
        assertEquals(info.getPublisher(), expectedPublisher);
        UsagePolicy policy = info.getUsagePolicies().get(0);
        assertEquals(policy.getXMLLang(), langs[0]);
        assertEquals(policy.getURI().getLocalString(), uris[0]);
        policy = info.getUsagePolicies().get(1);
        assertEquals(policy.getXMLLang(), langs[1]);
        assertEquals(policy.getURI().getLocalString(), uris[1]);
    }

    public void testChildElementsMarshall() {
        PublicationInfo info = (PublicationInfo) buildXMLObject(PublicationInfo.DEFAULT_ELEMENT_NAME);
        info.setPublisher(expectedPublisher);

        for (int i = 0; i < 2; i++) {
            LocalizedString string = new LocalizedString();
            string.setLocalizedString(uris[i]);

            UsagePolicy policy = (UsagePolicy) buildXMLObject(UsagePolicy.DEFAULT_ELEMENT_NAME);
            policy.setURI(string);
            policy.setXMLLang(langs[i]);
            info.getUsagePolicies().add(policy);
        }
        assertEquals(expectedChildElementsDOM, info);
    }
}
