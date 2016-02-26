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

public class GithubBack {

	private final String username, repository;

	public GithubBack(String username, String repository) {
		this.username = username;
		this.repository = repository;
	}

	public String getUsername() {
		return username;
	}

	public String getRepository() {
		return repository;
	}

	public String getUrl() {
		return "https://api.github.com/repos/" + username + "/" + repository;
	}

	public String getUrlTags() {
		return getUrl() + "/tags";
	}

	public String getUrlReleases() {
		return getUrl() + "/releases";
	}

	public String getUrlIssues() {
		return getUrl() + "/issues";
	}
}
