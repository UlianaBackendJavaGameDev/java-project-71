.PHONY: build
build:
	cd app && ./gradlew build

test:
	cd app && ./gradlew test

lint:
	cd app && ./gradlew spotlessCheck

spotless-apply:
	cd app && ./gradlew spotlessApply

clean:
	cd app && ./gradlew clean

install:
	cd app && ./gradlew installDist

run-dist:
	cd app && ./gradlew installDist
	cd app/build/install/java-project-71/bin/java-project-71 $(ARGS)

.PHONY: setup
setup:
	cd app && ./gradlew clean build
	cd app && ./gradlew publishToMavenLocal
	cd app && ./gradlew test