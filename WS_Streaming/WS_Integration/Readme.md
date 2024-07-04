# WebSocket Streaming AI Application

This project demonstrates a simple AI application that streams responses one letter at a time to the client using WebSockets. It consists of a backend built with Spring Boot and FastAPI, and a frontend built with plain HTML, JavaScript, and React.


## Introduction

This project integrates a Spring Boot WebSocket server with a FastAPI backend to process and stream AI responses letter by letter to a frontend client. The FastAPI backend processes input messages and streams the responses one letter at a time to the Spring Boot server, which in turn streams these responses to the client via WebSockets.


## Features
Real-time streaming of AI responses one letter at a time.

WebSocket communication between the frontend and Spring Boot server.

Integration of FastAPI for processing messages.

Frontend built with HTML, JavaScript, and React.


## File Overview

## Backend

### Spring Boot
**WebSocketHandler.java**: Handles WebSocket connections and streams messages from FastAPI to the client.

**application.properties**: Configuration properties for the Spring Boot application.

**pom.xml**: Maven configuration file for managing dependencies and building the Spring Boot application.

### FastAPI
**main.py**: FastAPI application that processes incoming messages and streams responses one letter at a time to the Spring Boot server.

**requirements.txt**: List of Python dependencies required for the FastAPI application.

## Frontend

**index.html**: The main HTML file that contains the structure of the web page and references to the necessary scripts.

**script.js**: JavaScript file that handles WebSocket connections and user interactions on the frontend.


## License

This project is licensed under the MIT License.
