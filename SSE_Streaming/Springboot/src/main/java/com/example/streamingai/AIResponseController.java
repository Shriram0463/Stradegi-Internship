package com.example.streamingai;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/stream-ai-responses")
public class AIResponseController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamAiResponses() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                String sentence = "HelloEveryone!";
                char[] chars = sentence.toCharArray();
                for (char ch : chars) {
                    emitter.send(SseEmitter.event().data(String.valueOf(ch)));
                    TimeUnit.MILLISECONDS.sleep(100);  // Adjust delay between characters
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
