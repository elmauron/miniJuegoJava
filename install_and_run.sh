#!/bin/bash

# Check if Java is installed
if ! command -v java &> /dev/null
then
    echo "Java is not installed. Please install Java and try again."
    exit
fi

# Check if Gradle is installed
if ! command -v gradle &> /dev/null
then
    echo "Gradle is not installed. Installing Gradle..."
    # Install Gradle (this example uses SDKMAN for installation)
    curl -s "https://get.sdkman.io" | bash
    source "$HOME/.sdkman/bin/sdkman-init.sh"
    sdk install gradle
fi

# Build the project
echo "Building the project..."
./gradlew bootJar

# Run the JAR file
JAR_FILE=$(ls build/libs/*.jar | head -n 1)
echo "Running the application..."
java -jar "$JAR_FILE"