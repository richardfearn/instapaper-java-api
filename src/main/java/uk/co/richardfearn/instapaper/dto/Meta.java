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

import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class Meta extends Item {

    public static final String TYPE = "meta";

    private Meta() {
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        return (obj != null) && (getClass() == obj.getClass());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Builder() {
        }

        public Meta build() {
            return new Meta();
        }

    }

}
