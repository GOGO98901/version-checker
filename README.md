# Version Checker
A [java](http://java.com)-[github](http://github.com) version checker that uses the [github api](https://developer.github.com/v3/)

1. [To use](#to-use)
2. [Usage](#usage)
3. [Licence](#licence)

## To use
### How to install
#### Method 1
1. Download a release from the [releases tab](https://github.com/GOGO98901/version-checker/releases) and save to a relevant location
2. Add the jar file to the build path of the project
3. Use the library (see [usage](usage) bellow)

##### To Update (method 1)
1. Just deleate the jar file and follow the [Method 1](#method-1) instructions again

#### Method 2
1. Using [git shell](https://git-scm.com/docs/git-shell) or a such like program cd to project directory
2. Run the command `git submodule add https://github.com/GOGO98901/version-checker`
3. Then run the command  `git status` to make sure it worked. You should see something like
```shell
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.

Changes to be committed:
  (use "git reset HEAD &lt;file&gt;..." to unstage)

	new file:   .gitmodules
	new file:   version-checker
```

##### To update (method 2)
1. Using [git shell](https://git-scm.com/docs/git-shell) or a such like program cd to project directory
2. Run the command `git fetch`
2. Run the command `git merge`

Check this doc [Git Tools - Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules) if you want to know more

### Usage
Before I start you can see an example [here](https://github.com/GOGO98901/version-checker/blob/master/src/net/roryclaasen/test/Bootstrap.java).
Once added to you project then you can start
```java
VersionCheck version = new VersionCheck(username, repository, currentVersion);
```
So I might do 
```java
VersionCheck version = new VersionCheck("GOGO98901", "RorysMod", "1.2.4");
boolean isLatest = version.isLatestRelease();
```
If you wanted to check pre releases as well then you can use `version.isLatestRelease(true)`

## Licence
```
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
```
