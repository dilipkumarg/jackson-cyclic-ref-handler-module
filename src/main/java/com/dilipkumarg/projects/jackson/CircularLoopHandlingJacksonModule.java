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

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

/**
 * @author Dilip Kumar
 * @since 1.0
 */
public class CircularLoopHandlingJacksonModule extends Module {

    private final CLHandlingConfiguration configuration;

    public CircularLoopHandlingJacksonModule(final CLHandlingConfiguration configuration) {
        this.configuration = configuration;
    }

    public CircularLoopHandlingJacksonModule() {
        this(new CLHandlingConfiguration());
    }

    @Override
    public String getModuleName() {
        return "circular_loop_handling_module";
    }

    @Override
    public Version version() {
        return VersionUtil.parseVersion("2.9.8", "com.fasterxml.jackson.core", "jackson-core");
    }

    @Override
    public void setupModule(final SetupContext setupContext) {
        BeanSerializerModifier serializerModifier = new CLHandlingBeanSerializerModifier(configuration);

        setupContext.addBeanSerializerModifier(serializerModifier);
    }

    public CLHandlingConfiguration getConfiguration() {
        return configuration;
    }
}
