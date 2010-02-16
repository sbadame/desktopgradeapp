/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gradeapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author sandro
 */
// Most of Code Ripped From:
// http://www.java-tips.org/other-api-tips/javamail/how-to-send-an-email-with-a-file-attachment.html
public class EmailSender {
    public static void sentEmail(){
      // Change If Needed Here
      String to = JOptionPane.showInputDialog("What email address should I send this to?");
      String from = "bigantfbi333@optonline.net";
      String host = "mail.optonline.net";
      String filename = "gayjava.txt";
      String msgText1 = "See Attachment For Grade Graph.\nDo Not Reply To This Email.\n";
      String subject = "Grade Graph Results";

      //Show this progress bar
      JProgressBar progress = new JProgressBar();
      progress.setIndeterminate(true);
      progress.setString("Sending...");
      progress.setStringPainted(true);

      JPanel panel = new JPanel();
      panel.add(progress);

      
      JDialog dialog = new JDialog((JFrame)null, "Sending Email");
      dialog.setPreferredSize(new Dimension(175,100));
      dialog.setLocationRelativeTo(null);
      dialog.getContentPane().add(new JLabel("Working..."), BorderLayout.CENTER);
      dialog.setSize(175, 100);
      dialog.pack();
      dialog.setVisible(true);
      dialog.toFront();

      // create some properties and get the default Session
      Properties props = System.getProperties();
      props.put("mail.smtp.host", host);
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
      dialog.setVisible(false);
      dialog.dispose();
    }

}
