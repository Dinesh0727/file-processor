package com.tanla.ticket.file_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanla.ticket.file_processor.model.Message;

import jakarta.persistence.Table;

@Repository
@Table(name = "messages")
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
