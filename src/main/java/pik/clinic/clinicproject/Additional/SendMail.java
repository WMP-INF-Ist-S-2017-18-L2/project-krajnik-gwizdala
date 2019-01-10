package pik.clinic.clinicproject.Additional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Mateusz Korwel
 */
public class SendMail {

    private static final String HOST = "poczta.interia.pl";
    private static final int PORT = 465;
    // Adres email osby która wysyła maila
    private static final String FROM = "patro10@poczta.interia.pl";
    // Hasło do konta osoby która wysyła maila
    private static final String PASSWORD = "patryk";
    // Adres email osoby do której wysyłany jest mail
    private static final String TO = "stefekx9@interia.pl";
    // Temat wiadomości
    private static final String SUBJECT = "Hello World";
    // Treść wiadomości
    private static final String CONTENT = "To mój pierwszy mail wysłany za pomocą JavaMailAPI.";

    public void send() throws MessagingException {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");

        // Inicjalizacja sesji
        Session mailSession = Session.getDefaultInstance(props);

        // ustawienie debagowania, jeśli nie chcesz oglądać logów to usuń
        // linijkę poniżej lub zmień wartość na false
        mailSession.setDebug(true);

        // Tworzenie wiadomości email
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(SUBJECT);
        message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

        Transport transport = mailSession.getTransport();
        transport.connect(HOST, PORT, FROM, PASSWORD);

        // wysłanie wiadomości
        transport.sendMessage(message, message
                .getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}
