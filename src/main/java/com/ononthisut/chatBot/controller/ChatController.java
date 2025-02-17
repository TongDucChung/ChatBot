package com.ononthisut.chatBot.controller;

import com.ononthisut.chatBot.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chatbot")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String userMessage) {
        return chatService.sendMessage(userMessage);
    }
}
