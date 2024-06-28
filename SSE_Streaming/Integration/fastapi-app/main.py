from fastapi import FastAPI
import requests
import threading
import time

app = FastAPI()

messages = [
    "Dear Team,",
    "Please be informed that the meeting has been rescheduled to 3 PM.",
    "Thank you for your cooperation.",
    "Best regards,",
    "Management"
]

def send_messages():
    for message in messages:
        response = requests.post("http://localhost:8080/receive_message", json={"message": message})
        print(f"Message sent: {message}, Response: {response.text}")
        time.sleep(5)  # Wait for 5 seconds before sending the next message

@app.on_event("startup")
def startup_event():
    threading.Thread(target=send_messages).start()

@app.get("/")
def read_root():
    return {"message": "FastAPI is running and sending messages."}
