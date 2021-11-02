package pl.petersen.cinemamanager.email;

public interface EmailSendingService {
    void sendMessage(String email, String subject, String body);
}
