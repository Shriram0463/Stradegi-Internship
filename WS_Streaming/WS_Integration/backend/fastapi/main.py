from fastapi import FastAPI, WebSocket
import asyncio
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

async def process_message(message: str) -> str:
    msg = message.lower()
    if "hello" in msg:
        return "Hello! How can I assist you today?"
    elif "how are you" in msg or "are you okay" in msg:
        return "I'm just a program, but thanks for asking!"
    elif "bye" in msg or "goodbye" in msg:
        return "Goodbye! Have a great day."
    else:
        return "Sorry, I didn't understand that. Can you please clarify?"

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    while True:
        data = await websocket.receive_text()
        response_message = await process_message(data)
        for i in range(len(response_message)):
            await websocket.send_text(response_message[:i + 1])
            await asyncio.sleep(0.03)  # Adjust the sleep duration as needed
