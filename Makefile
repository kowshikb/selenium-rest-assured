# Makefile for Java Selenium REST Assured TestNG Maven Project

# Variables
MAVEN = mvn
SRC_DIR = src/test/java/com/myproject
TESTNG_DIR = testng


# .PHONY marks targets as not filenames
.PHONY: help compile clean runXML runFileWithMaven all maven-install maven-install-skip-tests maven-install-no-tests run

# Default: show help
help:
	@echo ""
	@echo "Available 'make' commands:"
	@echo "  make apptest                 # Run the UI AppTest only"
	@echo "  make all                     # Run all tests using Maven default"
	@echo "  make compile                 # Compile the project"
	@echo "  make clean                   # Clean the project"
	@echo "  make maven-install           # Compile, test, package, and install"
	@echo "  make maven-install-skip-tests # Like install, but skips tests"
	@echo "  make maven-install-no-tests   # Like install, skip test compilation too"
	@echo "  make run FILE=ClassName      # Compile and run a Java test class by name"
	@echo ""

# Compile project (no tests)
compile:
	$(MAVEN) compile

# Clean the project
clean:
	$(MAVEN) clean

# Run App (UI) tests
runFWM:
	$(MAVEN) test -Dtest=${FILE}

# Run all tests (default Maven)
all:
	$(MAVEN) test

# Full Maven install with tests
maven-install:
	$(MAVEN) install

# Maven install, skipping only test execution
maven-install-skip-tests:
	$(MAVEN) install -DskipTests

refresh:
	$(MAVEN) clean install

# Maven install, skip both test compile and execution
maven-install-no-tests:
	$(MAVEN) install -Dmaven.test.skip=true

# Compile and run one specific Java class
# Usage: make run FILE=ClassName
run:
	javac -d target/classes $(SRC_DIR)/$(FILE).java
	java -cp target/classes:$(CLASSPATH) com.myproject.$(FILE)

runXML:
	$(MAVEN) test -DsuiteXmlFile=$(TESTNG_DIR)/$(FILE)