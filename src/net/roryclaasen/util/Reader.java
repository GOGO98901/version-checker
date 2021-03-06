/*
Copyright 2016 Rory Claasen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package net.roryclaasen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Rory Claasen
 */
public class Reader {

	private int attemptCount = 0;

	private String url;

	public Reader(String url) {
		this.url = url;
	}

	public String get() throws IOException, KeyManagementException, NoSuchAlgorithmException {
		attemptCount++;
		try {
			URL url = new URL(this.url);
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine, data = "";
			while ((inputLine = br.readLine()) != null) {
				data += inputLine + System.getProperty("line.separator");
			}
			return data;
		} catch (IOException e) {
			if (attemptCount == 1) {
				if (Trust.hasBeenApplied()) throw e;
				Trust.applyTrustFix();
				return get();
			}
			throw e;
		}
	}

	public int getNumberOfAttempts() {
		return attemptCount;
	}
}
