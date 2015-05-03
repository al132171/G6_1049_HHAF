package controlador.utilidades;


import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import modelo.datos.Reserva;
import modelo.datos.Usuario;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;




public class CrearFactura {

	public static void createContrato(Reserva reserva, String id, Usuario monitor) {
		try {
			OutputStream file = new FileOutputStream(new File("/home/fer/Desktop/natureadventure_rep/Natureadventure/src/main/webapp/contratos/"+id+".pdf"));
			Calendar calendario = new GregorianCalendar();
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();


			Paragraph contrato = new Paragraph();

			Image image1 = Image.getInstance("/home/fer/Desktop/natureadventure_rep/Natureadventure/src/main/webapp/contratos/logo.png");
			document.add(image1);

			contrato.setFont(FontFactory.getFont("Courier",10));

			System.out.println("**************************lo hace bien contrato");
			contrato.add("\n\n Castellón de la Plana, a "+calendario.get(Calendar.DAY_OF_MONTH)+" de "+ calendario.getDisplayName(Calendar.MONTH, 0, Locale.ENGLISH) +
					" de "+calendario.get(Calendar.YEAR) +"\n");
			document.add(contrato);
			contrato.clear();

			contrato.setAlignment(Element.ALIGN_CENTER);
			contrato.setFont(FontFactory.getFont("Courier",10, Font.BOLD));
			contrato.add("REUNIDOS DE UNA  PARTE,\n");
			document.add(contrato);
			contrato.clear();

			contrato.setAlignment(Element.ALIGN_JUSTIFIED);
			contrato.setFont(FontFactory.getFont("Courier",10));
			contrato.add("con D.N.I. número "+reserva.getDni()+ " con nombre y apellidos "+reserva.getNombre() +" "+reserva.getApellidos() + " , teléfono "+ reserva.getTelefono() 
					+ " y e-mail "+ reserva.getCorreo() + "en adelante, el “CLIENTE”\n \n");
			document.add(contrato);

			contrato.clear();
			contrato.setAlignment(Element.ALIGN_CENTER);
			contrato.setFont(FontFactory.getFont("Courier",10, Font.BOLD));
			contrato.add("DE OTRA  PARTE,\n");
			document.add(contrato);

			contrato.clear();
			contrato.setAlignment(Element.ALIGN_JUSTIFIED);
			contrato.setFont(FontFactory.getFont("Courier",10));
			contrato.add("con D.N.I. número 12345678X y "
					+ "en nombre y representación de la empresa mercantil NATUREADVENTURE (…), en adelante, el “PROVEEDOR”, "
					+ "domiciliada en Castellón de la Plana, calle Falsa nº 123, C.P. 12007  y C.I.F. 87654321X."
					+ "El CLIENTE y el PROVEEDOR, en adelante, podrán ser denominadas, individualmente, “la Parte” y, conjuntamente, "
					+ "“las Partes”, reconociéndose mutuamente capacidad jurídica y de obrar suficiente para la celebración del presente Contrato \n \n");
			document.add(contrato);

			contrato.clear();
			contrato.setAlignment(Element.ALIGN_CENTER);
			contrato.setFont(FontFactory.getFont("Courier",10, Font.BOLD));
			contrato.add("EXPONEN, \n");
			document.add(contrato);

			contrato.clear();
			contrato.setAlignment(Element.ALIGN_JUSTIFIED);
			contrato.setFont(FontFactory.getFont("Courier",10));
			contrato.add("PRIMERO: Que el CLIENTE a fecha de  ha contratado la actividad ZZZZ. La reserva posee los siguientes datos:\n");
			contrato.add("- Fecha de la actividad: " +reserva.getFechaActividad() +"\n - Aseguradora que cubre la actividad: ROMPETECHOS SEGUROS (Incluída en el precio) \n "
					+ "- Número de personas: "+ reserva.getCantidadPersonas() +"\n - Monitor asignado: "+monitor.getNombre() +" "+monitor.getApellidos() +"(D.N.I: "+monitor.getDni() 
					+")\n - Precio total "+reserva.getPrecio()+"€ \n \n");

			contrato.add("SEGUNDO: Que el PROVEEDOR es una empresa especializada en la prestación de servicios de "+
					"de actividades deportivas supervisadas todas ellas por un monitor y una compañía aseguradora que cubre los posibles accidentes.\n");
			document.add(contrato);

			contrato.clear();
			contrato.setAlignment(Element.ALIGN_JUSTIFIED);
			contrato.setFont(FontFactory.getFont("Courier",10, Font.ITALIC));
			contrato.add("* Privacidad: Utilizamos los datos personales que usted nos facilita para ofrecer nuestros Servicios, prestar apoyo a los clientes, realizar las comprobaciones de seguridad e identificación necesarias, "
					+ "procesar las transacciones online, ayudarle a participar en promociones de terceros, cumplir "
					+ "con los requisitos de la empresa y cualquier otro propósito relacionado con la gestión de los Servicios.\n \n");
			document.add(contrato);

			contrato.clear();
			contrato.setAlignment(Element.ALIGN_JUSTIFIED);
			contrato.setFont(FontFactory.getFont("Courier",10));
			contrato.add("TERCERO: Que las Partes están interesadas en celebrar un contrato de arrendamiento de equipos "
					+ "en virtud del cual el PROVEEDOR arriende al CLIENTE los equipos que se relacionan en este contrato.\n\n \n");
			contrato.add("Y en prueba de cuanto antecede, las Partes suscriben el Contrato, en dos ejemplares y a un solo efecto, "
					+ "en el lugar y fecha señalados en el encabezamiento.\n\n");
			document.add(contrato);



			PdfPTable mitablasimple=new PdfPTable(2);
			mitablasimple.setExtendLastRow(true);
			mitablasimple.setWidthPercentage(50);
			Image image2 = Image.getInstance("/home/fer/Documentos/EI1049-tallersoftware/createFactura/src/main/tmp/firmaNatureadventure.png");
			//Image image3 = Image.getInstance("/home/fer/Documentos/EI1049-tallersoftware/createFactura/src/main/tmp/firmaCliente.png");

			mitablasimple.addCell(image2);
			mitablasimple.addCell(reserva.getNombre() + " "+reserva.getApellidos());

			mitablasimple.addCell("Por el proveedor");
			mitablasimple.addCell("Por el cliente");

			document.add(mitablasimple);

			document.close();
			file.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}


}