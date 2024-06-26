package com.example.sentencestreaming;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class StreamingController {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @GetMapping("/stream-letters")
    public SseEmitter streamLetters() {
        SseEmitter emitter = new SseEmitter();

        executor.execute(() -> {
            try {
                String sentence = "Hello Everyone.";

                for (int i = 0; i < sentence.length(); i++) {
                    char letter = sentence.charAt(i);
                    // Simulate delay between characters
                    Thread.sleep(100);

                    // Send each character individually
                    emitter.send(Character.toString(letter));
                }

                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
