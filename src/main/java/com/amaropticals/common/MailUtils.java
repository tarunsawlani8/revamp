package com.amaropticals.common;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amaropticals.model.CreateInvoiceRequest;
import com.sun.istack.ByteArrayDataSource;

public class MailUtils {
	private final static Logger LOGGER = LoggerFactory.getLogger(MailUtils.class);

	private static final String host = "host7.dns2dns.com";
	private static final String user = "admin@amaropticals.in";// change accordingly
	private static final String password = "$Rajeshnisha8$";// change accordingly

	private static Properties props = null;

	private static void loadProperties() {

		// Get the session object
		if (props == null) {
			props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
			props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
			props.put("mail.smtp.port", "465"); // SMTP Port
		}

	}

	private static Session getSession() {
		loadProperties();
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		return session;

	}

	public static void sendMail(String toAddress, String subject, CreateInvoiceRequest request) {

		try {
			MimeMessage message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			message.setSubject(subject);
			BodyPart messageBodyPart1 = new MimeBodyPart();
			StringBuilder messageBody = new StringBuilder();
			messageBody.append("Hello " + request.getName() + ",");
			messageBody.append(
					"\nPlease find the attached receipt of your purchase.Do provide us the opportunity to serve you again.\n\nWarm Regards,\nAmar Opticals,\nNear Pushpak Electronics,\nT.P.Nagar, Korba\nPh:7067979795");
			messageBodyPart1.setText(messageBody.toString());

			// 4) create new MimeBodyPart object and set DataHandler object to this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = request.getInvoiceId() + ".pdf";// change accordingly
			PDFUtils n = new PDFUtils();
			DataSource source = null;

			source = new ByteArrayDataSource(n.convertToPDF(request), "application/pdf");

			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			// 5) create Multipart object and add MimeBodyPart objects to this object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// send the message
			Transport.send(message);

			LOGGER.info("Mail sent successfully to={}", toAddress);

		} catch (Exception e) {
			LOGGER.error("Can't send mail to={}, due to exception={}", toAddress, e);

		}

	}

}
