package com.amaropticals.revamp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;  
import javax.mail.internet.*;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;

import com.amaropticals.common.MailUtils;
import com.amaropticals.common.PDFUtils;
import com.amaropticals.model.CreateInvoiceRequest;
import com.amaropticals.model.ItemModel;
import com.amaropticals.model.LensModel;
import com.sun.istack.ByteArrayDataSource;  
  
public class MailTEst {  
 public static void main(String[] args) throws FOPException, IOException, TransformerException {  
 /* 
  String host="host7.dns2dns.com";  
  final String user="admin@amaropticals.in";//change accordingly  
  final String password="$Rajeshnisha8$";//change accordingly  
    
 
	Properties props = new Properties();
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
	props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
	props.put("mail.smtp.port", "465"); // SMTP Port

  
  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, password);
		}
	});
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("Hello");  
     message.setText("This is simple program of sending email using JavaMail API");  
    
     
     
     //3) create MimeBodyPart object and set your message text     
     BodyPart messageBodyPart1 = new MimeBodyPart();  
     messageBodyPart1.setText("This is message body");  
       
     //4) create new MimeBodyPart object and set DataHandler object to this object      
     MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
   
     String filename = "invoice.pdf";//change accordingly  
*/     PDFUtils n = new PDFUtils();
     CreateInvoiceRequest model = new CreateInvoiceRequest();
     model.setInvoiceId(18070601);
     model.setTotalAmount("120");
     model.setPendingAmount("0");
     model.setInitialAmount("123");
     model.setName("Ankit");
     ItemModel item = new ItemModel();
     item.setProductName("FRAME RIMLESS");
     item.setBuyQuantity(1);
     item.setUnitPrice(120);
     item.setTotalCost("120");
     
     ItemModel item2 = new ItemModel();
     item2.setLensActive(true);
     item2.setProductName("ARC");
     item2.setBuyQuantity(2);
     item2.setUnitPrice(240);
     item2.setTotalCost("480");
     LensModel lefteye0 = new  LensModel();
     LensModel lefteye1 = new  LensModel();
     LensModel righteye0 = new  LensModel();
     LensModel righteye1 = new  LensModel();
     lefteye0.setSph("-0.5");
     lefteye0.setCyl("-1.0");
     lefteye0.setAxial("-1.5");
     lefteye1.setSph("0.5");
    lefteye1.setCyl("1.0");
    lefteye1.setAxial("1.5"); 
    
    
    righteye0.setSph("-2.0");
    righteye0.setCyl("-2.5");
    righteye0.setAxial("-3.0");
    
    righteye1.setSph("2.0");
    righteye1.setCyl("2.5");
    righteye1.setAxial("3.0");
    item2.setLeftEye(Arrays.asList(lefteye0, lefteye1));
     item2.setRightEye(Arrays.asList(righteye0, righteye1));
     model.setPurchaseItems(Arrays.asList(item,item2 ));
     
     String to="monusmart84@gmail.com";//change accordingly  
     
     MailUtils.sendMail(to, "Your purchase at Amar Opticals Invoice Id:"+ model.getInvoiceId(), model);
/*        DataSource source = new ByteArrayDataSource(n.convertToPDF(model), "application/pdf");  
     messageBodyPart2.setDataHandler(new DataHandler(source));  
     messageBodyPart2.setFileName(filename);  
      
      
     //5) create Multipart object and add MimeBodyPart objects to this object      
     Multipart multipart = new MimeMultipart();  
     multipart.addBodyPart(messageBodyPart1);  
     multipart.addBodyPart(messageBodyPart2);  
   
     //6) set the multiplart object to the message object  
     message.setContent(multipart);  
      
     
    //send the message  
     Transport.send(message);  
  
     System.out.println("message sent successfully...");*/  
   
  //   } catch (MessagingException e) {e.printStackTrace();}  
 }  
}  