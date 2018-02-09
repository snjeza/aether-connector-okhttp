/**
 * Copyright (c) 2012 to original author or authors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package io.takari.aether.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface Response extends Closeable {
  int getStatusCode() throws IOException;
  String getStatusMessage() throws IOException;
  String getHeader(String name);
  Map<String, List<String>> getHeaders();
  InputStream getInputStream() throws IOException;
  void close();
}
