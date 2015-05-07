package controlador.utilidades;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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

public class EnviaCorreo{
	

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
 
	public void generateAndSendEmail() throws AddressException, MessagingException {
 
//Step1		
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
//Step2				
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getInstance(mailServerProperties);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("frodriguezsidro@gmail.com"));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("appujimatica@gmail.com"));
		generateMailMessage.setSubject("Appujimática ha ampliado su catálogo");
		String emailBody = "El equipo de Appujimática le notifica que ha ampliado su catálogo disponible en su página web. " + "<br><br>";
		MimeMultipart multiParte = new MimeMultipart();
		BodyPart adjunto = new MimeBodyPart();
		adjunto.setDataHandler(new DataHandler(new FileDataSource("/home/fer/Desktop/natureadventure_rep/Natureadventure/src/main/webapp/contratos/6781430324446945.pdf")));
		adjunto.setFileName("6781430324446945.pdf");
//		
//		BodyPart texto = new MimeBodyPart();
//		texto.setText("Ha comprado de forma satisfactoria sus entradas. Se adjuntan las entradas "
//				+ "para que pueda acceder a nuestras instalaciones.\n Gracias por confiar en nosotros, \n Un cordial saludo,"
//				+ "\n La dirección de TicketSales.");
//
//
//		
//		multiParte.addBodyPart(texto);
		multiParte.addBodyPart(adjunto);
		
		generateMailMessage.setContent(emailBody, "text/html");
		generateMailMessage.setContent(multiParte);
		System.out.println("Mail Session has been created successfully..");
 
//Step3		
		
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
		
		// Enter your correct gmail UserID and Password (XXXarpitshah@gmail.com)
		transport.connect("smtp.gmail.com", "appujimatica@gmail.com", "practicas123()");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		
		

		transport.close();
	}

}
