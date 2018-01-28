package herramientas.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public SendMail(){
		
	}
	
	public void sendMail(String name){
		Properties props = System.getProperties();
	
		// Setup mail server
		props.put("mail.smtp.host", "correoc4.c4tabasco.gob.mx");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.socketFactory.fallback", "true");
		props.put("mail.smtp.ssl.enable", "false");
		props.put("mail.smtp.auth.plain.disable", "true");
		// Get session
		//Session session = Session.getDefaultInstance(props, null);
		Session session = Session.getDefaultInstance(props,
		    new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication("scp@c4tabasco.gob.mx", "c4tabasco");
		        }
		  	});
			
		
		

		/*class EmailAuthenticator extends Authenticator {
			String user;
			String pw;
			EmailAuthenticator (String FROM, String PASSWORD)
			{
			    super();
			    this.user = FROM;
			    this.pw = PASSWORD;
			}
			public PasswordAuthentication getPasswordAuthentication()
			{
			    return new PasswordAuthentication(user, pw);
			}
		}*/

//		Session session = Session.getInstance(props, new EmailAuthenticator("scp@c4tabasco,gob.mx", "c4tabasco"));
//		session.setDebug(debug);
//		System.out.println("Session created");
	
		// Define message
		MimeMessage message = new MimeMessage(session);
		try{
			// Set the from address
			message.setFrom(new InternetAddress("scp@c4tabasco.gob.mx"));
		
			// Set the to address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("jbritop@c4tabasco.gob.mx"));
		
			// Set the subject
			message.setSubject("Hello "+name+" JavaMail");
		
			// Set the content
			message.setText("Welcome to JavaMail");
		
			// Send message
			Transport.send(message);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
