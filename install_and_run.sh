#!/bin/bash

LOG_FILE="install_and_run.log"

# Function to log errors
log_error() {
    echo "$1" | tee -a "$LOG_FILE"
}

# Function to install Java 17
install_java() {
    echo "Installing Java 17..." | tee -a "$LOG_FILE"
    sudo apt update | tee -a "$LOG_FILE"
    sudo apt install -y openjdk-17-jdk | tee -a "$LOG_FILE"
}

# Check if Java is installed and its version
if ! command -v java &> /dev/null
then
    log_error "Java is not installed. Installing Java 17..."
    install_java
else
    JAVA_VERSION=$(java -version 2>&1 | awk -F[\"_] 'NR==1{print $2}')
    if [[ "$JAVA_VERSION" < "17" ]]
    then
        log_error "Java version is less than 17. Installing Java 17..."
        install_java
    else
        echo "Java version is $JAVA_VERSION" | tee -a "$LOG_FILE"
    fi
fi

# Check if Gradle is installed
if ! command -v gradle &> /dev/null
then
    log_error "Gradle is not installed. Installing Gradle..."
    # Install Gradle (this example uses SDKMAN for installation)
    curl -s "https://get.sdkman.io" | bash | tee -a "$LOG_FILE"
    source "$HOME/.sdkman/bin/sdkman-init.sh"
    sdk install gradle | tee -a "$LOG_FILE"
fi

# Build the project
echo "Building the project..." | tee -a "$LOG_FILE"
./gradlew bootJar 2>>"$LOG_FILE"
if [ $? -ne 0 ]; then
    log_error "Build failed. Check the log file for details."
    exit 1
fi

# Run the JAR file
JAR_FILE=$(ls build/libs/*.jar | head -n 1)
echo "Running the application..." | tee -a "$LOG_FILE"
java -jar "$JAR_FILE" 2>>"$LOG_FILE"
if [ $? -ne 0 ]; then
    log_error "Failed to run the application. Check the log file for details."
    exit 1
fi