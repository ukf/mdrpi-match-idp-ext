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

import uk.org.ukfederation.mdrpi.opensaml.Publication;
import uk.org.ukfederation.mdrpi.opensaml.PublicationInfo;
import uk.org.ukfederation.mdrpi.opensaml.PublicationPath;
import uk.org.ukfederation.mdrpi.opensaml.RegistrationInfo;
import uk.org.ukfederation.mdrpi.opensaml.RegistrationPolicy;
import uk.org.ukfederation.mdrpi.opensaml.UsagePolicy;

/**
 * Class to bring together the registration of the providers.
 */
public final class MdrpiImpl {
    
    /**
     * Constructor.
     *
     */
    private MdrpiImpl() {
    }

    /**
     * This function registers the handlers - only needed because this function is a plugin.
     */
    public static void configure() {
        Configuration.registerObjectProvider(RegistrationInfo.DEFAULT_ELEMENT_NAME, new RegistrationInfoBuilder(),
                new RegistrationInfoMarshaller(), new RegistrationInfoUnmarshaller());
        Configuration.registerObjectProvider(RegistrationPolicy.DEFAULT_ELEMENT_NAME, new RegistrationPolicyBuilder(),
                new RegistrationPolicyMarshaller(), new RegistrationPolicyUnmarshaller());

        Configuration.registerObjectProvider(PublicationInfo.DEFAULT_ELEMENT_NAME, new PublicationInfoBuilder(),
                new PublicationInfoMarshaller(), new PublicationInfoUnmarshaller());
        Configuration.registerObjectProvider(UsagePolicy.DEFAULT_ELEMENT_NAME, new UsagePolicyBuilder(),
                new UsagePolicyMarshaller(), new UsagePolicyUnmarshaller());

        Configuration.registerObjectProvider(Publication.DEFAULT_ELEMENT_NAME, new PublicationBuilder(),
                new PublicationMarshaller(), new PublicationUnmarshaller());
        Configuration.registerObjectProvider(PublicationPath.DEFAULT_ELEMENT_NAME, new PublicationPathBuilder(),
                new PublicationPathMarshaller(), new PublicationPathUnmarshaller());
    }
}
