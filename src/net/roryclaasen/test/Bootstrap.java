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
package net.roryclaasen.test;

import net.roryclaasen.githubcheck.Release;
import net.roryclaasen.githubcheck.VersionCheck;

public class Bootstrap {

	public static void main(String[] args) {
		VersionCheck version = new VersionCheck("GOGO98901", "RorysMod", "1.2.4");

		System.out.println("Tag check");
		try {
			String[] tags = version.getTagList();
			if (tags != null) {
				for (String tag : tags) {
					System.out.println("Tag: " + tag);
				}
			} else System.err.println("Tags are null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(System.getProperty("line.separator") + "Release check");
		try {
			System.out.println("Latest when set to fales");
			Release latest = version.getLatestVersion(false);
			System.out.println("Release: " + latest.getName());

			System.out.println("Latest when set to true");
			latest = version.getLatestVersion(true);
			System.out.println("Release: " + latest.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(System.getProperty("line.separator") + "Release list");
		try {
			Release[] releases = version.getReleasesList();
			if (releases != null) {
				for (Release release : releases) {
					System.out.println("Release: " + release.getName());
					System.out.println("Url: " + release.getUrl());
					System.out.println("Pre-Release: " + release.isPreRelease());
				}
			} else System.err.println("Releases are null");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
