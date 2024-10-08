package com.tanla.ticket.file_processor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanla.ticket.file_processor.model.Message;
import com.tanla.ticket.file_processor.repository.MessageRepository;

import jakarta.transaction.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional
    public String addMessage(Message message) {
        messageRepository.save(message);
        return "Saved the message : " + message.toString() + "to the Database";
    }

    @Override
    @Transactional
    public String deleteMessage(Message message) {
        messageRepository.delete(message);
        return "Deleted  the message : " + message.toString() + "to the Database";
    }

    @Override
    @Transactional
    public String updateMessage(Message message) {
        messageRepository.save(message);
        return "Updated the message " + message.toString() + "in the Database";
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional
    public String deleteMessageById(Integer id) {
        messageRepository.deleteById(id);
        return "Deleted the Message with id " + id + "from the Database";
    }
}
