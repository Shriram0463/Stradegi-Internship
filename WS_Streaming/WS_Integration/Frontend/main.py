from fastapi import FastAPI, WebSocket
from fastapi.responses import HTMLResponse
import uvicorn
import asyncio
import websockets

app = FastAPI()

# Assuming index.html is in the same directory as main.py
with open("index.html") as f:
    html_content = f.read()

# WebSocket endpoint to handle incoming connections
@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    while True:
        try:
            message = await websocket.receive_text()
            await forward_to_springboot(message, websocket)
        except Exception as e:
            print(f"Error: {e}")
            break

async def forward_to_springboot(message: str, websocket: WebSocket):
    async with websockets.connect("ws://localhost:8080/ws") as ws:
        await ws.send(message)
        response = await ws.recv()
        await stream_message(response, websocket)

async def stream_message(response: str, websocket: WebSocket):
    for char in response:  # Iterate through each character in the response
        await websocket.send_text(char)  # Send each character individually
        await asyncio.sleep(0.05)  # Adjust the sleep interval as needed for the typing effect

# Route to serve index.html as the frontend UI
@app.get("/")
async def web_app():
    return HTMLResponse(content=html_content, status_code=200)

# Run FastAPI application with uvicorn server
if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
