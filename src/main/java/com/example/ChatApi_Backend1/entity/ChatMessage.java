package com.example.ChatApi_Backend1.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"chat\"")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String messageContent;

    public ChatMessage() {
    }

    public ChatMessage(Long id, String sender, String receiver, LocalDateTime timestamp, String messageContent) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.messageContent = messageContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
