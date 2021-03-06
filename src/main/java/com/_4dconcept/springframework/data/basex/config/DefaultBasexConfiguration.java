/*
 * Copyright 2017 the original author or authors.
 *
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
 */
package com._4dconcept.springframework.data.basex.config;

import org.basex.api.client.ClientSession;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class DefaultBasexConfiguration extends AbstractBasexConfiguration {

    @Override
    protected URI getBasexUri() {
        return URI.create("localhost:1984");
    }

    @Override
    public ClientSession getClientSession() throws IOException {
        return new ClientSession("localhost", 1984, "admin", "admin");
    }
}