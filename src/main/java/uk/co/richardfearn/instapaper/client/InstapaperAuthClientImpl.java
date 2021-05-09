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

import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UrlEncodedParser;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.Map;

public class InstapaperAuthClientImpl extends InstapaperClientBase implements InstapaperAuthClient {

    private static final String OAUTH_ACCESS_TOKEN = "/api/1/oauth/access_token";

    private static final String X_AUTH_USERNAME = "x_auth_username";

    private static final String X_AUTH_PASSWORD = "x_auth_password";

    private static final String X_AUTH_MODE = "x_auth_mode";

    private static final String CLIENT_AUTH = "client_auth";

    @Override
    public OAuthCredentialsResponse authenticate(final String username, final String password) throws IOException {

        final Map<String, Object> data = ImmutableMap.<String, Object>builder()
                .put(X_AUTH_USERNAME, username)
                .put(X_AUTH_PASSWORD, password)
                .put(X_AUTH_MODE, CLIENT_AUTH)
                .build();

        final HttpResponse response = sendRequestWithData(OAUTH_ACCESS_TOKEN, data);
        response.setContentLoggingLimit(0);

        final OAuthCredentialsResponse oAuthResponse = new OAuthCredentialsResponse();
        UrlEncodedParser.parse(response.parseAsString(), oAuthResponse);
        return oAuthResponse;
    }

    @Override
    protected OAuthParameters createOAuthParameters() {

        final OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = consumerSecret;

        final OAuthParameters parameters = new OAuthParameters();
        parameters.consumerKey = consumerKey;
        parameters.signer = signer;

        return parameters;
    }

}
