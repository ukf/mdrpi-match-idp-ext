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

import uk.org.ukfederation.mdrpi.opensaml.Publication;


public class PublicationTest extends BaseSAMLObjectProviderTestCase {

    private static String expectedPublisher = "publisher";
    private static String expectedPublicationId = "Ident";
    private static DateTime expectedCreationInstant = new DateTime(2010, 8, 11, 14, 59, 1, 2, ISOChronology.getInstanceUTC());

    /**
     * Constructor.
     */
    public PublicationTest() {
        MdrpiImpl.configure();
        singleElementFile = "/uk/org/ukfederation/mdrpi/opensaml/Publication.xml";
        singleElementOptionalAttributesFile = "/uk/org/ukfederation/mdrpi/opensaml/PublicationOptionalAttr.xml";
    }

    /** {@inheritDoc} */
    public void testSingleElementUnmarshall() {
        Publication info = (Publication) unmarshallElement(singleElementFile);
        assertEquals(info.getPublisher(), expectedPublisher);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesUnmarshall() {
        Publication info = (Publication) unmarshallElement(singleElementOptionalAttributesFile);
        assertEquals(info.getPublisher(), expectedPublisher);
        assertEquals(info.getPublicationId(), expectedPublicationId);
        assertEquals(info.getCreationInstant(), expectedCreationInstant);
    }

    /** {@inheritDoc} */
    public void testSingleElementMarshall() {
        Publication info = (Publication) buildXMLObject(Publication.DEFAULT_ELEMENT_NAME);

        info.setPublisher(expectedPublisher);

        assertEquals(expectedDOM, info);
    }

    /** {@inheritDoc} */
    public void testSingleElementOptionalAttributesMarshall() {
        Publication info = (Publication) buildXMLObject(Publication.DEFAULT_ELEMENT_NAME);

        info.setPublisher(expectedPublisher);
        info.setCreationInstant(expectedCreationInstant);
        info.setPublicationId(expectedPublicationId);

        assertEquals(expectedOptionalAttributesDOM, info);
    }
}
