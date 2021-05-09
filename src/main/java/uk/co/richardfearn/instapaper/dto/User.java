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

public final class User extends Item {

    public static final String TYPE = "user";

    private final String username;

    private final Integer userId;

    private final String subscriptionIsActive;

    private User(@JsonProperty("username") String username,
                 @JsonProperty("user_id") Integer userId,
                 @JsonProperty("subscription_is_active") String subscriptionIsActive) {
        this.username = username;
        this.userId = userId;
        this.subscriptionIsActive = subscriptionIsActive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("username", StringUtils.quote(username))
                .append("userId", userId)
                .append("subscriptionIsActive", StringUtils.quote(subscriptionIsActive))
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

        final User other = (User) obj;

        return new EqualsBuilder()
                .append(username, other.username)
                .append(userId, other.userId)
                .append(subscriptionIsActive, other.subscriptionIsActive)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(username)
                .append(userId)
                .append(subscriptionIsActive)
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String username;
        private Integer userId;
        private String subscriptionIsActive;

        private Builder() {
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder withSubscriptionIsActive(String subscriptionIsActive) {
            this.subscriptionIsActive = subscriptionIsActive;
            return this;
        }

        public User build() {
            return new User(username, userId, subscriptionIsActive);
        }

    }

}
