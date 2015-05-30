package controlador.utilidades;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
			emailBody = "La administración de NatureAdventure le confirma que su reserva realizada el día <b>"+reserva.getFechaReserva()+"</b> ha sido aceptada. " + "<br><br>"
					+ "A continuación le facilitamos los detalles de la transacción: <br/> "
					+ "<ul>"
					+ "<li> Nombre y apellidos cliente: "+reserva.getNombre() + " "+reserva.getApellidos()+"</li>"
					+ "<li> DNI cliente: "+reserva.getDni()+"</li>"
					+ "<li> E-mail: "+reserva.getCorreo()+"</li>"
					+ "<li> Actividad: "+reserva.getActividad().getNombre()+"</li>"
					+ "<li> Fecha de la actividad: "+reserva.getFechaActividad()+"</li>"
					+ "<li> Cantidad personas: "+reserva.getCantidadPersonas()+"</li>"
					+ "<li> Precio Total (I.V.A incluido): <strong>"+reserva.getPrecio()+"Euros</strong></li>"
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

}
