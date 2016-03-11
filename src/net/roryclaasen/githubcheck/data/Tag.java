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

import org.json.simple.JSONObject;

/**
 * @author Rory Claasen
 */
public class Tag extends JSONData {

	private String name, zipball, tarball;

	public Tag(JSONObject object) {
		super(object);
	}

	@Override
	protected void load(JSONObject object) throws TagNotFoundException {
		if (hasKey("name")) {
			name = (String) object.get("name");
		} else {
			throw new TagNotFoundException("Key not found (name)");
		}
		if (hasKey("zipball_url")) {
			zipball = (String) object.get("zipball_url");
		} else {
			throw new TagNotFoundException("Key not found (zipball_url)");
		}
		if (hasKey("tarball_url")) {
			tarball = (String) object.get("tarball_url");
		} else {
			throw new TagNotFoundException("Key not found (tarball_url)");
		}
	}

	public String getName() {
		return name;
	}

	public String getZipball() {
		return zipball;
	}

	public String getTarball() {
		return tarball;
	}
}
