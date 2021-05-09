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

package uk.co.richardfearn.instapaper.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.richardfearn.instapaper.util.TestUtils.readFromFile;

public class FolderTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier
                .forClass(Folder.class)
                .verify();
    }

    @Test
    public void shouldDeserializeFromJson() throws Exception {

        // Given

        final String json = readFromFile("uk/co/richardfearn/instapaper/dto/folder.json");

        final ObjectMapper mapper = new ObjectMapper();

        final Folder expectedDto = Folder.builder()
                .withFolderId(1234567)
                .withTitle("My Folder")
                .withSlug("my-folder")
                .build();

        // When

        final Folder actualDto = mapper.readValue(json, Folder.class);

        // Then

        assertThat(actualDto).isEqualTo(expectedDto);
    }

}
