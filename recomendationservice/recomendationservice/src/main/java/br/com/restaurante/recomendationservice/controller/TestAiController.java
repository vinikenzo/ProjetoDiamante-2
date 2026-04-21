package br.com.restaurante.recomendationservice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-ai")
public class TestAiController {
    private final ChatClient chatClient;

    public TestAiController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String testGroqConnection(){
        return chatClient.prompt()
                .user("Tell me a one-sentece joke about Java developers.")
                .call()
                .content();
    }


}
