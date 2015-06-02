package controlador.utilidades;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import modelo.datos.Noticia;
import modelo.datos.Reserva;
import modelo.datos.Usuario;
import modelo.datos.UsuarioSuscritoNoticias;

public class EnviaNoticia{
	

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
 
	public void generateAndSendEmail(Noticia noticia, String[] emailSuscritos) throws AddressException, MessagingException {
 
//Step1		
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
//Step2				
		Address[] direcciones = new Address[emailSuscritos.length];
		for (int i = 0; i < direcciones.length; i++) {
			direcciones[i] = new InternetAddress(emailSuscritos[i]);
		}
		
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getInstance(mailServerProperties);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipients(Message.RecipientType.TO, direcciones);
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("ujimaticaNatureadventure@gmail.com"));
		String emailBody = "";
			generateMailMessage.setSubject("NatureAdventure: confirmación reserva");
			
			emailBody = "Hola, hemos publicado una nueva noticia en nuestro blog, te la ponemos a continuación.<br><br>"
					+ noticia.getTitulo() + "<br>" + noticia.getSubtitulo() + "<br>"
					+ noticia.getDescripcion() + "<br><br>"
					+ "Gracias por confiar en NatureAdventure. <br/> Un coordial saludo, <br/> La dirección de NatureAdventure";	
		
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
//Step3		
		
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
		
		transport.connect("smtp.gmail.com", "ujimaticaNatureadventure@gmail.com", "practicas123()");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());

		transport.close();
	}

}
