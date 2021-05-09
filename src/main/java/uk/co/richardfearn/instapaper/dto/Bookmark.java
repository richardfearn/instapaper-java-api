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

public final class Bookmark extends Item {

    public static final String TYPE = "bookmark";

    private final Integer bookmarkId;

    private final String title;

    private final String url;

    private final Integer time;

    private Bookmark(@JsonProperty("bookmark_id") Integer bookmarkId,
                     @JsonProperty("title") String title,
                     @JsonProperty("url") String url,
                     @JsonProperty("time") Integer time) {
        this.bookmarkId = bookmarkId;
        this.title = title;
        this.url = url;
        this.time = time;
    }

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Integer getTime() {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("bookmarkId", bookmarkId)
                .append("title", StringUtils.quote(title))
                .append("url", StringUtils.quote(url))
                .append("time", time)
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

        final Bookmark other = (Bookmark) obj;

        return new EqualsBuilder()
                .append(bookmarkId, other.bookmarkId)
                .append(title, other.title)
                .append(url, other.url)
                .append(time, other.time)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bookmarkId)
                .append(title)
                .append(url)
                .append(time)
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer bookmarkId;
        private String title;
        private String url;
        private Integer time;

        private Builder() {
        }

        public Builder withBookmarkId(Integer bookmarkId) {
            this.bookmarkId = bookmarkId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withTime(Integer time) {
            this.time = time;
            return this;
        }

        public Bookmark build() {
            return new Bookmark(bookmarkId, title, url, time);
        }

    }

}
