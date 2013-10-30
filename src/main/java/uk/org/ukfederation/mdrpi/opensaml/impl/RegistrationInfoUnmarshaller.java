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

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.opensaml.common.impl.AbstractSAMLObjectUnmarshaller;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.UnmarshallingException;
import org.w3c.dom.Attr;

import uk.org.ukfederation.mdrpi.opensaml.RegistrationInfo;
import uk.org.ukfederation.mdrpi.opensaml.RegistrationPolicy;

/**
 * An unmarshaller for {@link RegistrationInfo}
 */
public class RegistrationInfoUnmarshaller extends AbstractSAMLObjectUnmarshaller {

    /** {@inheritDoc} */
    protected void processChildElement(XMLObject parentObject, XMLObject childObject) throws UnmarshallingException {
        RegistrationInfo info = (RegistrationInfo) parentObject;
        
        if (childObject instanceof RegistrationPolicy) {
            info.getRegistrationPolicies().add((RegistrationPolicy)childObject);
        } else {
            super.processChildElement(parentObject, childObject);
        }
    }
    
    /** {@inheritDoc} */
    protected void processAttribute(XMLObject samlObject, Attr attribute) throws UnmarshallingException {
        RegistrationInfo info = (RegistrationInfo) samlObject;
        
        if (RegistrationInfo.REGISTRATION_AUTHORITY_ATTRIB_NAME.equals(attribute.getName())) {
            info.setRegistrationAuthority(attribute.getValue());
        } else if (RegistrationInfo.REGISTRATION_INSTANT_ATTRIB_NAME.equals(attribute.getName())) {
            info.setRegistrationInstant(new DateTime(attribute.getValue(), ISOChronology.getInstanceUTC()));
        } else {
            super.processAttribute(samlObject, attribute);
        }
    }

}
