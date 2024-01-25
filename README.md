# XOMDA Test Projects

## Prerequisites

Because the [XOMDA](https://github.com/xomda/xomda)-project hasn't been published yet to maven, it should first be
deployed to your local maven repository, so that gradle can find it.

```bash
# in xomda (https://github.com/xomda/xomda)
./gradlew clean build publishToMavenLocal
```

## Build the project

After that, you should be able to build the project. The XOMDA-plugin will kick in and do its work.

```bash
./gradlew clean build
```