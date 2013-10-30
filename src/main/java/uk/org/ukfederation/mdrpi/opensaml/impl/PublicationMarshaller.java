/*
 * Copyright (C) 2013 University of Edinburgh.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

import org.opensaml.Configuration;
import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;

import uk.org.ukfederation.mdrpi.opensaml.Publication;

/**
 * A marshaller for {@link Publication}.
 */
public class PublicationMarshaller extends AbstractSAMLObjectMarshaller {
    /** {@inheritDoc} */
    protected void marshallAttributes(XMLObject samlObject, Element domElement) throws MarshallingException {
        Publication info = (Publication) samlObject;

        if (info.getPublisher() != null) {
            domElement.setAttributeNS(null, Publication.PUBLISHER_ATTRIB_NAME,
                    info.getPublisher());
        }

        if (info.getPublicationId() != null) {
            domElement.setAttributeNS(null, Publication.PUBLICATION_ID_ATTRIB_NAME,
                    info.getPublicationId());
        }


        if (info.getCreationInstant() != null) {
            String creationInstant = Configuration.getSAMLDateFormatter().print(info.getCreationInstant());
            domElement.setAttributeNS(null, Publication.CREATION_INSTANT_ATTRIB_NAME, creationInstant);
        }
    }
}
