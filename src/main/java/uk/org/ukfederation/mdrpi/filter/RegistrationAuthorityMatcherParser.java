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

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.xml.util.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import edu.internet2.middleware.shibboleth.common.config.attribute.filtering.BaseFilterBeanDefinitionParser;

/** Spring bean definition parser that creates {@link RegistrationAuthorityMatcher} beans. */
public class RegistrationAuthorityMatcherParser extends BaseFilterBeanDefinitionParser {

    /** LDAP data connector type name. */
    public static final QName TYPE_NAME = new QName(FilterNamespaceHandler.NAMESPACE, "AttributeRequesterMDRPI");

    /** Name of the attribute carrying the Issuers list. */
    public static final String REGISTRARS_ATTR_NAME = "registrars";

    /** Name of the attribute carrying the boolean to flag behaviour if the metadata MDRPI. */
    public static final String MATCH_IF_METADATA_SILENT_ATTR_NAME = "matchIfMetadataSilent";

    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(RegistrationAuthorityMatcherParser.class);

    /** {@inheritDoc} */
    protected Class<RegistrationAuthorityMatcher> getBeanClass(Element element) {
        return RegistrationAuthorityMatcher.class;
    }

    /** {@inheritDoc} */
    protected void doParse(Element configElement, ParserContext parserContext, BeanDefinitionBuilder builder) {
    
        super.doParse(configElement, parserContext, builder);

        boolean matchIfSilent = false;
        if (configElement.hasAttributeNS(null, MATCH_IF_METADATA_SILENT_ATTR_NAME)) {
            matchIfSilent = XMLHelper.getAttributeValueAsBoolean(
                    configElement.getAttributeNodeNS(null, MATCH_IF_METADATA_SILENT_ATTR_NAME));
        }
        log.debug("MDRPI Filter: Match if Metadata silent = {}", matchIfSilent);
        builder.addPropertyValue("matchIfMetadataSilent", matchIfSilent);

        final Attr attr = configElement.getAttributeNodeNS(null, REGISTRARS_ATTR_NAME);
        if (attr != null) {
            final List<String> issuers = XMLHelper.getAttributeValueAsList(attr);
            log.debug("MDRPI Filter: Issuers = {}", issuers);
            builder.addPropertyValue("issuers", issuers);
        }
    }

}
