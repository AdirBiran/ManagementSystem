package Domain;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailSender {

    public static boolean send(String to, String messageToSend){
        /*
        String from = "liatp@post.bgu.ac.il";//change accordingly
        String host = "localhost";//or IP address

        //Get the session object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        //compose the message
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("new message from FootballManagementSystem");
            message.setText(messageToSend);

            // Send message
            Transport.send(message);
            return true;

        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    */
    return true;}
}
