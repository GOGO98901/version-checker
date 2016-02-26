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
package net.roryclaasen.githubcheck;

import org.json.simple.JSONObject;

public class Release {

	private String url, name,tagName;
	private boolean preRelease;

	public Release(JSONObject object) throws Exception {
		if (!object.containsKey("url")) throw new Exception("Key not found (url)");
		url = (String) object.get("url");
		
		if (!object.containsKey("name")) throw new Exception("Key not found (name)");
		name = (String) object.get("name");
		
		if (!object.containsKey("tag_name")) throw new Exception("Key not found (tag_name)");
		tagName = (String) object.get("tag_name");
		
		if (!object.containsKey("prerelease")) throw new Exception("Key not found (prerelease)");
		preRelease = (Boolean) object.get("prerelease");
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}
	
	public String getTagName(){
		return tagName;
	}

	public boolean isPreRelease() {
		return preRelease;
	}
}
