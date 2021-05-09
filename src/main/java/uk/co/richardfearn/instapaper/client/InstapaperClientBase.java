/*
 * Copyright 2021 Richard Fearn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.richardfearn.instapaper.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.util.Map;

import static com.google.api.client.http.HttpMethods.POST;

public abstract class InstapaperClientBase {

    private static final String API_BASE_URL = "https://www.instapaper.com";

    protected HttpTransport transport = new NetHttpTransport();

    protected final ObjectMapper mapper = new ObjectMapper();

    protected String consumerKey;

    protected String consumerSecret;

    public void setTransport(final HttpTransport transport) {
        this.transport = transport;
    }

    public void setConsumerKey(final String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setConsumerSecret(final String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    protected HttpResponse sendRequest(final String path) throws IOException {
        return sendRequestWithContent(path, null);
    }

    protected HttpResponse sendRequestWithData(final String path, final Map<String, Object> data) throws IOException {
        return sendRequestWithContent(path, new UrlEncodedContent(data));
    }

    protected HttpResponse sendRequestWithContent(final String path, final HttpContent content) throws IOException {

        final OAuthParameters oAuthParams = createOAuthParameters();

        final GenericUrl url = new GenericUrl(API_BASE_URL + path);

        final HttpRequestFactory requestFactory = transport.createRequestFactory();
        final HttpRequest request = requestFactory.buildRequest(POST, url, content);
        oAuthParams.intercept(request);

        return request.execute();
    }

    protected abstract OAuthParameters createOAuthParameters();

}
