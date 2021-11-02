package pl.petersen.cinemamanager.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSendingServiceImpl implements EmailSendingService{

    private final EmailConfig emailConfig;

    @Autowired
    public EmailSendingServiceImpl(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }



    public void sendMessage(String email, String subject, String body) throws MessagingException {
        JavaMailSenderImpl sender = setSender();
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(email);
        helper.setFrom(emailConfig.getUsername());
        helper.setSubject(subject);
        helper.setText(body);

        sender.send(mimeMessage);
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
