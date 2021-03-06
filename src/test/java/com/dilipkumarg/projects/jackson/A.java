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

/**
 * @author Dilip Kumar
 */
public class A {
    private String value;
    private B b;

    public A(final String value, final B b) {
        this.value = value;
        this.b = b;
    }

    public String getValue() {
        return value;
    }

    public B getB() {
        return b;
    }
}
