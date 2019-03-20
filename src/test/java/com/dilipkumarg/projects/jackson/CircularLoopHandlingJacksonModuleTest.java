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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Dilip Kumar
 */
class CircularLoopHandlingJacksonModuleTest {
    private ObjectMapper objectMapper;

    private A a;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();

        C c = new C("c value");

        B b = new B("b value", c);

        a = new A("a value", b);

        c.setA(a);
    }

    @Test
    public void test1() {

        assertThrows(JsonMappingException.class, () -> objectMapper.writeValueAsString(a));
    }

    @Test
    public void test2() throws JsonProcessingException {

        objectMapper.registerModule(new CircularLoopHandlingJacksonModule());

        final String valueAsString = objectMapper.writeValueAsString(a);

        assertEquals("{\"value\":\"a value\",\"b\":{\"value\":\"b value\",\"c\":{\"value\":\"c value\"," +
                "\"a\":null}}}", valueAsString);

    }

    @Test
    public void test3() throws JsonProcessingException {

        objectMapper.registerModule(new CircularLoopHandlingJacksonModule(new CLHandlingConfiguration(true, null)));

        try {
            objectMapper.writeValueAsString(a);

            fail("Should throw cyclic reference issue.");

        } catch (JsonProcessingException e) {
            assertEquals(
                    "Cyclic-reference leading to cycle, Object Reference Stack:A->B->C " +
                            "(through reference chain: com.dilipkumarg.projects.jackson.A[\"b\"]->" +
                            "com.dilipkumarg.projects.jackson.B[\"c\"]->com.dilipkumarg.projects.jackson.C[\"a\"])",
                    e.getMessage());
        }

    }
}
