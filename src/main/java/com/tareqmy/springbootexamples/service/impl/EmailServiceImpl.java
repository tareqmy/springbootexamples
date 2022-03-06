package com.tareqmy.springbootexamples.service.impl;

import com.tareqmy.springbootexamples.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Created by tareqmy at 4/3/22
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSenderImpl javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    @Value("${springbootexamples.mail.from}")
    private String from;

    private static final Locale locale = Locale.US;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl javaMailSender, MessageSource messageSource, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    @Override
    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(recipient);
            messageHelper.setSubject("No Subject");
            messageHelper.setText(message);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", recipient, e.getMessage());
        }
    }

    @Async
    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", to, e.getMessage());
        }
    }

    @Async
    @Override
    public void sendDummyWithRandomToken(String destinationEmail, String randomToken) {
        Context context = new Context(locale);
        context.setVariable("randomToken", randomToken);
        String content = templateEngine.process("mails/dummy", context);
        String subject = messageSource.getMessage("email.dummy.title", null, locale);
        sendEmail(destinationEmail, subject, content, false, true);
    }
}
