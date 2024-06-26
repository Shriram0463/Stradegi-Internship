# Streaming AI Assistant with Spring Boot and Server-Sent Events (SSE)

This project demonstrates how to create a streaming AI assistant using Spring Boot and Server-Sent Events (SSE). The assistant streams letters of a sentence to a web client in real-time.


## Overview

This application utilizes Spring Boot, a popular Java-based framework for building standalone, production-grade applications.

The frontend of the application is a simple HTML page that listens to the SSE endpoint /stream-letters provided by Spring Boot. As letters are streamed from the server, they are displayed in real-time on the client-side.


## Features

Real-time streaming of letters to the client browser using SSE.

Demonstrates asynchronous programming with Spring's SseEmitter.

Simple HTML interface to display streamed letters.


## Prerequisites

Before running this project, ensure you have the following installed:
Java Development Kit (JDK) 8+

Maven (for dependency management)

IDE of your choice (IntelliJ IDEA, Eclipse, etc.)


## License

This project is licensed under the MIT License.
