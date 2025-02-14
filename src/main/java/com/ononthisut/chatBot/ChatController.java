package com.ononthisut.chatBot;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChatController {
    private final ChatService chatService;
    private final ImageService imageService;

    public ChatController(ChatService chatService, ImageService imageService) {
        this.chatService = chatService;
        this.imageService = imageService;
    }


    @GetMapping("/ai")
    public String generate(@RequestParam(value = "message", defaultValue = "hello chatgpt") String message) {
        return chatService.sendMessage(message);
    }

    @GetMapping("/ai/image")
    public void generateImage(HttpServletResponse httpResponse, @RequestParam String message) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(message);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        httpResponse.sendRedirect(imageUrl);
    }
}
