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

package uk.org.ukfederation.mdrpi.filter;

import org.opensaml.saml2.common.Extensions;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.xml.XMLObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.org.ukfederation.mdrpi.opensaml.RegistrationInfo;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.FilterProcessingException;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.ShibbolethFilteringContext;
import edu.internet2.middleware.shibboleth.common.attribute.filtering.provider.match.basic.AbstractMatchFunctor;

/**
 * This filter filters on mdrpi in the SP's metadata.
 */
public class RegistrationAuthorityMatcher extends AbstractMatchFunctor {

    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(RegistrationAuthorityMatcher.class);

    /** The issuers to match against. */
    private String[] issuers = new String[0];

    /** What to say if no MDRPI is present. */
    private boolean matchIfMetadataSilent;

    /**
     * Look for the {@link RegistrationInfo} inside the peer's entity description.
     * @param filterContext
     * @return
     * @throws FilterProcessingException 
     */
    private RegistrationInfo getRegistrationInfo(ShibbolethFilteringContext filterContext)
            throws FilterProcessingException {
        if (null == filterContext.getAttributeRequestContext()) {
            throw new FilterProcessingException("No Request Context on filter context");
        }
        
        final EntityDescriptor peerEntity = filterContext.getAttributeRequestContext().getPeerEntityMetadata();
        if (null == peerEntity) {
            throw new FilterProcessingException("No peer entity in request");
        }
        
        final Extensions extensions = peerEntity.getExtensions();
        if (null == extensions) {
            throw new FilterProcessingException("No Extensions in peer metadata");
        }
        
        for (XMLObject object:extensions.getUnknownXMLObjects()) {
            if (object instanceof RegistrationInfo) {
               return (RegistrationInfo) object;
            }
        }
        return null;
    }
    
    /** {@inheritDoc} */
    protected boolean doEvaluatePolicyRequirement(ShibbolethFilteringContext filterContext)
            throws FilterProcessingException {
        final RegistrationInfo info = getRegistrationInfo(filterContext);
        
        if (info == null) {
            log.debug("The peer's metadata did not contain a RegistrationInfo descriptor");
            return matchIfMetadataSilent;
        }
        final String authority = info.getRegistrationAuthority();
        log.debug("Peer's metadata has registration authority: {}", authority);
        for (String issuer:issuers) {
            if (issuer.matches(authority)) {
                log.debug("Peer's metadata registration authority matches");
                return true;
            }
        }
        log.debug("Peer's metadata registration authority does not match");
        return false;
    }

    /** {@inheritDoc} */
    protected boolean doEvaluateValue(ShibbolethFilteringContext filterContext, String attributeId,
            Object attributeValue) throws FilterProcessingException {
        return doEvaluatePolicyRequirement(filterContext);
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
    public void setIssuers(String[] issuers) {
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
