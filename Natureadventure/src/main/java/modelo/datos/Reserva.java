package modelo.datos;

/**
 * @author appujimatica
 * Objeto de dominio Reserva
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"fechaActividad", "fechaReserva","cantidadPersonas", "precio","nombre","apellidos","dni",
		"correo","telefono","estado", "actividad", "usuario", "contrato"})
@Entity
@NamedQueries({
	
	
	@NamedQuery(name="Reserva.encuentraTodasPendientes", query = "SELECT r FROM Reserva r WHERE r.estado = 'P'"),
	@NamedQuery(name="Reserva.encuentraTodasAceptadas", query = "SELECT r FROM Reserva r WHERE r.estado = 'A'"),
	@NamedQuery(name="Reserva.encuentraReserva", query = "SELECT r FROM Reserva r WHERE r.dni = :dni AND r.fechaReserva = :fechaReserva"),
	@NamedQuery(name="Reserva.encuentraTodasMonitorFechaActividad", query = "SELECT r FROM Reserva r WHERE r.usuario.username = :username AND r.fechaActividad = :fechaActividad"),
	@NamedQuery(name="Reserva.borrarReserva", query = "DELETE FROM Reserva r WHERE r.dni = :dni AND r.fechaReserva = :fechaReserva"),
	@NamedQuery(name="Reserva.encuentraTodas", query = "SELECT r FROM Reserva r WHERE r.usuario.username = :username")
	
	
})

public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlTransient
	private Long id;
	private String fechaActividad;
	private String fechaReserva;
	private int cantidadPersonas;
	private float precio;
	private String nombre;
	private String apellidos;
	private String dni;
	private String correo;
	private String telefono;
	private String estado;
	private String contrato;
	@ManyToOne
	private Actividad actividad;
	@ManyToOne
	private Usuario usuario;

	
	
	public Reserva(){
		super();
	}

	public Reserva(Long id, String fechaActividad, String fechaReserva,
			int cantidadPersonas, float precio, String nombre,
			String apellidos, String dni, String correo, String telefono,
			String estado, String contrato) {
		super();
		this.id = id;
		this.fechaActividad = fechaActividad;
		this.fechaReserva = fechaReserva;
		this.cantidadPersonas = cantidadPersonas;
		this.precio = precio;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.estado = estado;
		this.contrato = contrato;
	}


	public String getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(String fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaActividad=" + fechaActividad
				+ ", fechaReserva=" + fechaReserva + ", cantidadPersonas="
				+ cantidadPersonas + ", precio=" + precio + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", dni=" + dni
				+ ", correo=" + correo + ", telefono=" + telefono + ", estado="
				+ estado + "]";
	}
	
	
	
}
