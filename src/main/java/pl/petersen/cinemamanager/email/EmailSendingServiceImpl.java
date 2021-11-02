package pl.petersen.cinemamanager.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingServiceImpl implements EmailSendingService{

    private final EmailConfig emailConfig;

    @Autowired
    public EmailSendingServiceImpl(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    private SimpleMailMessage setMessage(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getHost());
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        return message;
    }

    public void sendMessage(String email, String subject, String body) {
        JavaMailSenderImpl sender = setSender();
        SimpleMailMessage message = setMessage(email, subject, body);
        sender.send(message);
    }

    private JavaMailSenderImpl setSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(emailConfig.getHost());
        sender.setPort(emailConfig.getPort());
        sender.setUsername(emailConfig.getUsername());
        sender.setPassword(emailConfig.getPassword());
        return sender;
    }

}
