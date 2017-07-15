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
package me.roryclaasen.githubcheck;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.roryclaasen.githubcheck.data.Release;
import me.roryclaasen.githubcheck.data.Tag;
import me.roryclaasen.util.Reader;

/**
 * @author Rory Claasen
 */
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
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = (JsonArray) parser.parse(json);
			Release[] releases = new Release[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject jsonObject = (JsonObject) jsonArray.get(i);
				releases[i] = new Release(jsonObject);
			}
			return releases;
		} else throw new Exception("No data received");
	}

	public final Tag[] getTagList() throws Exception {
		String json = new Reader(github.getUrlTags()).get();
		if (json != null) {
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = (JsonArray) (Object) parser.parse(json);
			Tag[] tags = new Tag[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject jsonObject = (JsonObject) jsonArray.get(i);
				tags[i] = new Tag(jsonObject);
			}
			return tags;
		} else throw new Exception("No data received");
	}

	/**
	 * Not including pre-releases
	 * 
	 * @return if the current tag is equal to the latest release tag
	 */
	public boolean isLatestRelease() {
		return isLatestRelease(false);
	}

	/**
	 * 
	 * @param preRelease
	 *            if set to true then pre-releases will be included
	 * @return if the current tag is equal to the latest release tag
	 */
	public boolean isLatestRelease(boolean preRelease) {
		try {
			return currentVersion.equals(getLatestVersion(preRelease).getTagName());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This function can only be used if both the <b>currentVersion</b> and the version on GitHub.com are using the version format <i>x.x.x</i> <br>
	 * example: <br>
	 * current version = 1.2.4 <br>
	 * latest version = 1.3.1 <br>
	 * This function would return true
	 * 
	 * @param full
	 *            If set to true then it will check each number individually
	 * @param preRelease
	 *            if the current tag is equal to the latest release tag
	 * @return if the current tag is equal to the latest release tag
	 */
	public boolean isLatestRelease(boolean full, boolean preRelease) {
		if (!full) return isLatestRelease(preRelease);
		try {
			String[] remote = getLatestVersion(preRelease).getTagName().split(".");
			String[] current = currentVersion.split(".");
			if (isHigher(current[0], remote[0])) return true;
			else {
				if (isHigher(current[1], remote[1])) return true;
				else return isHigher(current[2], remote[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean isHigher(String current, String remote) throws NumberFormatException {
		if (current == null || remote == null) return false;
		int currentInt = Integer.parseInt(current);
		int remoteInt = Integer.parseInt(remote);
		return currentInt >= remoteInt;
	}

	/**
	 * This has no way of telling if tag is a pre-release or not
	 * 
	 * @return if the current tag is equal to the latest tag
	 */
	public boolean isLatestTag() {
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

	/**
	 * 
	 * @return the data used to pass in at initialisation
	 */
	public GithubBack getGithub() {
		return github;
	}
}
