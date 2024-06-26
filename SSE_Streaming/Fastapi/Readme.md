# Streaming AI Assistant with FastAPI and Server-Sent Events (SSE)

This project demonstrates how to create a real-time AI assistant using FastAPI and Server-Sent Events (SSE). The assistant streams sentences to a web client where they are displayed in real-time.

## Overview

This application utilizes FastAPI, a modern, fast (high-performance), web framework for building APIs with Python 3.7+.
The frontend of the application is a simple HTML page that listens to Server-Sent Events (SSE) endpoint /stream-ai-responses provided by FastAPI. As sentences are generated on the server, they are streamed to the client using SSE, enabling real-time updates without the need for client-side polling.

## Features

Real-time streaming of AI-generated sentences to the client browser.
Simple and responsive HTML interface to display streamed sentences.
Demonstrates asynchronous programming with asyncio in Python.


## Prerequisites

Before running this project, ensure you have the following installed:

Python 3.7+
FastAPI (pip install fastapi)
Uvicorn (for ASGI server, pip install uvicorn)


## License

This project is licensed under the MIT License.
