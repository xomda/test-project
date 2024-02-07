# XOMDA Test Projects

## Prerequisites

The [XOMDA](https://github.com/xomda/xomda)-project hasn't been published to maven central yet.
To make Gradle find XOMDA, you should perform one of the following actions.

### Deploy locally

Deployed to your local maven repository, so that gradle can find it:

  ```bash
  # in xomda (https://github.com/xomda/xomda)
  ./gradlew clean build publishToMavenLocal
  ```

### Configure the repo

The maven repo should be defined in settings.gradle:

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