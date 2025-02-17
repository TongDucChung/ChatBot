package com.ononthisut.chatBot.controller;

import com.ononthisut.chatBot.service.ChatService;
import com.ononthisut.chatBot.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("chatbot")
public class ChatRedirectController {
    private final ChatService chatService;
    private final ImageService imageService;

    public ChatRedirectController(ChatService chatService, ImageService imageService) {
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

    @GetMapping("/chat")
    public String showChatPage(Model model) {
        return "chatView";
    }
}


