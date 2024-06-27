# AI Streaming Chatbot

This project integrates FastAPI and Spring Boot to create an AI streaming chatbot that streams responses character by character in real-time.


### Files Overview

- `Application.java`: The main class that starts the Spring Boot application.
- `TokenWebSocketHandler.java`: Handles WebSocket connections and streams responses token by token.
- `TokenWebSocketConfig.java`: Configures WebSocket endpoints.
- `main.py`: FastAPI backend that handles WebSocket connections and processes user messages.
- `index.html`: A simple web page that connects to the WebSocket server.
- `pom.xml`: Maven configuration file that includes dependencies and build configuration.


### Prerequisites

- Java 18 or higher
- Maven 3.6 or higher
- Python 3.7 or higher
- FastAPI and Uvicorn installed (see `requirements.txt` for details)


### License


This project is licensed under the MIT License - see the LICENSE file for details.

