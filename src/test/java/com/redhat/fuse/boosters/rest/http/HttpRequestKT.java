/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.redhat.fuse.boosters.rest.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.openshift.client.OpenShiftClient;

@RunWith(Arquillian.class)
public class HttpRequestKT {

    @ArquillianResource
    OpenShiftClient client;

    @Test
    public void templateTest() {
        File template = new File(".openshiftio/application.yaml");
        assertTrue(template.exists());
        Map<String, String> templateParameters = Collections.singletonMap("SOURCE_REPOSITORY_URL",
                "https://github.com/jboss-fuse/fuse-rest-http-booster");
        List<HasMetadata> resources = client.templates().load(template).process(templateParameters).getItems();

        for (HasMetadata res : resources) {
            client.resource(res).createOrReplace();
        }

        assertEquals("fuse-rest-http-booster", client.buildConfigs().list().getItems().get(0).getMetadata().getName());
    }
}
