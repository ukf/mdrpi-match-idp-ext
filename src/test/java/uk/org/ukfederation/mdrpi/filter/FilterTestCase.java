/*
 * Licensed to the University Corporation for Advanced Internet Development, 
 * Inc. (UCAID) under one or more contributor license agreements.  See the 
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache 
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

package uk.org.ukfederation.mdrpi.filter;

import org.opensaml.saml2.metadata.provider.MetadataProviderException;

import uk.org.ukfederation.mdrpi.opensaml.impl.MdrpiImpl;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.FilterProcessingException;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.match.BaseTestCaseMetadata;

/** {@link MdrpiFilter} unit test. */
public class FilterTestCase extends BaseTestCaseMetadata {

    private static final String REQUESTED_REG_INFO =  "http://www.swamid.se/";
    private static final String INCOMMON_REG_INFO = "https://incommon.org";
    private static final String INCOMMON_SP = "https://wiki.ligo.org/shibboleth-sp";
    private static final String NO_REGINFO_SP = "https://issues.shibboleth.net/shibboleth"; 
    
    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        MdrpiImpl.configure();
        metadataFile = "/uk/org/ukfederation/mdrpi/filter/metadata.xml";
        requesterEntityId = "https://sp-test.swamid.se/shibboleth";
        issuerEntityId = "https://swamid.user.uu.se/idp/shibboleth";

        super.setUp();
    }

    public void testSWAMID() throws Exception {
        requestContext.setPeerEntityMetadata(metadataProvider.getEntityDescriptor(requesterEntityId));
        final MdrpiFilter filter = new MdrpiFilter();
        String[] array = {REQUESTED_REG_INFO, "foo",}; 
        filter.setIssuers(array);
        
        assertTrue(filter.evaluatePolicyRequirement(filterContext));
        array[0] = INCOMMON_REG_INFO; 
        assertFalse(filter.evaluatePolicyRequirement(filterContext));
    }
    
    public void testLigo() throws MetadataProviderException, FilterProcessingException {
        requestContext.setPeerEntityMetadata(metadataProvider.getEntityDescriptor(INCOMMON_SP));
        final MdrpiFilter filter = new MdrpiFilter();
        String[] array = {REQUESTED_REG_INFO, "foo",}; 
        filter.setIssuers(array);
        
        assertFalse(filter.evaluatePolicyRequirement(filterContext));
        array[0] = INCOMMON_REG_INFO; 
        assertTrue(filter.evaluatePolicyRequirement(filterContext));
    }
    
    public void testNone() throws MetadataProviderException, FilterProcessingException {
        requestContext.setPeerEntityMetadata(metadataProvider.getEntityDescriptor(NO_REGINFO_SP));
        final MdrpiFilter filter = new MdrpiFilter();
        String[] array = {REQUESTED_REG_INFO, INCOMMON_REG_INFO, "foo",}; 
        filter.setIssuers(array);
        
        filter.setMatchIfMetadataSilent(true);
        assertTrue(filter.evaluatePolicyRequirement(filterContext));
        filter.setMatchIfMetadataSilent(false);
        assertFalse(filter.evaluatePolicyRequirement(filterContext));
    }
}