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
echo "Running the application..."
java -jar build/libs/your-project-name-0.0.1-SNAPSHOT.jar