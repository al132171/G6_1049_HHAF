package modelo.datos;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by oscar on 27/11/14.
 */

@XmlRootElement
@XmlType(propOrder = {"nombre", "duracion","horaInicio", "fechaInicio","fechaFin","descripcion","nivel","precio","participantesMax","participantesMin","lugar","imagen"})
@Entity
@NamedQueries({
	@NamedQuery(name="Actividad.encuentraTodas", query = "SELECT p FROM Actividad p"),
	@NamedQuery(name = "Actividad.encuentraPorNombre", query = "SELECT p FROM Actividad p WHERE p.nombre = :nombre"),
	@NamedQuery(name="Actividad.borraPorNombre", query = "DELETE FROM Actividad p WHERE p.nombre = :nombre")
})


public class Actividad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlTransient
	private Long id;
	private String nombre;
	private String duracion;
	private String horaInicio;
	private String fechaInicio;
	private String fechaFin;
	private String descripcion;
	private String nivel;
	private float precio;
	private int participantesMax;
	private int participantesMin;
	private String lugar;
	//@Lob
	//private byte[] imagen;
	private String imagen;

	public Actividad() {
		super();
	}

	public Actividad(Long id, String nombre, String duracion,
			String horaInicio, String fechaInicio, String fechaFin,
			String descripcion, String nivel, float precio,
			int participantesMax, int participantesMin, String lugar, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.horaInicio = horaInicio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.precio = precio;
		this.participantesMax = participantesMax;
		this.participantesMin = participantesMin;
		this.lugar = lugar;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getParticipantesMax() {
		return participantesMax;
	}

	public void setParticipantesMax(int participantesMax) {
		this.participantesMax = participantesMax;
	}

	public int getParticipantesMin() {
		return participantesMin;
	}

	public void setParticipantesMin(int participantesMin) {
		this.participantesMin = participantesMin;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}    




}

