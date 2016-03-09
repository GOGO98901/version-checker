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
package net.roryclaasen.githubcheck.data;

import net.roryclaasen.util.exception.TagNotFoundException;

import com.google.gson.JsonObject;

public class Release extends JSONData {

	public Release(JsonObject object) {
		super(object);
	}

	private String url, name, tagName;
	private boolean preRelease;

	@Override
	protected void load(JsonObject object) throws TagNotFoundException {
		if (hasKey("url")) {
			url = object.get("url").getAsString();
		} else {
			throw new TagNotFoundException("Key not found (url)");
		}
		if (hasKey("name")) {
			name = object.get("name").getAsString();
		} else {
			throw new TagNotFoundException("Key not found (name)");
		}
		if (hasKey("tag_name")) {
			tagName = object.get("tag_name").getAsString();
		} else {
			throw new TagNotFoundException("Key not found (tag_name)");
		}
		if (hasKey("prerelease")) {
			preRelease = object.get("prerelease").getAsBoolean();
		} else {
			throw new TagNotFoundException("Key not found (prerelease)");
		}
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getTagName() {
		return tagName;
	}

	public boolean isPreRelease() {
		return preRelease;
	}
}
