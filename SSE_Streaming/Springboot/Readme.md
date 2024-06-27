# Streaming AI Assistant using Spring Boot and SSE

This project demonstrates how to create a streaming AI assistant using Spring Boot and Server-Sent Events (SSE). The AI assistant streams responses letter by letter to a client via SSE.


## Prerequisites

Before running this project, ensure you have the following installed:

Java 11 or higher
Maven


## Project Overview
1. ### StreamingAiApplication.java

  Main class that initializes and runs the Spring Boot application.

2. ### AIResponseController.java

  REST controller handling the SSE endpoint /stream-ai-responses to stream AI responses.

3. ### index.html

  HTML file providing a basic interface to display streamed AI responses.

4. ### application.properties

  Configuration file for Spring Boot application properties.

5. ### pom.xml

  Maven project file defining dependencies and plugins for the project.


## License

This project is licensed under the MIT License - see the LICENSE file for details.
