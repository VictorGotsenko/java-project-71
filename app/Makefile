run-dist:
	@./build/install/app/bin/app -h
run-dist-json:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json
run-dist-yaml:
	./build/install/app/bin/app src/test/resources/file_yaml1.yaml src/test/resources/file_yaml2.yaml -f YAML
clean:
	./gradlew clean
build:
	./gradlew build
install:
	./gradlew clean install
test:
	./gradlew test
report:
	./gradlew jacocoTestReport
lint:
	./gradlew checkstyleMain
.PHONY: build

