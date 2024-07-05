# WebSocket Token Streaming Project


## Overview

This project demonstrates bidirectional communication and real-time response streaming using WebSocket between a React frontend, Spring Boot backend, and FastAPI backend. The setup allows users to send messages from the frontend, which are processed by FastAPI and streamed back letter by letter through Spring Boot to React for display.


## Prerequisites

Java 18 or Higher

Maven 3.6 or Higher


## File Overview

### Frontend
**index.html**: HTML file serving as the entry point for the React application.

**App.js**: React component handling WebSocket connections, user input, and message rendering.

## Backend

**WebSocketHandler.java**: Java class in Spring Boot handling WebSocket connections, forwarding messages to FastAPI, and streaming responses.

**main.py**: FastAPI script processing incoming messages and generating responses based on predefined rules.

## CSS and Scripts

**App.css**: CSS file for styling the React components.

**script.js**: JavaScript file for WebSocket handling and UI interaction.


## Flow

1. User Interaction: Users input messages in the frontend.

2. WebSocket Communication: Messages are sent via WebSocket from React to Spring Boot.

3. Spring Boot Handling: Spring Boot receives the message and forwards it to FastAPI using HTTP.

4. Processing in FastAPI: FastAPI processes the message and generates a response.

5. Response Streaming: FastAPI sends the response back letter by letter to Spring Boot.

6. Display in React: Spring Boot streams each letter to React, which renders them in real-time for the user.


## Technologies Used

**Frontend**: React

**Backend**: Spring Boot, FastAPI

**Communication**: WebSocket


## License

This project is licensed under the MIT License.
