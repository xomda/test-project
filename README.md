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

The repository will fetch XOMDA from GitHub Packages.
In order to do so, you'll need to configure access to GitHub Packages.

> ### Create a new GitHub token
>
> In your GitHub Profile, go to "Settings > Developer settings > Personal access tokens > Tokens (classic)".
> Create a new token with "read:packages" permissions.

You'll need to add your GitHub Access token to `~/.gradle/gradle.properties`:

```.properties
gpr.user=<GITHUB_USERNAME>
gpr.token=<PERSONAL_ACCESS_TOKEN>
```

## Build the project

The XOMDA-plugin will kick in automatically during the Gradle build.

```bash
./gradlew clean build
```
