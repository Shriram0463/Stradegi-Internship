from fastapi import FastAPI, HTTPException
from typing import List
from pydantic import BaseModel
from httpx import AsyncClient

app = FastAPI()

class MessagesPayload(BaseModel):
    messages: List[str]

@app.post("/send-messages")
async def send_messages(payload: MessagesPayload):
    async with AsyncClient() as client:
        url = "http://localhost:8080/send-message"
        for message in payload.messages:
            await client.post(url, json={"message": message})
    return {"message": f"Messages sent successfully: {payload.messages}"}
