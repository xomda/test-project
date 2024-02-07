# XOMDA Test Projects

This project contains a number of sub-projects (starting with _test-_)
which demonstrate how [XOMDA](https://github.com/xomda/xomda) works in practise.

Check the README.md files within these sub-projects.

## Prerequisites

The [XOMDA](https://github.com/xomda/xomda)-project hasn't been published to maven central yet.
To make Gradle find XOMDA, you should perform one of the following actions.

#### 1) Deploy locally

Deployed to your local maven repository, so that gradle can find it:

  ```bash
  # in xomda (https://github.com/xomda/xomda)
  ./gradlew clean build publishToMavenLocal
  ```

#### 2) Configure the repo

By defining the _Github packages_ maven repo,
Gradle will be able to download the necessary packages:

***settings.gradle***

```groovy
pluginManagement {
	repositories {
		maven {
			name 'xomda packages'
			url 'https://maven.pkg.github.com/xomda/xomda'
		}
	}
}
```

## Build the project

The XOMDA-plugin will kick in automatically during the Gradle build.

```bash
./gradlew clean build
```