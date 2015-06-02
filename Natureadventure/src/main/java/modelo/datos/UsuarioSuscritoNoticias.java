package modelo.datos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"nombre", "email"})
@Entity
@NamedQueries({
	@NamedQuery(name="UsuarioSuscritoNoticias.encuentraTodos", query = "SELECT usn FROM UsuarioSuscritoNoticias usn"),
	@NamedQuery(name="UsuarioSuscritoNoticias.encuentraSuscripcion", query = "SELECT usn FROM UsuarioSuscritoNoticias usn WHERE usn.email = :email")
})


public class UsuarioSuscritoNoticias {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlTransient
	private String nombre;
	private String email;
	
	public UsuarioSuscritoNoticias() {
		super();
	}

	public UsuarioSuscritoNoticias(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UsuarioSuscritoNoticias [nombre=" + nombre + ", email=" + email
				+ "]";
	}
	
}
