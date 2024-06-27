import asyncio
import uvicorn
from fastapi import FastAPI, WebSocket
from fastapi.responses import HTMLResponse

# Initialize FastAPI application
app = FastAPI()

# Read index.html content (assuming index.html exists in the same directory as this script)
with open("index.html") as f:
    html = f.read()

# Function to process user input and generate responses
async def process_user_input(message: str) -> str:
    # Convert message to lowercase for case-insensitive comparison
    lower_message = message.lower()

    # Define keywords and corresponding responses
    if "hello" in lower_message:
        return "Hello! How can I assist you today?"
    elif "how are you" in lower_message or "are you okay" in lower_message:
        return "I'm just a program, but thanks for asking!"
    elif "bye" in lower_message or "goodbye" in lower_message:
        return "Goodbye! Have a great day."
    else:
        return "Sorry, I didn't understand that. Can you please clarify?"

@app.get("/")
async def web_app() -> HTMLResponse:
    """
    Web App
    """
    return HTMLResponse(html)

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket) -> None:
    """
    WebSocket endpoint for handling user input and generating responses
    """
    await websocket.accept()
    while True:
        # Receive message from the client
        message = await websocket.receive_text()

        # Process the message and generate a response
        response = await process_user_input(message)

        # Send the response letter by letter, each in a separate message
        for i in range(len(response)):
            await websocket.send_text(response[:i+1])
            await asyncio.sleep(0.04)  # Adjust the sleep duration as needed

if __name__ == "__main__":
    uvicorn.run(
        "main:app",
        host="0.0.0.0",
        port=8000,
        log_level="debug",
        reload=True,
    )
