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

import net.roryclaasen.githubcheck.VersionCheck;
import net.roryclaasen.githubcheck.data.Release;
import net.roryclaasen.githubcheck.data.Tag;
/**
 * @author Rory Claasen
 */
public class Bootstrap {

	public static void main(String[] args) {
		System.out.println("-----------{ Github  Version  Checker }-----------");
		System.out.println("A program by Rory Claasen");
		System.out.println();

		// You can change these to what ever you want for you're projects!
		VersionCheck version = new VersionCheck("GOGO98901", "RorysMod", "1.2.4");
		System.out.println("  Username set to: " + version.getGithub().getUsername());
		System.out.println("Repository set to: " + version.getGithub().getRepository());
		System.out.println("   Version set to: " + version.getCurrentVersion());

		tagCheck(version);
		System.out.println();
		releaseCheck(version);
		System.out.println();
		releaseList(version);
	}

	private static void tagCheck(VersionCheck version) {
		System.out.println("------------------{ Tag  check }------------------");
		try {
			Tag[] tags = version.getTagList();
			if (tags != null) {
				for (Tag tag : tags) {
					System.out.println("Tag: " + tag.getName());
				}
			} else System.err.println("Tags are null");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void releaseCheck(VersionCheck version) {
		System.out.println("----------------{ Release  check }----------------");
		try {
			Release latest = version.getLatestVersion(false);
			System.out.println("Latest when set to fales");
			System.out.println("Release: " + latest.getName());

			latest = version.getLatestVersion(true);
			System.out.println("Latest when set to true");
			System.out.println("Release: " + latest.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void releaseList(VersionCheck version) {
		System.out.println("-----------------{ Release list }-----------------");
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
