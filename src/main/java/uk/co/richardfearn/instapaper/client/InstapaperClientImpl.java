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

import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpResponse;
import com.google.common.collect.ImmutableMap;
import uk.co.richardfearn.instapaper.dto.Bookmark;
import uk.co.richardfearn.instapaper.dto.Folder;
import uk.co.richardfearn.instapaper.dto.Item;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class InstapaperClientImpl extends InstapaperClientBase implements InstapaperClient {

    private static final String FOLDERS_LIST = "/api/1/folders/list";
    private static final String BOOKMARKS_LIST = "/api/1/bookmarks/list";

    private static final String FOLDER_ID = "folder_id";
    private static final String LIMIT = "limit";

    private String token;

    private String tokenSecret;

    public void setToken(final String token) {
        this.token = token;
    }

    public void setTokenSecret(final String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @Override
    public List<Folder> listFolders() throws IOException {

        final HttpResponse response = sendRequest(FOLDERS_LIST);

        final CollectionType folderListType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Folder.class);

        return mapper.readValue(response.getContent(), folderListType);
    }

    @Override
    public List<Bookmark> listBookmarks(final Folder folder) throws IOException {

        final Map<String, Object> data = ImmutableMap.<String, Object>builder()
                .put(FOLDER_ID, folder.getFolderId())
                .put(LIMIT, 500)
                .build();

        final HttpResponse response = sendRequestWithData(BOOKMARKS_LIST, data);

        final CollectionType itemListType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Item.class);

        final List<Item> items = mapper.readValue(response.getContent(), itemListType);

        return items.stream()
                .filter(Bookmark.class::isInstance)
                .map(Bookmark.class::cast)
                .collect(toList());
    }

    @Override
    protected OAuthParameters createOAuthParameters() {

        final OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = consumerSecret;
        signer.tokenSharedSecret = tokenSecret;

        final OAuthParameters parameters = new OAuthParameters();
        parameters.consumerKey = consumerKey;
        parameters.token = token;
        parameters.signer = signer;

        return parameters;
    }

}
