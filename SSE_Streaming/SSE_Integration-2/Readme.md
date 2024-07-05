# Streaming Application with Spring Boot, FastAPI, and React

This project demonstrates how to create a streaming application using Spring Boot, FastAPI, and React. The application streams a message letter by letter from the backend (Spring Boot) to the frontend (React).


## Introduction

This project integrates Spring Boot and FastAPI to create a streaming application. The backend service receives and processes messages, which are then streamed to the frontend application in real-time.

## Features

Stream messages letter by letter from the backend to the frontend.

Display the streamed messages in a React application.

Simple and intuitive user interface.


## Architecture

The project consists of three main components:

**Spring Boot**: Backend service to handle message streaming.

**FastAPI**: API service to send messages to the Spring Boot application.

**React**: Frontend application to display the streamed messages.


## Usage

To send messages to the backend (Spring Boot) via FastAPI, use the following curl command in a seperate terminal after running Fastapi and Springboot:


curl -X POST http://localhost:8000/send-messages  
     -H "Content-Type: application/json" 
     -d "{\"messages\": [\"Message 1\", \"Message 2\", \"Message 3\"]}"


Replace "Message 1", "Message 2", "Message 3" with your desired messages. This command sends a POST request to the FastAPI endpoint at http://localhost:8000/send-messages with a JSON payload containing the messages array.

After sending this, run react app to start the application.


## Prerequisites

Java 11 or higher

Python 3.8 or higher

Node.js and npm

Maven


## Technologies Used

**Spring Boot**: Backend service for message streaming.

**FastAPI**: API service for sending messages.

**React**: Frontend application for displaying messages.

**Axios**: HTTP client for making API requests from React.

**JavaScript (React)**: Frontend development.

**Java**: Backend development with Spring Boot.

**Python**: API development with FastAPI


## License

This project is licensed under the MIT License.
