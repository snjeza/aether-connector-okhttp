/**
 * Copyright (c) 2012 to original author or authors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package io.takari.aether.connector.test.suite.server;

/*
 * Copyright (c) 2010-2011 Sonatype, Inc. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0, 
 * and you may not use this file except in compliance with the Apache License Version 2.0. 
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the Apache License Version 2.0 is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.B64Code;

/**
 * @author Benjamin Hanzelmann
 *
 */
public class BasicAuth
    implements Behaviour
{

    private final String password;

    private final String user;

    private final AtomicInteger failed = new AtomicInteger( 0 );

    public BasicAuth( String user, String password )
    {
        this.user = user;
        this.password = password;
    }

    public boolean execute( HttpServletRequest request, HttpServletResponse response, Map<Object, Object> ctx )
        throws Exception
    {
        String userPass = new String( B64Code.encode( ( user + ":" + password ).getBytes( "UTF-8" ) ) );
        if ( ( "Basic " + userPass ).equals( request.getHeader( "Authorization" ) ) )
        {
            return true;
        }

        failed.incrementAndGet();

        response.sendError( 401, "not authorized" );
        return false;
    }

    public int getFailedCount()
    {
        return failed.get();
    }

}
