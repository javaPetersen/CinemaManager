package pl.petersen.cinemamanager.email;

import javax.mail.MessagingException;

public interface EmailSendingService {
    void sendMessage(String email, String subject, String body) throws MessagingException;
}
