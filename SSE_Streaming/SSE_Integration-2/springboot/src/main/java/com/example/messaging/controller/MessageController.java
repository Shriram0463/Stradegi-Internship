package com.example.messaging.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class MessageController {

    private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

    @PostMapping(value = "/send-message")
    public void receiveMessage(@RequestBody Map<String, String> message) {
        messages.add(message.get("message"));
    }

    @GetMapping(value = "/stream-messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter streamMessages() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        new Thread(() -> {
            try {
                for (String message : messages) {
                    char[] characters = message.toCharArray();
                    for (char character : characters) {
                        emitter.send(character + "");  // Send each character as a string
                        Thread.sleep(100);  // Simulate delay between each character
                    }
                    emitter.send("\n");  // Send newline after each message
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}
