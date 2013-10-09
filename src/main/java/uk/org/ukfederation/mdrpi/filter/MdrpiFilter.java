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
package uk.org.ukfederation.mdrpi.filter;

import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.FilterProcessingException;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.ShibbolethFilteringContext;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.match.basic.AbstractMatchFunctor;

/**
 * This filter filters on mdrpi in the SP's metadata.
 */
public class MdrpiFilter extends AbstractMatchFunctor {
    
    /** This issuers to match against */
    private String issuers[];
    
    private boolean matchIfMetadataSilent;

    /** {@inheritDoc} */
    protected boolean doEvaluatePolicyRequirement(ShibbolethFilteringContext filterContext) throws FilterProcessingException {
        // TODO Auto-generated method stub
        return false;
    }

    /** {@inheritDoc} */
    protected boolean doEvaluateValue(ShibbolethFilteringContext filterContext, String attributeId,
            Object attributeValue) throws FilterProcessingException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return Returns the issuers.
     */
    public String[] getIssuers() {
        return issuers;
    }

    /**
     * @param issuers The issuers to set.
     */
    public void setIssuers(String issuers[]) {
        this.issuers = issuers;
    }

    /**
     * @return Returns the matchIfMetadataSilent.
     */
    public boolean isMatchIfMetadataSilent() {
        return matchIfMetadataSilent;
    }

    /**
     * @param matchIfMetadataSilent The matchIfMetadataSilent to set.
     */
    public void setMatchIfMetadataSilent(boolean matchIfMetadataSilent) {
        this.matchIfMetadataSilent = matchIfMetadataSilent;
    }

}
