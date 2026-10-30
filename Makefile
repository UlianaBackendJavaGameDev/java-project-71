.PHONY: build
build:
	./gradlew build

test:
	./gradlew test

lint:
	./gradlew spotlessCheck

spotless-apply:
	./gradlew spotlessApply

clean:
	./gradlew clean

install:
	./gradlew installDist


run-dist:
	./gradlew installDist
	./build/install/java-project-71/bin/java-project-71 $(ARGS)
