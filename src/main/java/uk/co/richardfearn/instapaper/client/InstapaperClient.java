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

import uk.co.richardfearn.instapaper.dto.Bookmark;
import uk.co.richardfearn.instapaper.dto.Folder;

import java.io.IOException;
import java.util.List;

public interface InstapaperClient {

    List<Folder> listFolders() throws IOException;

    List<Bookmark> listBookmarks(Folder folder) throws IOException;

}
