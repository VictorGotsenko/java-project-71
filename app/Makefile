run-dist:
	./build/install/app/bin/app -h
run-dist-json:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json
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

