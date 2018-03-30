package com.betcesc.game.common;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.jade.util.luti.ManejoProperties;

/**
 * @author Administrador
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Correo implements Serializable {
	static Logger log =Logger.getLogger(Correo.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8405183883365431420L;
	public String senderhost = null;
	public final String senderlogin = "betcesc@betcesc2.com";
	public final String senderpassword = "cesar147";
	public String from = null;
	public String to = null;
	public String subject = null;
	public String cc = null;
	public String bcc = null;
	public String text = null;
	public String textHtml = null;
	public boolean success = true;
	public int error = 0;

	public static final int ERROR_INVALID_HOST_LOGIN_PASSWORD = 1;
	public static final int ERROR_MENSAJE_CON_ERRORES = 2;
	public static final int ERROR_GENERAL = 3;

	public Correo() {
		//senderhost = "localhost";
		//senderhost = "mail.cantv.net";
		ManejoProperties config = new ManejoProperties("config.properties");
		config.cargarArchivoPropertie();
		
		senderhost = config.getProperty("senderhost");
		from = "betcesc@betcesc2.com";
		text = "";
		textHtml = "";
		success = true;
	}

	public Correo(String sender) {
		senderhost = sender;
		log.info("senderhost = " + senderhost);
		from = "betcesc@betcesc2.com";
		text = "";
		textHtml = "";
		success = true;
	}

	public int send() throws Exception {
		success = true;
		if ((senderhost == null) || (senderlogin == null) || (senderpassword == null)) {
			success = false;
			error = ERROR_INVALID_HOST_LOGIN_PASSWORD;
		} else {

			// Get system properties
			Properties props = System.getProperties();

			// Setup mail server
			props.put("mail.smtp.host", senderhost);

			javax.mail.Authenticator pa = null; // default: no authentication
			props.put("mail.smtp.auth", "true");
			pa = new javax.mail.Authenticator() {
				public javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(senderlogin, senderpassword);
				}
			};

			// Get session
			// Session session = Session.getDefaultInstance(props, pa); // esta
			// descrito como un error
			Session session = Session.getInstance(props, pa);

			// Define message
			MimeMessage message = new MimeMessage(session);

			// System.out.print(from+subject+text);
			try {
				message.setFrom(new InternetAddress(from));
				String[] str = to.split(","); // make an array containig all
												// the TO adress (two adress are
												// sepereted with ",".
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(str[0], false));
				for (int i = 1; i < str.length; i++) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(str[i], false));
				}
				// message.setRecipient(Message.RecipientType.TO,new
				// InternetAddress(to, false));
				if (cc != null) {
					str = cc.split(",");
					message.setRecipient(Message.RecipientType.CC, new InternetAddress(str[0], false));
					for (int i = 1; i < str.length; i++) {
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(str[i], false));
					}
					// message.setRecipient(Message.RecipientType.TO,new
					// InternetAddress(cc, false));
				}
				if (bcc != null) {
					str = bcc.split(",");
					message.setRecipient(Message.RecipientType.BCC, new InternetAddress(str[0], false));
					for (int i = 1; i < str.length; i++) {
						message.addRecipient(Message.RecipientType.BCC, new InternetAddress(str[i], false));
					}
				}
				message.setSubject(subject);
				message.setText(text);
				message.setContent((textHtml == null || textHtml.equals("") ? text : textHtml), "text/html");

				// Send message
				Transport.send(message);
			} catch (MessagingException e) {
				success = false;
				error = ERROR_MENSAJE_CON_ERRORES;
			}
		}
		return error;
	}

	/**
	 * @return Returns the bcc.
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc
	 *            The bcc to set.
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return Returns the cc.
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            The cc to set.
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Returns the senderhost.
	 */
	public String getSenderhost() {
		return senderhost;
	}

	/**
	 * @param senderhost
	 *            The senderhost to set.
	 */
	public void setSenderhost(String senderhost) {
		this.senderhost = senderhost;
	}

	/**
	 * @return Returns the senderlogin.
	 */
	public String getSenderlogin() {
		return senderlogin;
	}

	/**
	 * @return Returns the senderpassword.
	 */
	public String getSenderpassword() {
		return senderpassword;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return Returns the textHtml.
	 */
	public String getTextHtml() {
		return textHtml;
	}

	/**
	 * @param textHtml
	 *            The textHtml to set.
	 */
	public void setTextHtml(String textHtml) {
		this.textHtml = textHtml;
	}

	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}
}
