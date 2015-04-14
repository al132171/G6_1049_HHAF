package modelo.datos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"nombre","apellidos", "username", "password", "email", "telefono", "rol", "especialidad"})
@Entity
@NamedQueries({
        @NamedQuery(name="Usuario.login", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
})
//@MappedSuperclass
//@XmlTransient

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlTransient
	private Long id;
	private String nombre;
	private String apellidos;
	private String username;
	private String password;
	private String email;
	private String telefono;
	private String rol;
	private String especialidad;
	
	public Usuario(){
		super();
	}
	
	public Usuario(String nombre, String apellidos, String username, String password, String email, 
			String telefono, String rol, String especialidad){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.username = username;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
		this.rol =rol;
		this.especialidad = especialidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos
				+ ", username=" + username + ", password=" + password
				+ ", email=" + email + ", telefono=" + telefono + ", rol="
				+ rol + "]";
	}
	
	
}
