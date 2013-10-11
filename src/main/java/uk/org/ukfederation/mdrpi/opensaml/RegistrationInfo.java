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

import java.util.List;

import javax.xml.namespace.QName;
import org.joda.time.DateTime;
import org.opensaml.common.SAMLObject;

/**
 *
 */
/**
 *
 */
public interface RegistrationInfo extends SAMLObject {

    /** Name of the element inside the Extensions. */
    public static final String DEFAULT_ELEMENT_LOCAL_NAME = "RegistrationInfo";

    /** Default element name. */
    public static final QName DEFAULT_ELEMENT_NAME = new QName(Mdrpi.MDRPI_NS, DEFAULT_ELEMENT_LOCAL_NAME,
            Mdrpi.MDRPI_PREFIX);

    /** Local name of the XSI type. */
    public static final String TYPE_LOCAL_NAME = "RegistrationInfoType";

    /** QName of the XSI type. */
    public static final QName TYPE_NAME = new QName(Mdrpi.MDRPI_NS, TYPE_LOCAL_NAME, Mdrpi.MDRPI_PREFIX);

    /** Registration Authority attribute name. */
    public String REGISTRATION_AUTHORITY__ATTRIB_NAME = "registrationAuthority";

    /** Registration Instant attribute name. */
    public String REGISTRATION_INSTANT__ATTRIB_NAME = "registrationInstant";

    /**
     * Get the registration authority.
     * 
     * @return the registration authority
     */
    public String GetRegistrationAuthority();

    /**
     * Set the registration authority.
     * 
     * @param authority the registration authority
     */
    public void SetRegistrationAuthority(String authority);

    /**
     * Get the registration instant.
     * 
     * @return the registration instant
     */
    public DateTime GetRegistrationInstant();

    /**
     * Set the registration instant.
     * 
     * @param dateTime the instant
     */
    public void SetRegistrationInstant(DateTime dateTime);

    /**
     * Get the policies.
     * 
     * @return the list of policies
     */
    public List<RegistrationPolicy> GetRegistrationPolicies();

    /**
     * Get the policies.
     * 
     * @param policies the list of policies
     */
    public void SetRegistrationPolicies(List<RegistrationPolicy> policies);
}
