/*
 * This file to You under the Apache  License, Version 2.0 (the "License"); you 
 * may not use this file except in compliance with the License.  You may obtain
 * a copy of the License at
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

import uk.org.ukfederation.mdrpi.opensaml.impl.MdrpiImpl;
import edu.internet2.middleware.shibboleth.common.config.BaseSpringNamespaceHandler;

/**
 * Spring namespace handler for the Shibboleth metadata namespace.
 */
public class FilterNamespaceHandler extends BaseSpringNamespaceHandler {

    /** Namespace for this handler. */
    public static final String NAMESPACE = "http://ukfederation.org.uk/schemas/mdrpi/filter";

    /** {@inheritDoc} */
    public void init() {
        // Borrow this thread to register our handler
        MdrpiImpl.configure();
        registerBeanDefinitionParser(MdrpiFilterParser.TYPE_NAME,
                new MdrpiFilterParser());
    }
}
