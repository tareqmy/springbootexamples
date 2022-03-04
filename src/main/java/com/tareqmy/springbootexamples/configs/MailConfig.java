package com.tareqmy.springbootexamples.configs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StopWatch;

import java.util.Properties;

/**
 * Created by tmyousuf on 16/3/20.
 */
@Slf4j
@Configuration
public class MailConfig {

    private static final String DEFAULT_HOST = "127.0.0.1";

    private static final String PROP_SMTP_AUTH = "mail.smtp.auth";

    private static final String PROP_STARTTLS = "mail.smtp.starttls.enable";

    private static final String PROP_TRANSPORT_PROTO = "mail.transport.protocol";

    @Value(value = "${springbootexamples.mail.host}")
    private String HOST;

    @Value(value = "${springbootexamples.mail.port}")
    private int PORT;

    @Value(value = "${springbootexamples.mail.username}")
    private String USERNAME;

    @Value(value = "${springbootexamples.mail.password}")
    private String PASSWORD;

    @Value(value = "${springbootexamples.mail.protocol}")
    private String PROTOCOL;

    @Value(value = "${springbootexamples.mail.tls}")
    private String TLS;

    @Value(value = "${springbootexamples.mail.auth}")
    private String AUTH;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        StopWatch watch = new StopWatch();
        watch.start();
        log.debug("Configuring mail server");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        if (!StringUtils.isEmpty(HOST)) {
            sender.setHost(HOST);
        } else {
            log.warn("Warning! Your SMTP server is not configured. We will try to use one on localhost.");
            log.debug("Did you configure your SMTP settings in your application.properties?");
            sender.setHost(DEFAULT_HOST);
        }
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);

        Properties sendProperties = new Properties();
        sendProperties.setProperty(PROP_SMTP_AUTH, AUTH);
        sendProperties.setProperty(PROP_STARTTLS, TLS);
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, PROTOCOL);
        sender.setJavaMailProperties(sendProperties);

        watch.stop();
        log.debug("Started Mail in {} ms", watch.getTotalTimeMillis());
        return sender;
    }
}
