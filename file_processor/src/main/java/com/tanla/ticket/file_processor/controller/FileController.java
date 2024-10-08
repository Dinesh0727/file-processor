package com.tanla.ticket.file_processor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tanla.ticket.file_processor.rabbitmq.Producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private Producer producer;

    @PostMapping("/fileUpload")
    public void fileToList(@RequestParam("file") MultipartFile file) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading Input File");
        }

        // call producer

        producer.sendMessages(strings);
        System.out.println("Size of the strings array : " + strings.size());
    }

}
