package com.TeamRCB.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.TeamRCB.Reports.ExtentManager;

public class Mailing {
	// to send email through gmail first have to enable below link
	// https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbkFmQVRieGNoOVBELTFGbEdrRTJqa3YxbjVld3xBQ3Jtc0ttR0NYUjhoZ08zZ3FsWjhINGV2WG5OR0pMR1p5Z3Rnai1HbFljM0IxR2pacFpWU0tWOC1FRTJmT0YtZkp3MHlndlhreWZRYUw3MTQ2c1FGVEw2elJMNElBc3lfWTI1SDFoaWRDekpWNzNVcVN6T2dPRQ&q=https%3A%2F%2Fmyaccount.google.com%2Flesssecureapps

	static String key, data, from, to, password;
	static String[] AllToAdress;
	static String host = "smtp.gmail.com";

	public void GetData() throws IOException {

		File prop = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\application.properties");
		FileInputStream input = new FileInputStream(prop);
		Properties props = new Properties();
		props.load(input);

		// Enumaration value = props.keys();
		// Iterator value = (Iterator) props.keys();
		Enumeration<Object> value = props.keys();

//		while(value.hasNext()){
//			key = (String) value.next();
		while (value.hasMoreElements()) {
			key = (String) value.nextElement();
			data = props.getProperty(key);

			if (key.equals("to")) {
				to = data;
			}

			if (key.equals("from")) {
				from = data;
			}
			if (key.equals("password")) {
				password = data;
			}

		}

		AllToAdress = to.split(",");

	}

	public void mail() throws IOException, AddressException, MessagingException {

		GetData();

		try {
//	System properties	
			Properties SysmProp = System.getProperties();
			SysmProp.put("mail.smtp.starttls.enable", "true");
			SysmProp.put("mail.smtp.host", host);
			SysmProp.put("mail.smtp.user", from);
			SysmProp.put("mail.smtp.password", password);
			SysmProp.put("mail.smtp.port", "587");
			SysmProp.put("mail.smtp.auth", "true");

//	Creating session and mime message object	
			Session session = Session.getInstance(SysmProp);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

//	Form Internet address array
			InternetAddress[] ia = new InternetAddress[AllToAdress.length];

			for (int i = 0; i < AllToAdress.length; i++) {
				ia[i] = new InternetAddress(AllToAdress[i]);
			}

			for (int j = 0; j < AllToAdress.length; j++) {
				message.addRecipient(Message.RecipientType.TO, ia[j]);
			}

			Date dt = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(dt);

//	Set subject and body and adding attachments.
			message.setSubject("Execution report for TestVagrant Technologies- Code Assessment on " + strDate);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hello, Good day! \n" + "\n"
					+ "All scenarios have been executed. Please find the attached report of the execution.  \n" + "\n"
					+ "Thanks,\n" + "Priye Ranjan.");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
//	String stringDate = (new SimpleDateFormat("dd-MMM-hh-mm")).format(new Date());
//	String fileName = "report-" + stringDate + ".html";
//	String folderName = "test-output/ExtentReport";

			File file2 = new File(ExtentManager.fetchFileName());
			// System.out.println(ExtentSetup.reportPath);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file2.getAbsolutePath());
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Report_" + strDate + ".html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

//	Start transportation of message
			Transport trans = session.getTransport("smtp");
			trans.connect(host, from, password);
			trans.sendMessage(message, message.getAllRecipients());

			for (String address : AllToAdress) {
				System.out.println("Mail has been sent to " + address);
			}

			trans.close();
		} catch (AddressException ae) {
			System.out.println("Address exception: " + ae);
		} catch (MessagingException me) {
			System.out.println("Messaging exception: " + me);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
