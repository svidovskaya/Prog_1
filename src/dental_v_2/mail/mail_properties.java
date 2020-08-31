package dental_v_2.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.logging.Logger;

public class mail_properties {

    public static void sendMail(String recepient, String n, String p, int variant, String file) throws Exception{
        System.out.println("preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "katya010198@gmail.com";
        String password = "Geibcnbrkthfkthf";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message;
        if (variant == 1){
            message = prepareMessage(session, myAccountEmail, recepient, n, p);
            Transport.send(message);
            System.out.println("send");
        } else if (variant == 2){
            message = prepareMessage2(session, myAccountEmail, recepient, file);
            Transport.send(message);
            System.out.println("send");
        }



    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String n, String p){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("DentalGid - восстановление пароля");
            message.setText("Здравствуйте, " + n + "! \n Ваш пароль: " + p );
            return message;
        } catch (Exception ex){
            Logger.getLogger(null);
        }
        return null;


    }
    private static Message prepareMessage2(Session session, String myAccountEmail, String recepient, String file){
        try {
            //
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccountEmail));
            // Set To: header field of the header.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            // Set Subject: header field
            message.setSubject("DentalGid - накладная");
            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
            messageBodyPart.setText("Во вложении находится накладная");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part

            MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource("C:\\"+file+".txt"); // ex : "C:\\test.pdf"
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(file+".txt"); // ex : "test.pdf"

            multipart.addBodyPart(messageBodyPart);  // add the text part
            multipart.addBodyPart(attachmentBodyPart); // add the attachement part

            message.setContent(multipart);

return message;
          //  Transport.send(message);
            // Part two is attachment


        }  catch (Exception ex){
            Logger.getLogger(null);
        }
        return null;




    }


}

