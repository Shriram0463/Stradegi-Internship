from fastapi import FastAPI, WebSocket
from fastapi.responses import HTMLResponse
import uvicorn
import asyncio
import websockets

app = FastAPI()

# Assuming index.html is in the same directory as main.py
with open("index.html") as f:
    html_content = f.read()

# Function to process user input and generate AI responses (replace with actual AI model integration)
async def process_user_input(message: str) -> str:
    if "hello" in message.lower():
        return "Hello! How can I assist you today?"
    elif "bye" in message.lower():
        return "Goodbye! Have a great day."
    else:
        return "Sorry, I didn't understand that. Can you please clarify?"

# WebSocket endpoint to handle incoming connections
@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    while True:
        try:
            message = await websocket.receive_text()
            response = await process_user_input(message)
            await websocket.send_text(response)
        except websockets.exceptions.ConnectionClosedOK:
            break

# Route to serve index.html as the frontend UI
@app.get("/")
async def web_app():
    return HTMLResponse(content=html_content, status_code=200)

# Run FastAPI application with uvicorn server
if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
