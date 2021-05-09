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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.StringUtils;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public final class Folder extends Item {

    public static final String TYPE = "folder";

    private final Integer folderId;

    private final String title;

    private final String slug;

    private Folder(@JsonProperty("folder_id") Integer folderId,
                   @JsonProperty("title") String title,
                   @JsonProperty("slug") String slug) {
        this.folderId = folderId;
        this.title = title;
        this.slug = slug;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("folderId", folderId)
                .append("title", StringUtils.quote(title))
                .append("slug", StringUtils.quote(slug))
                .toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Folder other = (Folder) obj;

        return new EqualsBuilder()
                .append(folderId, other.folderId)
                .append(title, other.title)
                .append(slug, other.slug)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(folderId)
                .append(title)
                .append(slug)
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer folderId;
        private String title;
        private String slug;

        private Builder() {
        }

        public Builder withFolderId(Integer folderId) {
            this.folderId = folderId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public Folder build() {
            return new Folder(folderId, title, slug);
        }

    }

}
