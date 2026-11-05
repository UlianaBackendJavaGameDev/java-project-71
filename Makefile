.PHONY: build
build:
	.\gradlew.bat build

test:
	.\gradlew.bat test

lint:
	.\gradlew.bat spotlessCheck

spotless-apply:
	.\gradlew.bat spotlessApply

clean:
	.\gradlew.bat clean

install:
	.\gradlew.bat installDist

run-dist:
	.\gradlew.bat installDist
	.\build\install\java-project-71\bin\java-project-71 $(ARGS)