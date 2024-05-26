/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.conditional.auth.functions.user.store;

import org.graalvm.polyglot.HostAccess;
import org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.JsAuthenticatedUser;
import org.wso2.carbon.identity.application.authentication.framework.config.model.graph.js.JsAuthenticationContext;
import org.wso2.carbon.identity.application.authentication.framework.exception.FrameworkException;

import java.util.Map;

/**
 * Implementation of GetUserWithClaimValuesV2Function. This will return a user given a set of claim values.
 */
public class GetUserWithClaimValuesV2FunctionImpl implements GetUserWithClaimValuesV2Function {

    @Override
    @HostAccess.Export
    public JsAuthenticatedUser getUniqueUserWithClaimValues(Map<String, String> claimMap,
                                                            JsAuthenticationContext authenticationContext,
                                                            String... parameters) throws FrameworkException {

        return new UserStoreFunctions().getUniqueUserWithClaimValues(claimMap, authenticationContext, parameters);
    }
}
