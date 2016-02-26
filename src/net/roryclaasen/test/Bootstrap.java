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

public class Bootstrap {

	public static void main(String[] args) {
		VersionCheck version = new VersionCheck("GOGO98901", "RorysMod", "1.2.4");

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
	}
}
