
run-dist:
	./gradlew installDist
	./build/install/java-project-71/bin/java-project-71 $(ARGS)
.PHONY: run-dist