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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class VersionCheck {

	private GithubBack github;
	private String currentVersion;

	public VersionCheck(String username, String repository, String currentVersion) {
		this.github = new GithubBack(username, repository);
		this.currentVersion = currentVersion;
	}

	public final String[] getTagList() throws Exception {
		String json = new Reader(github.getUrlTags()).get();
		if (json != null) {
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) (Object) parser.parse(json);
			String[] tags = new String[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				tags[i] = (String) jsonObject.get("name");
			}
			return tags;
		} else throw new Exception("No data received");
	}

	public String getCurrentVersion() {
		return currentVersion;
	}
}
