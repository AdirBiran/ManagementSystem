package Domain;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailSender {

    public static boolean send(String mail, String message){
/*
        String from = "liatico77@gmail.com";

        // Assuming you are sending email from localhost-check this
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));

            // Set Subject: header field
            mimeMessage.setSubject("This is the Subject Line!");

            // Now set the actual message
            mimeMessage.setText(message);

            // Send message
            Transport.send(mimeMessage);
            return true;

        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
*/
        return true;
    }
}
