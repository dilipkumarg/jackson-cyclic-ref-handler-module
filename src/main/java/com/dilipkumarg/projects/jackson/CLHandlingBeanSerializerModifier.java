/*-
 * ========================LICENSE_START=================================
 * jackson-modules-cyclic-handler
 * %%
 * Copyright (C) 2019 Dilip Kumar
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package com.dilipkumarg.projects.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

/**
 * @author Dilip Kumar
 * @since 1.0
 */
public class CLHandlingBeanSerializerModifier extends BeanSerializerModifier {

    private final CLHandlingConfiguration configuration;

    public CLHandlingBeanSerializerModifier(final CLHandlingConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public JsonSerializer<?> modifySerializer(
            final SerializationConfig config, final BeanDescription beanDesc, final JsonSerializer<?> serializer) {
        if (serializer instanceof BeanSerializer) {
            return new CLHandlingSerializer<>((BeanSerializer) serializer,
                    configuration);
        } else {
            return serializer;
        }
    }
}
