/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gradeapp;

import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author sandro
 */
// Most of Code Ripped From:
// http://www.java-tips.org/other-api-tips/javamail/how-to-send-an-email-with-a-file-attachment.html
public class EmailSender {
public static void sentEmail(){
JOptionPane.showMessageDialog(null, "Wait A Sec ... Hacking ... Sending Email ...");

  String to = "anthonydifiore333@gmail.com";
  String from = "anthonydifiore333@gmail.com";
  String host = "smtp.gmail.com";
  String filename = "gayjava.txt";
  String msgText1 = "Sending a file.\n";
  String subject = "Sending a file";

  // create some properties and get the default Session
  Properties props = System.getProperties();
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.port", "465");
  Session session = Session.getInstance(props, null);

  try
  {
      // create a message
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address = {new InternetAddress(to)};
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.setSubject(subject);

      // create and fill the first message part
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1);

      // create the second message part
      MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
         FileDataSource fds = new FileDataSource(filename);
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(fds.getName());

      // create the Multipart and add its parts to it
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);

      // add the Multipart to the message
      msg.setContent(mp);

      // set the Date: header
      msg.setSentDate(new Date());

      // send the message
      Transport.send(msg);

  }
  catch (MessagingException mex)
  {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
      }
  }
  JOptionPane.showMessageDialog(null, "Done Hacking ... Send Email.");
}

}
