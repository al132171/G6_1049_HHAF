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

/**
 * Created by oscar on 27/11/14.
 */

@XmlRootElement
@XmlType(propOrder = {"id", "nombre", "duracion","horaInicio", "fechaInicio","fechaFin","descripcion","nivel", "precio","participantesMax","participantesMin","lugar","imagen", "categoria", "estado"})
@Entity
@NamedQueries({
	@NamedQuery(name="Actividad.encuentraTodasActivas", query = "SELECT p FROM Actividad p WHERE p.estado = 'T'"),
	@NamedQuery(name="Actividad.encuentraOfertas", query = "SELECT p FROM Actividad p WHERE p.estado = 'T' ORDER BY p.precio"),
	@NamedQuery(name="Actividad.encuentraNovedades", query = "SELECT p FROM Actividad p WHERE p.estado = 'T' ORDER BY p.id"),
	@NamedQuery(name="Actividad.encuentraTodasArchivadas", query = "SELECT p FROM Actividad p WHERE p.estado = 'F'"),
	@NamedQuery(name = "Actividad.encuentraPorNombre", query = "SELECT p FROM Actividad p WHERE p.nombre = :nombre"),
	@NamedQuery(name = "Actividad.encuentraPorNombreActiva", query = "SELECT p FROM Actividad p WHERE p.estado = 'T' AND p.nombre = :nombre"),
	@NamedQuery(name = "Actividad.encuentraPorPalabraClave", query = "SELECT p FROM Actividad p WHERE p.estado = 'T'AND (p.nombre LIKE :palabraClave OR p.descripcion LIKE :palabraClave)"),
	@NamedQuery(name = "Actividad.encuentraPorCategoria", query = "SELECT p FROM Actividad p WHERE p.estado = 'T'AND p.categoria LIKE :categoria"),
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
	private String categoria;
	private String estado;
	
	public Actividad() {
		super();
	}

	public Actividad(Long id, String nombre, String duracion,
			String horaInicio, String fechaInicio, String fechaFin,
			String descripcion, String nivel, float precio,
			int participantesMax, int participantesMin, String lugar, 
			String imagen, String categoria, String estado) {
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
		this.categoria = categoria;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}

