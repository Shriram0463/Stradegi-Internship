from fastapi import FastAPI, Response
from fastapi.responses import StreamingResponse
import asyncio

app = FastAPI()

async def generate_ai_responses():
    sentences = [
        "hi how may i help you?",
        "what can i assist with today?",
        "feel free to ask any questions.",
        "let me know how i can support you."
    ]
    
    for sentence in sentences:
        for char in sentence:
            yield f"data: {char}\n\n"
            await asyncio.sleep(0.1)  # Adjust delay between characters
        yield "data: \n\n" * 2 # To signal the end of the sentence
        await asyncio.sleep(1)  # Adjust delay between sentences

@app.get("/")
async def read_root():
    return Response(content=open("index.html", "r").read(), media_type="text/html")

@app.get("/stream-ai-responses")
async def stream_ai_responses():
    async def stream():
        async for message in generate_ai_responses():
            yield message

    response = StreamingResponse(stream(), media_type="text/event-stream")
    response.headers["Cache-Control"] = "no-cache"
    response.headers["Connection"] = "keep-alive"
    return response

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="localhost", port=8000)
