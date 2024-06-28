package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class MessageController {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @PostMapping("/receive_message")
    public void receiveMessage(@RequestBody Message message) throws IOException {
        for (char letter : message.getMessage().toCharArray()) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(SseEmitter.event().data(letter));
                } catch (IOException e) {
                    emitters.remove(emitter);
                }
            }
            try {
                Thread.sleep(100); // Simulate delay for letter-by-letter streaming
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    static class Message {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
