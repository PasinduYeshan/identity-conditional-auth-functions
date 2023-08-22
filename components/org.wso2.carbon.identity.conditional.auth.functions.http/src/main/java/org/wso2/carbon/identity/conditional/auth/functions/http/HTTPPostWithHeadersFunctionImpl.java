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

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;


/**
 * Implementation of the {@link HTTPPostFunction}
 */
public class HTTPPostWithHeadersFunctionImpl extends AbstractHTTPFunction implements HTTPPostWithHeadersFunction {

    private static final Log LOG = LogFactory.getLog(HTTPPostWithHeadersFunctionImpl.class);

    public HTTPPostWithHeadersFunctionImpl() {

       super();
    }

    @Override
    public void httpPostWithHeaders(String epUrl, Map<String, Object> payloadData, Map<String, String> headers, Map<String, Object> eventHandlers) {

        HttpPost request = new HttpPost(epUrl);

        if (headers == null) {
            headers = new HashMap<>();
        }

        headers.putIfAbsent(ACCEPT, TYPE_APPLICATION_JSON);
        headers.putIfAbsent(CONTENT_TYPE, TYPE_APPLICATION_JSON);
        headers.entrySet().stream()
                .filter(entry -> entry.getKey() != null)
                .forEach(entry -> request.setHeader(entry.getKey(), entry.getValue()));

        if (MapUtils.isNotEmpty(payloadData)) {
            //For the header "Content-Type : application/x-www-form-urlencoded" request body data is set to
            // UrlEncodedFormEntity format. For the other cases request body data is set to StringEntity format.
            if (TYPE_APPLICATION_FORM_URLENCODED.equals(headers.get(CONTENT_TYPE))) {
                List<NameValuePair> entities = new ArrayList<>();
                for (Map.Entry<String, Object> dataElements : payloadData.entrySet()) {
                    if (!StringUtils.isEmpty(dataElements.getKey())) {
                        String value = (dataElements.getValue() != null) ? dataElements.getValue().toString() : null;
                        entities.add(new BasicNameValuePair(dataElements.getKey(), value));
                    }
                }
                request.setEntity(new UrlEncodedFormEntity(entities, StandardCharsets.UTF_8));
            } else {
                JSONObject jsonObject = new JSONObject();
                for (Map.Entry<String, Object> dataElements : payloadData.entrySet()) {
                    if (!StringUtils.isEmpty(dataElements.getKey())) {
                        jsonObject.put(dataElements.getKey(), dataElements.getValue());
                    }
                }
                request.setEntity(new StringEntity(jsonObject.toJSONString(), StandardCharsets.UTF_8));
            }
        }

        executeHttpMethod(request, eventHandlers);
    }
}
