package com.helper;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.jsp.JspException;

import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.pop3.POP3SSLStore;

public class MailUtility {

	private Session session = null;
	private Store store = null;
	private String username, password;
	private Folder folder;

	public MailUtility() {

	}

	public void setUserPass(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public Part getBodyMultiPart(String host, String user, String password,
			int messageNo, int attNo, String folerName) {
		Part part = null;
		try {
			MailUtility gmail = new MailUtility();
			gmail.setUserPass(user, password);
			gmail.connect();
			gmail.openFolder(folerName);
			Message msg = gmail.folder.getMessage(messageNo);
			Multipart mp = (Multipart) msg.getContent();
			part = mp.getBodyPart(attNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return part;
	}

	
	public void connect() throws Exception {

		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties pop3Props = new Properties();

		pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false"); // standard
																			// parameter
		pop3Props.setProperty("mail.pop3.port", "995");
		pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

		URLName url = new URLName("pop3", "pop.gmail.com", 995, "", username,
				password);

		session = Session.getInstance(pop3Props, null);
		store = new POP3SSLStore(session, url);
		store.connect();
		store.close();

		Properties iampProps = new Properties();

		iampProps.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
		iampProps.setProperty("mail.imap.socketFactory.fallback", "false");
		iampProps.setProperty("mail.imap.port", "993");
		iampProps.setProperty("mail.imap.socketFactory.port", "993");

		url = new URLName("imap", "imap.gmail.com", 993, "", username, password);

		session = Session.getInstance(iampProps, null);
		store = new IMAPSSLStore(session, url);
		store.connect();

	}
public static void main(String[] args) {
HashMap param=new HashMap();
param.put("to", "kirankale0999@gmail.com");
param.put("from", "Ashugore75@gmail.com");
param.put("subject", "mail.rajesh.agrawal@gmail.com");
param.put("body", "mail.rajesh.agrawal@gmail.com");
param.put("user", "mail.rajesh.agrawal@gmail.com");
param.put("password", "Ashugore75@");
new MailUtility().sendEmail(param);
}
	public boolean sendEmail(HashMap parameters) {

		boolean success = false;
		String recipients = StringHelper.n2s(parameters
				.get("to"));
		String sender = StringHelper.n2s(parameters
				.get("from"));
		String cc = StringHelper.n2s(parameters.get("cc"));
		String subject = StringHelper.n2s(parameters
				.get("subject"));
		String body = StringHelper.n2s(parameters
				.get("body"));
		final String userName = StringHelper.n2s(parameters
				.get("user"));
		final String password = StringHelper.n2s(parameters
				.get("password"));
		//
		// String recipients="mail.rajesh.agrawal@gmail.com";
		// String sender="rajagrawal4uster@localhost";
		// String cc="rajagrawal4uster@gmail.com";
		// String subject="Test Test";
		// String body="Test Body";
		// final String userName="rajagrawal4uster@gmail.com";
		// final String password="asd*";
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session sessio1n = Session.getDefaultInstance(props, auth);

		sessio1n.setDebug(true);

		Message msg = new MimeMessage(sessio1n);
		InternetAddress[] toAddrs = null, ccAddrs = null;

		try {

			if (recipients.length() > 0) {
				toAddrs = InternetAddress.parse(recipients, false);
				msg.setRecipients(Message.RecipientType.TO, toAddrs);
			} else
				throw new JspException("No recipient address specified");

			// if (sender.length()>0)
			// msg.setFrom(new InternetAddress(sender));
			// else
			// throw new JspException("No sender address specified");

			if (cc.length() > 0) {
				ccAddrs = InternetAddress.parse(cc, false);
				msg.setRecipients(Message.RecipientType.CC, ccAddrs);
			}

			if (subject != null)
				msg.setSubject(subject);
			msg.setSentDate(new Date());
			if (body != null)
				msg.setText(body);
			else
				msg.setText("");

			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		success = true;
		return success;
	}

	public void openFolder(String folderName) throws Exception {

		// Open the Folder
		folder = store.getDefaultFolder();

		folder = folder.getFolder(folderName);

		if (folder == null) {
			throw new Exception("Invalid folder");
		}

		// try to open read/write and if that fails try read-only
		try {

			folder.open(Folder.READ_WRITE);

		} catch (MessagingException ex) {

			folder.open(Folder.READ_ONLY);

		}

		// Message[] unreadMessages = folder.search(
		// new FlagTerm(new Flags(Flags.Flag.RECENT), true));
		//
		// String phone=ConnectionManager.getGmailUserId(username);
		// System.out.println("phone "+phone);
		//
		//
		// if(unreadMessages!=null&&phone.length()>0){
		// FetchMailUsage.unreadmessages=unreadMessages.length;
		// try{
		// Sender sender=new
		// Sender(phone,"You have "+FetchMailUsage.unreadmessages+" new mails.");
		// sender.send();
		// }catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// }

	}

	public void closeFolder() throws Exception {
		folder.close(false);
	}

	public int getMessageCount() throws Exception {
		return folder.getMessageCount();
	}

	public int getNewMessageCount() throws Exception {
		return folder.getNewMessageCount();
	}

	public void disconnect() throws Exception {
		store.close();
	}



	
	
	public boolean sendEmailMultipart(HashMap parameters) {

		boolean success = false;
		String recipients = StringHelper.n2s(parameters
				.get("to"));
		// String
		// sender=StringHelper.n2s(parameters.get("from"));
		String cc = StringHelper.n2s(parameters.get("cc"));
		String subject = StringHelper.n2s(parameters
				.get("subject"));
		String body = StringHelper.n2s(parameters
				.get("body"));
		Object multipart = parameters.get("ATTACH");
		final String userName = StringHelper.n2s(parameters
				.get("user"));
		final String password = StringHelper.n2s(parameters
				.get("password"));

		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session sessio1n = Session.getDefaultInstance(props, auth);

		sessio1n.setDebug(true);

		Message msg = new MimeMessage(sessio1n);
		InternetAddress[] toAddrs = null, ccAddrs = null;

		try {

			if (recipients.length() > 0) {
				String toList = "";
				String toListA[] = recipients.split(",");
				for (int i = 0; i < toListA.length; i++) {
					if (!toListA[i].trim().equalsIgnoreCase("all"))
						// toList+=","+StringHelper.n2s(details.get("EMAIL_LIST"));
						// else
						toList += "," + toListA[i];
				}

				recipients = toList.substring(1);
				toAddrs = InternetAddress.parse(toList, false);

				msg.setRecipients(Message.RecipientType.TO, toAddrs);
			} else
				throw new JspException("No recipient address specified");

			if (userName.length() > 0)
				msg.setFrom(new InternetAddress(userName));
			else
				throw new JspException("No sender address specified");

			if (cc.length() > 0) {

				String ccList = ""; 
				String ccListA[] = cc.split(",");
				for (int i = 0; i < ccListA.length; i++) {
					// if(ccListA[i].trim().equalsIgnoreCase("all"))
					// ccList+=","+StringHelper.n2s(details.get("EMAIL_LIST"));
					// else
					ccList += "," + ccListA[i];
				}
				cc = ccList;
				ccAddrs = InternetAddress.parse(ccList, false);
				msg.setRecipients(Message.RecipientType.CC, ccAddrs);
			}

			if (subject != null)
				msg.setSubject(subject);
			msg.setSentDate(new Date());
			Multipart mp = new MimeMultipart();

			MimeBodyPart mailBody = new MimeBodyPart();

			if (body != null)
				mailBody.setText(body);
			else
				mailBody.setText("");
			mp.addBodyPart(mailBody);
			if (multipart != null) {
				if (multipart instanceof ArrayList) {
					ArrayList arr = (ArrayList) multipart;
					for (int i = 0; i < arr.size(); i++) {
						mp.addBodyPart((MimeBodyPart) arr.get(i));
					}
				} else if (multipart instanceof MimeBodyPart) {
					mp.addBodyPart((MimeBodyPart) multipart);
				}
			}
			msg.setContent(mp);
			System.out.println("To List " + recipients);
			System.out.println("CC List " + cc);

			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		success = true;

		return success;
	}

}
