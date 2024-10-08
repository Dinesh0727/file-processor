package com.tanla.ticket.file_processor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String template_id;

    private String message_text;

    private String esmeaddr;

    public Message() {
    }

    public Message(String template_id, String message_text, String esmeaddr) {
        this.template_id = template_id;
        this.message_text = message_text;
        this.esmeaddr = esmeaddr;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getEsmeaddr() {
        return esmeaddr;
    }

    public void setEsmeaddr(String esmeaddr) {
        this.esmeaddr = esmeaddr;
    }

    @Override
    public String toString() {
        return "Message [template_id=" + template_id + ", message_text=" + message_text + ", esmeaddr=" + esmeaddr
                + "]";
    }

}
