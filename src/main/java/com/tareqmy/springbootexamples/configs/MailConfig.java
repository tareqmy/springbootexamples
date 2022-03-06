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
    private String host;

    @Value(value = "${springbootexamples.mail.port}")
    private int port;

    @Value(value = "${springbootexamples.mail.username}")
    private String username;

    @Value(value = "${springbootexamples.mail.password}")
    private String password;

    @Value(value = "${springbootexamples.mail.protocol}")
    private String protocol;

    @Value(value = "${springbootexamples.mail.tls}")
    private String tls;

    @Value(value = "${springbootexamples.mail.auth}")
    private String auth;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        StopWatch watch = new StopWatch();
        watch.start();
        log.debug("Configuring mail server");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        if (!StringUtils.isEmpty(host)) {
            sender.setHost(host);
        } else {
            log.warn("Warning! Your SMTP server is not configured. We will try to use one on localhost.");
            log.debug("Did you configure your SMTP settings in your application.properties?");
            sender.setHost(DEFAULT_HOST);
        }
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties sendProperties = new Properties();
        sendProperties.setProperty(PROP_SMTP_AUTH, auth);
        sendProperties.setProperty(PROP_STARTTLS, tls);
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, protocol);
        sender.setJavaMailProperties(sendProperties);

        watch.stop();
        log.debug("Started Mail in {} ms", watch.getTotalTimeMillis());
        return sender;
    }
}
