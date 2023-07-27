package com.example.ChatApi_Backend1.repository;

import com.example.ChatApi_Backend1.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderAndReceiverOrderByTimestamp(String sender, String receiver);
}

