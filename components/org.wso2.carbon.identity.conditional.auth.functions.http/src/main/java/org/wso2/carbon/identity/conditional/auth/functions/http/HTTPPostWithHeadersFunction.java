/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
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

package org.wso2.carbon.identity.conditional.auth.functions.http;

import java.util.Map;

/**
 * Function to call http endpoints with headers. Function will post to the given endpoint reference with payload data.
 */
@FunctionalInterface
public interface HTTPPostWithHeadersFunction {

    /**
     *  POST data to the given endpoint.
     *
     * @param epUrl Endpoint url.
     * @param payloadData payload data.
     * @param headers HTTP headers
     * @param eventHandlers event handlers.
     */
    void httpPostWithHeaders(String epUrl, Map<String, Object> payloadData, Map<String, String> headers, Map<String, Object> eventHandlers);
}
