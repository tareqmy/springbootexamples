package com.tareqmy.springbootexamples.service;

public interface EmailService {

    void prepareAndSend(String recipient, String message);

    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    void sendDummyWithRandomToken(String destinationEmail, String randomToken);
}
