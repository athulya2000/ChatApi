package com.example.ChatApi_Backend1.controller;

import com.example.ChatApi_Backend1.JwtTokenUtil;
import com.example.ChatApi_Backend1.entity.ChatMessage;
import com.example.ChatApi_Backend1.entity.User;
import com.example.ChatApi_Backend1.repository.ChatMessageRepository;
import com.example.ChatApi_Backend1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestHeader("Authorization") String token,
                                              @RequestBody ChatMessage chatMessage) {
        String senderUsername = extractUsernameFromToken(token);
        chatMessage.setSender(senderUsername);
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(chatMessage);
        return ResponseEntity.ok("Message sent successfully.");
    }


    private String extractUsernameFromToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", ""));
        return username;
    }

    @GetMapping("/chat-history")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestHeader("Authorization") String token,
                                                            @RequestParam String receiver) {
        String senderUsername = extractUsernameFromToken(token);
        List<ChatMessage> chatHistory = chatMessageRepository.findBySenderAndReceiverOrderByTimestamp(senderUsername, receiver);
        return ResponseEntity.ok(chatHistory);
    }






}
