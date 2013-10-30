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

import uk.org.ukfederation.mdrpi.opensaml.RegistrationInfo;

/**
 * A marshaller for {@link RegistrationInfo}.
 */
public class RegistrationInfoMarshaller extends AbstractSAMLObjectMarshaller {
    /** {@inheritDoc} */
    protected void marshallAttributes(XMLObject samlObject, Element domElement) throws MarshallingException {
        RegistrationInfo info = (RegistrationInfo) samlObject;

        if (info.getRegistrationAuthority() != null) {
            domElement.setAttributeNS(null, RegistrationInfo.REGISTRATION_AUTHORITY_ATTRIB_NAME,
                    info.getRegistrationAuthority());
        }

        if (info.getRegistrationInstant() != null) {
            String registrationInstant = Configuration.getSAMLDateFormatter().print(info.getRegistrationInstant());
            domElement.setAttributeNS(null, RegistrationInfo.REGISTRATION_INSTANT_ATTRIB_NAME, registrationInstant);
        }
    }
}
