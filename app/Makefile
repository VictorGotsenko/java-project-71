run-dist:
	@./build/install/app/bin/app -h
run-dist-json:
	./build/install/app/bin/app src/test/resources/file1NestStrct.json src/test/resources/file2NestStrct.json -f json
run-dist-yaml:
	./build/install/app/bin/app -f plain src/test/resources/file_yaml1.yaml src/test/resources/file_yaml2.yaml
run-dist-nest-yaml:
	./build/install/app/bin/app -f json src/test/resources/file1NestStrct.yaml src/test/resources/file2NestStrct.yaml
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

