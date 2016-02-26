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

	/**
	 * 
	 * @param username
	 *            GitHub Username
	 * @param repository
	 *            GitHub Repository name
	 * @param currentVersion
	 *            Current tag version
	 */
	public VersionCheck(String username, String repository, String currentVersion) {
		this.github = new GithubBack(username, repository);
		this.currentVersion = currentVersion;
	}

	/**
	 * 
	 * @param preRelease
	 *            when set to true any pre-release will also get counted
	 * @return The Latest Release
	 * @throws Exception
	 */
	public Release getLatestVersion(boolean preRelease) throws Exception {
		Release[] list = getReleasesList();
		if (preRelease) return list[0];
		for (Release release : list) {
			if (release.isPreRelease()) continue;
			return release;
		}
		return null;
	}

	public final Release[] getReleasesList() throws Exception {
		String json = new Reader(github.getUrlReleases()).get();
		if (json != null) {
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) (Object) parser.parse(json);
			Release[] releases = new Release[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				releases[i] = new Release(jsonObject);
			}
			return releases;
		} else throw new Exception("No data received");
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

	/**
	 * Not including pre-releases
	 * 
	 * @return if the current tag is equal to the latest release tag
	 */
	public boolean checkRelease() {
		return checkRelease(false);
	}

	/**
	 * 
	 * @param preRelease
	 *            if set to true then pre releaess will be included
	 * @return if the current tag is equal to the latest release tag
	 */
	public boolean checkRelease(boolean preRelease) {
		try {
			return currentVersion.equals(getLatestVersion(preRelease).getTagName());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This has no way of telling if tag is a pre-release or not
	 * 
	 * @return if the current tag is equal to the latest tag
	 */
	public boolean checkTag() {
		try {
			return currentVersion.equals(getTagList()[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @return the current version tag that was passed in
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}
}
