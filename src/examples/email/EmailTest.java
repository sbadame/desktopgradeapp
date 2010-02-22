package email;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EmailTest {

    public static void main(String[] args) {
        String from = "desktopgradeapp@gmail.com";
        String to = JOptionPane.showInputDialog(null, "To:");
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File file= fc.getSelectedFile();
        if (file== null) {
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.port", "25");
        Session session = Session.getInstance(props, null);
        try {

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(RecipientType.TO, new Address[]{new InternetAddress(to)});
            msg.setSubject("Here comes the mail!");

            MimeBodyPart text = new MimeBodyPart();
            text.setText("Attached is the file.");

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(file);

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(text);
            mp.addBodyPart(attachment);

            msg.setContent(mp);
            
            msg.setSentDate(new Date());
            Transport transport = session.getTransport("smtps");
            transport.connect("smtp.gmail.com", "desktopgradeapp", "ufaufaufa");
            transport.sendMessage(msg, msg.getAllRecipients());

        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException me){
            me.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }



    }
}
