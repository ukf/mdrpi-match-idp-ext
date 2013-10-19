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

import uk.org.ukfederation.mdrpi.opensaml.Publication;
import uk.org.ukfederation.mdrpi.opensaml.PublicationPath;

public class PublicationPathTest extends BaseSAMLObjectProviderTestCase {

    private static String[] publishers = {"pub1", "pub2",};

    /**
     * Constructor.
     */
    public PublicationPathTest() {
        MdrpiImpl.configure();
        singleElementFile = "/uk/org/ukfederation/mdrpi/opensaml/PublicationPath.xml";
        childElementsFile = "/uk/org/ukfederation/mdrpi/opensaml/PublicationPathChildren.xml";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        PublicationPath pPath = (PublicationPath) unmarshallElement(singleElementFile);
        assertEquals(pPath.getPublications().size(), 0);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        PublicationPath pPath = (PublicationPath) buildXMLObject(PublicationPath.DEFAULT_ELEMENT_NAME);

        assertEquals(expectedDOM, pPath);
    }

    public void testChildElementsUnmarshall() {
        PublicationPath pPath = (PublicationPath) unmarshallElement(childElementsFile);
        assertEquals(pPath.getPublications().size(), 2);
        Publication pub = pPath.getPublications().get(0);
        assertEquals(pub.getPublisher(), publishers[0]);
        pub = pPath.getPublications().get(1);
        assertEquals(pub.getPublisher(), publishers[1]);
    }

    public void testChildElementsMarshall() {
        PublicationPath pPath = (PublicationPath) buildXMLObject(PublicationPath.DEFAULT_ELEMENT_NAME);

        for (int i = 0; i < 2; i++) {
            Publication pub = (Publication) buildXMLObject(Publication.DEFAULT_ELEMENT_NAME);
            pub.setPublisher(publishers[i]);
            pPath.getPublications().add(pub);
        }
        assertEquals(expectedChildElementsDOM, pPath);
    }
}
