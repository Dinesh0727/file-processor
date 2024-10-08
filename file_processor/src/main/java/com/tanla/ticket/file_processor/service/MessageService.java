package com.tanla.ticket.file_processor.service;

import com.tanla.ticket.file_processor.model.Message;
import java.util.List;

public interface MessageService {
    public String addMessage(Message message);

    public String deleteMessage(Message message);

    public String deleteMessageById(Integer id);

    public String updateMessage(Message message);

    public List<Message> getAllMessages();
}
