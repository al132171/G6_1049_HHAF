package controlador.utilidades;

/**
 * @author appujimatica
 * Clase de servicio que envía los correos generados en la plataforma
 */

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modelo.datos.Noticia;
import modelo.datos.Reserva;

public class EnviaCorreo{


	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;


	public void generateAndSendEmail(Reserva reserva, String tipo) throws AddressException, MessagingException {

		//Step1		
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");

		//Step2				
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getInstance(mailServerProperties);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("al132171@uji.es"));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("ujimaticaNatureadventure@gmail.com"));
		String emailBody = "";
		if(tipo.equals("aceptar")){
			generateMailMessage.setSubject("NatureAdventure: confirmación reserva");
			emailBody = "La administración de NatureAdventure le confirma que su reserva realizada el día "+reserva.getFechaReserva()+" ha sido aceptada. " + "<br><br>"
					+ "A continuación le facilitamos los detalles de la transacción: <br/> "
					+ "<ul>"
					+ "<li> Nombre y apellidos cliente: "+reserva.getNombre() + " "+reserva.getApellidos()+"</li>"
					+ "<li> DNI cliente: "+reserva.getDni()+"</li>"
					+ "<li> E-mail: "+reserva.getCorreo()+"</li>"
					+ "<li> Actividad: "+reserva.getActividad().getNombre()+"</li>"
					+ "<li> Fecha de la actividad: "+reserva.getFechaActividad()+"</li>"
					+ "<li> Cantidad personas: "+reserva.getCantidadPersonas()+"</li>"
					+ "<li> Precio Total (con I.V.A): <strong>"+reserva.getPrecio()+"€</strong></li>"
					+ "</ul>"
					+ "A continuación le facilitamos la información personal del monitor asignado a la actividad: <br/>"
					+ "<ul>"
					+ "<li> Nombre y apellidos: "+reserva.getUsuario().getNombre()+" "+reserva.getUsuario().getApellidos()+"</li>"
					+ "<li> Teléfono: " +reserva.getUsuario().getTelefono()+"</li>"
					+ "<li> Correo: "+reserva.getUsuario().getEmail()+"</li>"
					+ "</ul>"
					+ "Gracias por confiar en NatureAdventure. <br/> Reciba un coordial saludo, <br/> La dirección de NatureAdventure";	
		}else{
			generateMailMessage.setSubject("NatureAdventure: reserva cancelada");

			emailBody = "La administración de NatureAdventure lamenta comunicarle que la actividad no ha podido ser aceptada debido a la falta de disponibilidad de los monitores. <br/>"
					+ "Se le ha devuelto el importe íntegro de la reserva <br/>"+
					"Lamentamos que no pueda disfrutar de la actividad deseada y le invitamos a que siga comprando en nuestra plataforma.";

		}
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");

		//Step3		

		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");

		transport.connect("smtp.gmail.com", "ujimaticaNatureadventure@gmail.com", "practicas123()");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());



		transport.close();
	}


	public void enviaNoticia(Noticia noticia, String[] emailSuscritos) throws AddressException, MessagingException {

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
		generateMailMessage.setSubject("NatureAdventure: noticia publicada");

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
