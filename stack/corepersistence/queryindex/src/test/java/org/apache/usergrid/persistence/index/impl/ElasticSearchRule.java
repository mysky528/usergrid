/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */

package org.apache.usergrid.persistence.index.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.usergrid.persistence.index.IndexFig;
import org.elasticsearch.client.Client;
import org.safehaus.guicyfig.Env;
import org.safehaus.guicyfig.EnvironResource;
import org.safehaus.guicyfig.GuicyFigModule;


public class ElasticSearchRule extends EnvironResource {

    private Client client;

    public ElasticSearchRule() {
        super( Env.UNIT );
    }

    @Override
    protected void before() throws Throwable {
    }

    public synchronized Client getClient() {
        if ( client == null ) {
            Injector injector = Guice.createInjector( new GuicyFigModule( IndexFig.class ) );
            IndexFig indexFig = injector.getInstance( IndexFig.class );            
            client = EsProvider.getClient( indexFig );
        }
        return client;
    }
}
