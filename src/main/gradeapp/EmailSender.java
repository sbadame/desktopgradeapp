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
import javax.swing.JFileChooser;

/**
 *
 * @author DiFiore
 */
// Most of Code Ripped From:
// http://www.java-tips.org/other-api-tips/javamail/how-to-send-an-email-with-a-file-attachment.html
public class EmailSender {
public static void sentEmail(){

  // Change If Needed Here
  String to = JOptionPane.showInputDialog("Input Email Address To Send File.", "desktopgradeapp@gmail.com");
  String from = "desktopgradeapp@gmail.com";
  String host = "smtp.gmail.com";
  // Sandor code nazi didnt want hardcode file name
  // So will use gay java file chooser
  // OO takes all fun out of programming
  //String filename = "gayjava.txt";
  String msgText1 = "See Attachment For Grade Graph.\nDo Not Reply To This Email.\n";
  String subject = "Grade Graph Results";

  JFileChooser fc = new JFileChooser();
  fc.showOpenDialog(null);
  File filename= fc.getSelectedFile();

  // create some properties and get the default Session
  Properties props = System.getProperties();
  props.put("mail.smtps.host", host);
  props.put("mail.smtps.auth", "true");
  // If 25 no work try 587 or 465
  props.put("mail.smtp.port", "25");
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

      // Since Gmail is gay, needs auth, optonline didnt hence 
      // Didnt need user/pwd
      // Connect is inhertied from serivce in gay java language
      // Needed instance for .connect function not static uggh
      Transport transport = session.getTransport("smtps");
      // SendMessage also not static, hence needing instance of transport to do work
      transport.connect(host, "desktopgradeapp", "ufaufaufa");
      transport.sendMessage(msg, msg.getAllRecipients());

  }
  catch (MessagingException mex)
  {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
      }
  }
  JOptionPane.showMessageDialog(null, "Dear User,\nYour Email Was Sent.\nSincerely,\nHacker.\n");
}

}