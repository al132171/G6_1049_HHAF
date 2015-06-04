package modelo.datos;

/**
 * @author appujimatica
 * Objeto de dominio Usuario
 */

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
@XmlType(propOrder = {"nombre","apellidos", "username", "password", "dni", "email", "telefono", "rol", "especialidad", "estado"})
@Entity
@NamedQueries({
//	####### GERENTE
        @NamedQuery(name="Usuario.login", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name="Usuario.perfil", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
        @NamedQuery(name="Usuario.buscaMonitorDisponible", query = "SELECT u FROM Usuario u WHERE u.especialidad = :especialidad"),
        @NamedQuery(name="Usuario.buscaMonitorPorDni", query = "SELECT u FROM Usuario u WHERE u.dni = :dni"),
        
//  ####### MONITOR
        @NamedQuery(name="Usuario.encuentraTodosActivos", query = "SELECT p FROM Usuario p WHERE p.rol = 'M' AND p.estado = 'A'"),
        @NamedQuery(name="Usuario.encuentraTodosNoDisponibles", query = "SELECT p FROM Usuario p WHERE p.rol = 'M' AND p.estado = 'B'"),
        @NamedQuery(name="Usuario.encuentraPorNombre", query = "SELECT p FROM Usuario p WHERE p.nombre = :nombre AND p.rol = 'M'"),
        @NamedQuery(name="Usuario.borraPorDni", query = "DELETE FROM Usuario WHERE dni = :dni"),
        @NamedQuery(name="Usuario.encuentraPorEspecialidad", query = "SELECT p FROM Usuario p WHERE p.especialidad = :especialidad")

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
	private String dni;
	private String email;
	private String telefono;
	private String rol;
	private String especialidad;
	private String estado; // A = activo , B = baja

	public Usuario(){
		super();
	}
	
	public Usuario(String nombre, String apellidos, String username, String password, String dni,
			String email, String telefono, String rol, String especialidad, String estado){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.username = username;
		this.password = password;
		this.setDni(dni);
		this.email = email;
		this.telefono = telefono;
		this.rol =rol;
		this.especialidad = especialidad;
		this.estado = estado;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
